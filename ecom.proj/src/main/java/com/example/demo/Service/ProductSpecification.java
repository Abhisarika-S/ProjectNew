package com.example.demo.Service;

import com.example.demo.Model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSpecification<T> {
    public Specification<T> specif(String name) {
        return (root,query,criteriaBuilder)->
        {
//            List<Predicate> predicates = new ArrayList<>();
            Predicate Like = criteriaBuilder.like(root.get("name"), "%"+name+"%");
//            predicates.add(Like);
            System.out.printf("Output"+Like);
            return Like;
        };

    }
}
