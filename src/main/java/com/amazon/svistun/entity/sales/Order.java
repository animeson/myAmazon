package com.amazon.svistun.entity.sales;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Table(name = "orders", schema = "sales")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    /*	-- Order status:
    1 = Pending;
    2 = Processing;
    3 = Rejected;
    4 = Completed*/
    private Byte orderStatus;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "store_id")
    private Stores stores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "staff_id")
    private Staffs staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;



    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItems> orders;


}
