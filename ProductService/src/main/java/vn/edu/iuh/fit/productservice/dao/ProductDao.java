package vn.edu.iuh.fit.productservice.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.productservice.entity.Product;

import java.util.List;

@Repository
public class ProductDao {
    private EntityManager manager;
    @Autowired
    public ProductDao(EntityManager manager) {
        this.manager = manager;
    }
    public Product getProduct(long id){
        try{
            return manager.find(Product.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Product> getListProduct(){
        try{
            return manager.createQuery("select p from Product p", Product.class).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Transactional
    public boolean addProduct(Product product){
        try{
            long totalProduct= (long) manager.createQuery("select count(p) from Product p").getSingleResult();
            product.setPid(totalProduct+1);
            manager.persist(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public boolean updateProduct(Product product){
        try{
            manager.merge(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
