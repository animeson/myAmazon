package com.amazon.svistun.entity.production;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "categories", schema = "production")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;
}
