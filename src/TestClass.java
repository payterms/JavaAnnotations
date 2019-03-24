public class TestClass {

    @BeforeSuite
    public void init() {
        System.out.println("BeforeSuite method");
    }

//    Uncomment it to test Exception
//    @BeforeSuite
//    public void init2() {
//        System.out.println("Another BeforeSuite method");
//    }

    @AfterSuite
    public void end() {
        System.out.println("AfterSuite method");
    }

//    Uncomment it to test Exception
//    @AfterSuite
//    public void end2() {
//        System.out.println("Another AfterSuite method");
//    }

    @Test
    public void test1() {
        System.out.println("It's method 1 with default priority");
    }

    @Test(priority = 10)
    public void test6() {
        System.out.println("It's method 6 with priority 10");
    }

    @Test(priority = 1)
    public void test4() {
        System.out.println("It's method 4 with priority 1");
    }

    @Test(priority = 8)
    public void test2() {
        System.out.println("It's method 2 with priority 8");
    }

    @Test(priority = 1)
    public void test3() {
        System.out.println("It's method 3 with priority 1");
    }

    @Test(priority = 10)
    public void test5() {
        System.out.println("It's method 5 with priority 10");
    }

    @Test(priority = 2)
    public void test7() {
        System.out.println("It's method 7 with priority 2");
    }

    @Test(priority = 9)
    public void test8() {
        System.out.println("It's method 8 with priority 9");
    }
}
