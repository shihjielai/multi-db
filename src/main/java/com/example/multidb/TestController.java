package com.example.multidb;

import com.example.multidb.blog.entity.Post;
import com.example.multidb.blog.repository.PostRepository;
import com.example.multidb.mall.entity.Product;
import com.example.multidb.mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    private final ProductRepository productRepository;
    private final PostRepository postRepository;

    @Autowired
    public TestController(ProductRepository productRepository, PostRepository postRepository) {
        this.productRepository = productRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/get/{id}")
    public String post(@PathVariable Long id) {

        Optional<Product> product = productRepository.findById(id);
        Optional<Post> post = postRepository.findById(id);

        return product.get().getProductName() + post.get().getTitle();
    }
}
