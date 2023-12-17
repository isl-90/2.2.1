package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User ivan = new User("Ivan", "Ivanov", "ivan@mail.ru");
        Car audi = new Car("Audi", 6);
        ivan.setCar(audi);


        User petr = new User("Petr", "Petrov", "petr@mail.ru");
        Car bmw = new Car("BMW", 3);
        petr.setCar(bmw);


        User anna = new User("Anna", "Pavlova", "anna@mail.ru");
        Car mercedes = new Car("Mercedes", 600);
        anna.setCar(mercedes);


        User skot = new User("Skot", "White", "skot@mail.ru");
        Car volvo = new Car("Volvo", 90);
        skot.setCar(volvo);

        userService.add(ivan);
        userService.add(petr);
        userService.add(anna);
        userService.add(skot);

        userService.getUserByCarModel("BMW");

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        context.close();
    }
}
