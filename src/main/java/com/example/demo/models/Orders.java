package com.example.demo.models;

import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int Qty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "productId")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders(int qty) {
        Qty = qty;
    }
    public Orders() {
    }
}

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
//    @JoinTable(name = "OrdersSklad",
//            joinColumns = @JoinColumn(name = "Ordersid"),
//            inverseJoinColumns = @JoinColumn(name = "Skladid")
//    )
//    private List<Sklad> sklads = new ArrayList<>();


