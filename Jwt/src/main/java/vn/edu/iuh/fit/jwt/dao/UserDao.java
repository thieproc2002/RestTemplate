package vn.edu.iuh.fit.jwt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.jwt.entity.Token;
import vn.edu.iuh.fit.jwt.entity.User;

import javax.persistence.EntityManager;

@Repository
public class UserDao {
    private EntityManager manager;

    @Autowired
    public UserDao(EntityManager manager) {
        this.manager = manager;
    }
    @Transactional
    public boolean logIn(Long uid,String password){
        //pass la email
        User user=manager.find(User.class, uid);
        if(user!=null && user.getPassword().equalsIgnoreCase(password)){
            return true;
        }
        return false;
    }


}
