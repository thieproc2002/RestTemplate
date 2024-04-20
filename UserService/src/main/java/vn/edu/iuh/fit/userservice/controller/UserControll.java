package vn.edu.iuh.fit.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.userservice.dao.UserDao;
import vn.edu.iuh.fit.userservice.entity.User;

import java.util.List;

@RestController
@RequestMapping("/user-service")
public class UserControll {
    private UserDao userDao;
    @Autowired
    public UserControll(UserDao userDao) {
        this.userDao = userDao;
    }
    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userDao.getUser(userId);
    }
    @GetMapping("/user")
    public List<User> getAllUser() {
        return userDao.getListUser();
    }
}
