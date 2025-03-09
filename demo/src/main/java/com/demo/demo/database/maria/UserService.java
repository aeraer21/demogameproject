package com.demo.demo.database.maria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByMId(String id) {
        return userRepository.findByMId(id);
    }
    public boolean checkPassword(User user, String rawPassword) {
        return Objects.equals(rawPassword, user.getMPw());
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
