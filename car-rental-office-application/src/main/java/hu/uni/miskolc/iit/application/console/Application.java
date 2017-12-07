package hu.uni.miskolc.iit.application.console;

import hu.uni.miskolc.iit.controller.UserManagementController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Lenovo11 on 2017. 12. 07..
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConsoleConfig.class);
        UserManagementController userManagementController = (UserManagementController)context.getBean("userManagementController");
        System.out.println(userManagementController.getAllUser().getBody());
    }

}
