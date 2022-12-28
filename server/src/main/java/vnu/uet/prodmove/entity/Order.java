package vnu.uet.prodmove.entity;

import java.time.OffsetDateTime;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "orders")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

    @Id
    @Column(name="ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "orderDate")
    private OffsetDateTime orderDate;


    @Column(name = "soldDate")
    private OffsetDateTime soldDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productID", nullable = false)
    private Product product;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerID")
    @JsonIgnore
    private Customer customer;
}
