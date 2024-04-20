package vn.edu.iuh.fit.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.jwt.config.JwtTokenProvider;
import vn.edu.iuh.fit.jwt.dao.TokenDao;
import vn.edu.iuh.fit.jwt.dao.UserDao;
import vn.edu.iuh.fit.jwt.entity.Token;
import vn.edu.iuh.fit.jwt.entity.User;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/jwt")
public class AuthController {
//    @Autowired
    private JwtTokenProvider tokenProvider;
    private UserDao userDao;
    private TokenDao tokenDao;
    @Autowired
    public AuthController(JwtTokenProvider tokenProvider, UserDao userDao, TokenDao tokenDao) {
        this.tokenProvider = tokenProvider;
        this.userDao = userDao;
        this.tokenDao=tokenDao;
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        if (!userDao.logIn(user.getUid(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
        String token = tokenProvider.generateToken(user.getUsername());
        tokenDao.updateToken(new Token(user.getUid(), token));
        return ResponseEntity.ok(token);
    }
    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7); // Bỏ "Bearer " ra khỏi token
        return ResponseEntity.ok(tokenProvider.validateToken(jwtToken));
    }
//    @GetMapping("/token")
//    public List<String> getAllToken() {
//        return tokenDao.getListToken();
//    }
}
