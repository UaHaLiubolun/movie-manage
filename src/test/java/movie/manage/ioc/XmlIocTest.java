package movie.manage.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlIocTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-test.xml");

        MessageService messageService = context.getBean(MessageService.class);
        System.out.println(messageService.getMessage());

    }
}
