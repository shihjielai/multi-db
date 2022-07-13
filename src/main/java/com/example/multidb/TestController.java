package com.example.multidb;

import com.example.multidb.blog.entity.Post;
import com.example.multidb.blog.repository.PostRepository;
import com.example.multidb.mall.entity.Product;
import com.example.multidb.mall.repository.ProductRepository;
import com.example.multidb.productPost.entity.ProductPost;
import com.example.multidb.productPost.repository.ProductPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    private final ProductRepository productRepository;
    private final PostRepository postRepository;
    private final ProductPostRepository productPostRepository;

    @Autowired
    public TestController(ProductRepository productRepository, PostRepository postRepository, ProductPostRepository productPostRepository) {
        this.productRepository = productRepository;
        this.postRepository = postRepository;
        this.productPostRepository = productPostRepository;
    }

    @GetMapping("/get/{id}")
    public String post(@PathVariable Long id) {

        Optional<Product> product = productRepository.findById(id);
        Optional<Post> post = postRepository.findById(id);

        if (product.isEmpty()) {
            return "product with id: " + id + " does not exist";
        } else if (post.isEmpty()) {
            return "Post with id: " + id + " does not exist";
        } else {
            ProductPost productPost = new ProductPost();
            productPost.setProductName(product.get().getProductName());
            productPost.setPostTitle(post.get().getTitle());
            productPostRepository.save(productPost);
            return product.get().getProductName() + post.get().getTitle() + productPost.getPostTitle();
        }
    }
}
