package com.example.multidb.productPost.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_post_id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "post_title")
    private String postTitle;
}
