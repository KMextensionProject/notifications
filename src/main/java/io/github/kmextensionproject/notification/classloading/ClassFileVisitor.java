package io.github.kmextensionproject.notification.classloading;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

/**
 * The object of this class is intended for passing into the
 * {@link Files#walkFileTree} to scan parent package and all its sub packages
 * for class files.
 * <p>
 * This class provides a list of fully qualified class names for all class files
 * found under the package.
 *
 * @author mkrajcovic
 */
class ClassFileVisitor extends SimpleFileVisitor<Path> {

	private final Set<String> classNames;

	ClassFileVisitor() {
		classNames = new HashSet<>(20);
	}

	/**
	 * @return fully qualified names of found classes
	 */
	Set<String> getFoundClassNames() {
		return new HashSet<>(classNames);
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes bas) {
		String absoluteFilePath = file.toString();
		String fullyQualifiedClassName;

		if (absoluteFilePath.endsWith(".class")) {
			fullyQualifiedClassName = absoluteFilePath.substring(absoluteFilePath.lastIndexOf("classes/") + 8).replace('/', '.');
			// delete the .class suffix
			fullyQualifiedClassName = fullyQualifiedClassName.substring(0, fullyQualifiedClassName.lastIndexOf("."));
			classNames.add(fullyQualifiedClassName);
		}
		return CONTINUE;
	}
}
