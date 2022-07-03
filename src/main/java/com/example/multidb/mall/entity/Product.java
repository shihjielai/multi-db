package com.example.multidb.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String category;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    private Long price;

    @Column(name = "product_name")
    private String productName;

    private Long stock;

    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(name = "last_modified_time")
    private LocalDateTime lastModifiedTime;
}
