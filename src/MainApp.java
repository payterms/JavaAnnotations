public class MainApp {

    public static void main(String[] args) {
        //Вызываем статический метод start(), которому в качестве параметра передается объект типа Class
        TestInvoker.start(TestClass.class);
        //Вызываем статический метод start(), которому в качестве параметра передается имя класса.
        TestInvoker.start(TestClass.class.getName());
    }
}
