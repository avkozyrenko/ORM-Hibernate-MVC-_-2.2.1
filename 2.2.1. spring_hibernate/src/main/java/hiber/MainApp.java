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

      User user1 = new User("Nick", "Luck", "nick@mail.ru");
      User user2 = new User("Max", "Leaf", "max@mail.ru");
      User user3 = new User("John", "Show", "john@mail.ru");

      Car car1 = new Car("Lada", 4);
      Car car2 = new Car("Mazda", 3);
      Car car3 = new Car("BMW", 7);


      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      userService.getUserByCar("Mazda", 3);
      userService.getUserByCar("BMW", 6);
      userService.getUserByCar("BMW", 7);

      context.close();
   }
}
