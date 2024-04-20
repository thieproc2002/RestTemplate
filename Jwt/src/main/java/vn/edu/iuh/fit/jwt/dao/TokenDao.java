package vn.edu.iuh.fit.jwt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.jwt.entity.Token;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TokenDao {
    private EntityManager manager;
    @Autowired
    public TokenDao(EntityManager manager) {
        this.manager = manager;
    }
    @Transactional
    public boolean updateToken(Token token){
        try{
            manager.merge(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public List<String> getListToken(){
        try{
            List<String> tokenString=new ArrayList<>();
            List<Token> tokens= manager.createQuery("select t from Token t", Token.class).getResultList();
            for (Token token:tokens) {
                tokenString.add(token.getToken());
            }
            return tokenString;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
