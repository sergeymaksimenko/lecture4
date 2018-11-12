import org.testng.annotations.*;

public class TestNGTest {

    @BeforeTest
    public void setUp() {
        System.out.println("Before Test execution ");
    }

    @BeforeMethod
    public void prepareEnvironment() {
        System.out.println("Before every test method");
    }

    @AfterMethod
    public void clearEnvironment() {
        System.out.println("After every test method ");
    }

    @Test
    public void testCase1() {
        System.out.println("This is my Test Case 1");
    }

    @Test
    public void testCase2() {
        System.out.println("This is my Test Case 2");
    }

    @AfterTest
    public void tearDown() {
        System.out.println("After Test execution");
    }

}
