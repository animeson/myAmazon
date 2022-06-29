package com.amazon.svistun.entity.sales;

import com.amazon.svistun.entity.production.Stocks;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "stores", schema = "sales")
@Entity
public class Stores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;
    private String storeName;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String state;
    private String zipCode;


    @OneToMany(mappedBy = "stores", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;

    @OneToMany(mappedBy = "stores", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Staffs> staffs;

    @OneToMany(mappedBy = "stores", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Stocks> stocks;



}
