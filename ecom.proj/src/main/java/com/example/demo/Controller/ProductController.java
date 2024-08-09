package com.example.demo.Controller;

import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService;
import com.example.demo.Service.ProductSpecification;
import com.example.demo.exception.exceptionid;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    Product res;
    @Autowired
    private ProductService service;
    @Autowired
    private ProductSpecification<Product> spec;

    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }
    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getproducts();
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getAllProducts(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws exceptionid {
        res=service.getAllProducts(id);
        if(res==null) {
      throw new exceptionid("Invalid");
           // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/prod/")
    public List<Product> getAllProd(@RequestParam(value = "searchString") String name){
        Specification<Product> newi = spec.specif(name);
        return service.getAllProd(newi);


//        if(res==null) {
//            throw new exceptionid("Invalid");
//            // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(res, HttpStatus.OK);

    }
    @PostMapping("/products")
    public void addProducts(@RequestBody Product product){
        service.addProduct(product);
    }

    @PutMapping("/products")
    public void updateProducts(@RequestBody Product product){
        service.updateProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProducts(@PathVariable int id){
        service.deleteProducts(id);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<String> patchProduct(@PathVariable int id, @RequestBody Product product){
        service.patchProduct(id, product);
        return new ResponseEntity<>("Product Updated", HttpStatus.OK);
    }

    @GetMapping("/products/sort")
    public ResponseEntity<Page<Product>> getProductsSort(@RequestParam String brand, Pageable pageable){
        Page<Product> sortpro=service.getProductSort(brand,pageable);
        return ResponseEntity.ok(sortpro);

    }

}
