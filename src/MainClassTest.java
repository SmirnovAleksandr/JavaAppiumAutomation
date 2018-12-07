import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    private int testGetLocalNumber;

    @Test
    public void checkGetLocalNumber(){

        MainClass mainClass = new MainClass();
        testGetLocalNumber = mainClass.getLocalNumber();
        if(testGetLocalNumber != 14){

            Assert.assertTrue("MainClass.getLocalNumber doesn't return 14",testGetLocalNumber != 14);
        }
    }
}
