package vnu.uet.prodmove.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @Column(name="ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "orderDate", nullable = false)
    @Transient
    private LocalDate orderDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID", nullable = false)
    private Customer customer;
}
