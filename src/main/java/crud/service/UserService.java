package crud.service;

import crud.model.User;
import crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    public String addNewUser(User user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Error: passwords do not match!";
        }
        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            return "Error: username exists!";
        }
        userRepository.save(user);
        return "Success: user " + user.getUsername() + " was added!";
    }

    public String deleteUserById(Long id) {
        User user = userRepository.getOne(id);
        userRepository.deleteById(id);
        return "Success: user " + user.getUsername() + " was deleted!";
    }



}