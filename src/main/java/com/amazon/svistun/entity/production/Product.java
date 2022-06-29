package com.amazon.svistun.entity.production;

import com.amazon.svistun.entity.sales.OrderItems;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "products", schema = "production")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,
            name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "category_id")
    private Category category;

    private Integer modelYear;
    private Double listPrice;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItems> orderItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Stocks> stocks;
}
