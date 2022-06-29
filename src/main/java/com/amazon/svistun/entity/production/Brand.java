package com.amazon.svistun.entity.production;

import com.amazon.svistun.entity.sales.OrderItems;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "brands", schema = "production")
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;
    private String brandName;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;


}
