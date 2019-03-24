import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestInvoker {

    //статический метод start(), которому в качестве параметра передается объект типа Class
    public static void start(Class className) {
        inspector(className);
    }

    //статический метод start(), которому в качестве параметра передается имя класса.
    public static void start(String className) {
        try {
            Class stringClass = Class.forName(className);
            inspector(stringClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void inspector(Class aClass) {
        Method[] methods = aClass.getDeclaredMethods();
        TestUnitImpl testUnit = new TestUnitImpl();

        for (Method method : methods) {

            // аннотации для текущего метода
            final Annotation[] annotations = method.getAnnotations();

            // проверяем чтобы у метода была только одна аннотация
            if (annotations.length != 1) {
                throw new AnnotationCountException("Multiple Annotations mode is prohibited");
            }

            Annotation annotation = annotations[0];

            if (annotation instanceof BeforeSuite) {
                testUnit.setBefore(method);
                continue;
            }

            if (annotation instanceof AfterSuite) {
                testUnit.setAfter(method);
                continue;
            }

            if (annotation instanceof Test) {
                testUnit.addTestToList(method);
            }
        }
        testUnit.sortTests();
        invokeMethods(testUnit);
    }

    private static void invokeMethods(TestUnitImpl testUnit) {
        final TestClass testClass = new TestClass();
        final Method before = testUnit.getBefore();
        final Method after = testUnit.getAfter();
        final List<Method> tests = testUnit.getTestList();

        if (before != null) {
            try {
                before.invoke(testClass, null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        if (tests != null) {
            tests.forEach((k) -> {
                try {
                    k.invoke(testClass, null);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        }

        if (after != null) {
            try {
                after.invoke(testClass, null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
