package vn.edu.iuh.fit.userservice.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.userservice.entity.User;

import java.util.List;

@Repository
public class UserDao {
    private EntityManager manager;

    @Autowired
    public UserDao(EntityManager manager) {
        this.manager = manager;
    }
    public User getUser(long id){
        try{
            return manager.find(User.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<User> getListUser(){
        try{
            return manager.createQuery("select u from User u", User.class).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
