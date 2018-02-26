import model.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.transaction.BeforeTransaction;
import service.UserService;
import org.junit.Before;

/**
 * Created by shilc on 2016/2/22.
 */
public class UserTest {
    private UserService userService;

    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});
        userService = (UserService) context.getBean("userServiceImpl");
    }

    @Test
    public void addUser()
    {
        User user=new User();
        user.setCode("1");
        user.setName("test");
        System.out.println(userService.InsertUser(user));
        System.out.println("12345");
    }
}
