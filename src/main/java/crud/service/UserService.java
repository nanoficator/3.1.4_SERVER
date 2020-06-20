package crud.service;

import crud.model.User;

import java.util.Collection;

public interface UserService {

    Collection<User> getAllUsers();

    User getUserByUsername(String username);

    String addUser(User user);

    String deleteUserById(Long id);

    String editUser(User user);

}
