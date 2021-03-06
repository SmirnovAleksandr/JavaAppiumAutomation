import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends  MainClass{

    private int testGetLocalNumber;

    @Test
    public  void testGetClassString(){
        boolean isString;
        String result = getClassString();
        Assert.assertTrue("метод getClassNumber не возвращает строку, в которой есть подстрока “hello” или “Hello”", result.contains("Hello") || result.contains("Hello"));
    }

    @Test
    public void testGetClassNumber(){

            Assert.assertTrue("метод MainClass.getClassNumber() returns number less than 45",getClassNumber()>45 );
    }
    @Test
    public void checkGetLocalNumber(){

        MainClass mainClass = new MainClass();
        testGetLocalNumber = mainClass.getLocalNumber();
        if(testGetLocalNumber != 14){

            Assert.assertTrue("MainClass.getLocalNumber() doesn't return 14",testGetLocalNumber != 14);
        }
    }
}
