package be.pxl.weatheralert.service;

import be.pxl.weatheralert.domain.User;
import be.pxl.weatheralert.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        // Additional processing logic
        return userRepository.save(user);
    }
}
