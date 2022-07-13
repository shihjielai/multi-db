package com.example.multidb.productPost.repository;

import com.example.multidb.productPost.entity.ProductPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPostRepository extends JpaRepository<ProductPost, Integer> {
}
