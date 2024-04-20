package vn.edu.iuh.fit.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.productservice.dao.ProductDao;
import vn.edu.iuh.fit.productservice.entity.Order;
import vn.edu.iuh.fit.productservice.entity.Product;
import vn.edu.iuh.fit.productservice.entity.User;

@RestController
@RequestMapping("/product-service")
public class ProductControll {
    private RestTemplate restTemplate;
    private ProductDao productDao;
    @Autowired
    public ProductControll(RestTemplate restTemplate, ProductDao productDao) {
        this.restTemplate = restTemplate;
        this.productDao = productDao;
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId, @RequestHeader("Authorization") String token) {
        // Tạo header chứa thông tin xác thực
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                "http://localhost:8003/jwt/validate-token",
                HttpMethod.GET,
                requestEntity,
                Boolean.class
        );
        if(Boolean.FALSE.equals(responseEntity.getBody())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không hợp lệ");
        }
        return ResponseEntity.ok(productDao.getProduct(productId));
    }
    @GetMapping("/order/{userId}/{productId}")
    public ResponseEntity<?> getUserProductById(@PathVariable Long userId, @PathVariable Long productId, @RequestHeader("Authorization") String token) {
        // Tạo header chứa thông tin xác thực
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                "http://localhost:8003/jwt/validate-token",
                HttpMethod.GET,
                requestEntity,
                Boolean.class
        );
        if(Boolean.FALSE.equals(responseEntity.getBody())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không hợp lệ");
        }
        Product product=productDao.getProduct(productId);
        User user = restTemplate.getForObject("http://localhost:8001/user-service/user/"+userId, User.class);
        if(user!= null){
            Order order=new Order(1, user.getUsername(), product.getProductname(), product.getPrice());
            return ResponseEntity.ok(order);
        }else {
            return null;
        }

    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product, @RequestHeader("Authorization") String token) {
        // Tạo header chứa thông tin xác thực
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                "http://localhost:8003/jwt/validate-token",
                HttpMethod.GET,
                requestEntity,
                Boolean.class
        );
        if(Boolean.FALSE.equals(responseEntity.getBody())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không hợp lệ");
        }
        productDao.addProduct(product);
        return ResponseEntity.ok(product);
    }
}
