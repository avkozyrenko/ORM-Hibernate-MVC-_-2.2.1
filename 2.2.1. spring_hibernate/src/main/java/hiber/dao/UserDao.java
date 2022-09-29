package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);

   void getUserByCar(String s, int i);
   List<User> getUsers();
}
