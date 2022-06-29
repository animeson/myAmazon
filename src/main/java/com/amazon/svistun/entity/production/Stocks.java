package com.amazon.svistun.entity.production;

import com.amazon.svistun.entity.sales.Stores;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "stocks", schema = "production")
@Entity
public class Stocks implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "store_id")
    private Stores stores;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    private Integer quantity;
}
