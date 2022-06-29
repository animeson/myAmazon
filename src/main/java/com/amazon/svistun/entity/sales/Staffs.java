package com.amazon.svistun.entity.sales;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "staffs", schema = "sales")
@Entity
public class Staffs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Byte active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "store_id")
    private Stores stores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "manager_id")
    private Staffs staffs;

    @OneToMany(mappedBy = "staffs", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Staffs> staffsList;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;


}
