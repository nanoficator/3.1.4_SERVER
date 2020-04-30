package crud.service;

import crud.model.User;
import crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        try {
            return userRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public String addUser(User user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Error: passwords do not match!";
        }
        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            return "Error: username exists!";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Success: user " + user.getUsername() + " was added!";
    }

    public String deleteUserById(Long id) {
        User user = userRepository.getOne(id);
        userRepository.deleteById(id);
        return "Success: user " + user.getUsername() + " was deleted!";
    }

    public String changeUser(User user) {
        Long id = user.getId();
        String newUsername = user.getUsername();
        User userFromDbById = userRepository.findById(id).get();
        User userFromDbByUsername = userRepository.findUserByUsername(newUsername);

        if (user.getPassword().equals("") && user.getConfirmPassword().equals("")) {
            user.setPassword(userFromDbById.getPassword());
        } else {
            if (user.getPassword().equals(user.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                return "Error: Passwords do not match!";
            }
        }

        if (userFromDbById == null) {
            return "Error: User does not exist!";
        }

        if (userFromDbByUsername != null && !userFromDbByUsername.getId().equals(id)) {
            return "Error: Username exists!";
        }

        userRepository.save(user);

        return "User " + user.getUsername() + " was changed";
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(s);
    }
}