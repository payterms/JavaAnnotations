import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestUnitImpl implements TestUnit {

    private Method before;// метод для аннотации BeforeSuite

    private Method after;// метод для аннотации AfterSuite

    private List<Method> testList;// список методов для аннотации Test (располагаем в порядке заданной в аннтоции метода приоритетности)

    public TestUnitImpl() {
    }

    public Method getBefore() {
        return before;
    }

    public void setBefore(Method before) {
        if (this.before == null) {// если метод для аннотации еще не назначен
            this.before = before;
        } else {// Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре
            //, иначе необходимо бросить RuntimeException при запуске «тестирования».
            throw new RuntimeException();
        }

    }
    public Method getAfter() {
        return after;
    }

    public void setAfter(Method after) {
        if (this.after == null) {// если метод для аннотации еще не назначен
            this.after = after;
        } else {// Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре
            //, иначе необходимо бросить RuntimeException при запуске «тестирования».
            throw new RuntimeException();
        }
    }

    public List<Method> getTestList() {
        return testList;
    }

    @Override
    public void addTestToList(Method test) {
        if (this.testList == null) {// если список тестов пустой - создаем его
            this.testList = new ArrayList<>();
        }
        this.testList.add(test);// добавляем метод-тест
    }

    @Override
    public void sortTests() {
        // сортируем методы на основании данных о приоритете из аннотации методов
        if (this.testList != null) {
            this.testList.sort((Method m1, Method m2) -> {
                Test t1 = m1.getAnnotation(Test.class);
                Test t2 = m2.getAnnotation(Test.class);
                return (t1.priority() < t2.priority()) ? 1 :
                        (t1.priority() > t2.priority()) ? -1 : 0;
            });
        }
    }
}
