
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by xujiyao on 2017/4/15.
 */
public class TestCase {
    private static Log logger = LogFactory.getLog(TestCase.class);

    ApplicationContext ac;

    @Before
    public void init() {

         ac = new ClassPathXmlApplicationContext("config/spring-web.xml" ,
                "config/spring-mybatis.xml");

        System.out.println(ac);



    }

    @Test
    public void test1() {

            logger.info("asdasdjasiofjioas");

    }





}
