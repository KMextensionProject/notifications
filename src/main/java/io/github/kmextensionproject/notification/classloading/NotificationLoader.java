package io.github.kmextensionproject.notification.classloading;

import static java.nio.file.Files.walkFileTree;
import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;
import static java.util.logging.Logger.getLogger;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import io.github.kmextensionproject.notification.base.Notification;
import io.github.kmextensionproject.notification.base.StandardOutputNotification;

/**
 * Use this class to load multiple plugged in domain {@link Notification}
 * implementations or custom ones registered by user.
 * <p>
 * The motivation behind this is to have multiple communication interfaces for
 * sending simple notification messages in one place and be able to iteratively
 * distribute the desired message to multiple channels and recipients.
 *
 * @author mkrajcovic
 */
public class NotificationLoader {

	private static final Logger LOG = getLogger(NotificationLoader.class.getName());
	private static final String DOMAIN_PACKAGE = "io.github.kmextensionproject.notification";

	private NotificationLoader() {
		throw new IllegalStateException("NotificationLoader was not designed to be instantiated");
	}

	/**
	 * Uses reflection to load registered {@link Notification} direct
	 * implementations found on the class path in the following order:
	 * <ul>
	 * <li>modules under {@code io.github.kmextensionproject.notification} domain</li>
	 * <li>user classes added to the {@link GlobalNotificationRegistry}</li>
	 * </ul>
	 * <p>
	 * If there is no domain notification module present on the class path nor
	 * any of the user defined {@link Notification} implementations are found in
	 * the {@link GlobalNotificationRegistry} before calling this method, the
	 * default {@link StandardOutputNotification} is instantiated and added to
	 * the resulting list.
	 *
	 * @return non-null, non-empty list of registered notification objects
	 */
	public static List<Notification> loadRegisteredNotifications() {
		Set<Class<? extends Notification>> registeredClasses = findImplementors(loadClassNames(DOMAIN_PACKAGE));
		registeredClasses.addAll(GlobalNotificationRegistry.getInstance().getRegisteredClasses());

		List<Notification> notifications = instantiate(registeredClasses);
		if (notifications.isEmpty()) {
			LOG.warning("Domain modules are missing and user registry is empty");
			LOG.info("Registering the StandardOutputNotification as default");
			notifications.add(new StandardOutputNotification());
		} else {
			LOG.info(() -> "Registered notifications for: " + registeredClasses.stream()
				.map(Class::getSimpleName)
				.collect(toList()));
		}
		return notifications;
	}

	// performs lookup even for sub packages
	private static Set<String> loadClassNames(String packageName) {
		String packagePath = packageName.replace('.', '/');
		String filePath = locatePackageFullPath(packagePath);
		if (filePath.contains("!")) {
			return readClassNamesFromJarFile(filePath, packagePath);
		}
		return readClassNamesFromClassPath(filePath);
	}

	private static String locatePackageFullPath(String packagePath) {
		URL resourceUrl = NotificationLoader.class
				.getClassLoader()
				.getResource(packagePath);

		if (nonNull(resourceUrl)) {
			return resourceUrl.getFile();
		}
		throw new IllegalStateException("Unable to locate resource " + packagePath);
	}

	private static Set<String> readClassNamesFromJarFile(String file, String packagePath) {
		Set<String> classNames = new HashSet<>();
		String jarLocation = file.split("!")[0].substring(5); // URL = file:
		try (ZipFile jarFile = new ZipFile(jarLocation)) {
			Enumeration<? extends ZipEntry> jarEntries = jarFile.entries();
			while (jarEntries.hasMoreElements()) {
				ZipEntry jarEntry = jarEntries.nextElement();
				String entryName = jarEntry.getName();
				if (entryName.startsWith(packagePath) && entryName.endsWith(".class")) {
					String fullyQualifiedClassName = entryName.substring(0, entryName.lastIndexOf('.')).replace('/', '.');
					classNames.add(fullyQualifiedClassName);
				}
			}
		} catch (IOException ioex) {
			throw new IllegalStateException("Unable to read notification class files");
		}
		return classNames;
	}

	private static Set<String> readClassNamesFromClassPath(String file) {
		ClassFileVisitor finder = new ClassFileVisitor();
		try {
			walkFileTree(Paths.get(file), finder);
		} catch (IOException ioex) {
			LOG.severe("Error reading class path " + ioex);
		}
		return finder.getFoundClassNames();
	}

	@SuppressWarnings("unchecked")
	private static Set<Class<? extends Notification>> findImplementors(Set<String> classNames) {
		return classNames.stream()
			.map(NotificationLoader::getClass)
			.filter(Objects::nonNull)
			.filter(NotificationLoader::isNotificationSubclass)
			.map(e -> (Class<? extends Notification>) e)
			.collect(toCollection(LinkedHashSet::new));
	}

	private static Class<?> getClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	private static boolean isNotificationSubclass(Class<?> classObj) {
		Class<?>[] interfaces = classObj.getInterfaces();
		if (interfaces.length == 0) {
			return false;
		}
		return stream(interfaces).anyMatch(Notification.class::equals);
	}

	private static List<Notification> instantiate(Collection<Class<? extends Notification>> registeredClasses) {
		return registeredClasses.stream()
			.map(NotificationLoader::newInstance)
			.filter(Objects::nonNull)
			.collect(toList());
	}

	private static Notification newInstance(Class<?> classObj) {
		try {
			return (Notification) classObj.getDeclaredConstructor().newInstance();
		} catch (NoSuchMethodException |
				 SecurityException |
				 InstantiationException |
				 InvocationTargetException |
				 IllegalAccessException ex) {
			return null;
		}
	}
}
