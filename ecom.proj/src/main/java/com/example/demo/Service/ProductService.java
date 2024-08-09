package com.example.demo.Service;

import com.example.demo.Model.Product;
import com.example.demo.Repository.ProductRepo;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;


    Product res;


    public Product getAllProducts(int id ) {

        return repo.findById(id).orElse(null);
    }

    public List<Product> getproducts() {
        return repo.findAll();
    }

    public void addProduct(Product product) {
        repo.save(product);
    }

    public void updateProduct(Product product) {
        repo.save(product);
    }

    public void deleteProducts(int id) {
        repo.deleteById(id);
    }



    public List<Product> getAllProd(Specification<Product> newi) {
        return repo.findAll(newi);
    }

    public void patchProduct(int id, Product product) {
        Optional<Product> pro = repo.findById(id);
        if(product.getName() != null)
        {
            pro.get().setName(product.getName());
        }
        if(product.getBrand() != null)
        {
            pro.get().setBrand(product.getBrand());
        }
        if(product.getCategory() != null)
        {
            pro.get().setCategory(product.getCategory());
        }
        if(product.getDesc() != null)
        {
            pro.get().setDesc(product.getDesc());
        }
        if(product.getPrice() != null)
        {
            pro.get().setPrice(product.getPrice());
        }

        if(product.getQuantity() < 0)
        {
            pro.get().setQuantity(product.getQuantity());
        }
        if(product.isAvailable() || !product.isAvailable())
        {
            if(product.isAvailable()) {
                pro.get().setAvailable(!product.isAvailable());
            }
            if(!product.isAvailable()) {
                pro.get().setAvailable(product.isAvailable());
            }
        }
        if(product.getReleaseDate()!=null){
            pro.get().setReleaseDate(product.getReleaseDate());
        }
        repo.save(pro.get());

    }



    public Page<Product> getProductSort(String brand, Pageable pageable) {
        Page<Product> sortpro = repo.findBybrandContainingIgnoreCase(brand, pageable);
        return sortpro;
    }


}
