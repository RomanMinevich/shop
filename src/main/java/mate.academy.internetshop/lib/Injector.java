package mate.academy.internetshop.lib;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Injector {
    private static final String PROJECT_MAIN_PACKAGE = "mate.academy.internetshop";
    private static final List<Class> classes = new ArrayList<>();

    static {
        try {
            classes.addAll(getClasses(PROJECT_MAIN_PACKAGE));
        } catch (ClassNotFoundException | IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void injectDependency() throws IllegalAccessException {
        List<Field> annotatedFields = classes
                    .stream()
                    .map(Class::getDeclaredFields)
                    .flatMap(Stream::of)
                    .filter(field -> field.isAnnotationPresent(Inject.class))
                    .collect(Collectors.toList());
        for (Field field : annotatedFields) {
            Object object = AnnotatedClassMap.implement(field.getType());
            if (object.getClass().isAnnotationPresent(Service.class)
                    || object.getClass().isAnnotationPresent(Dao.class)) {
                field.setAccessible(true);
                field.set(null, object);
            }
        }
    }

    private static List<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class> findClasses(File directory, String packageName)
            throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(
                        Class.forName(packageName + '.' + file.getName().substring(
                        0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
