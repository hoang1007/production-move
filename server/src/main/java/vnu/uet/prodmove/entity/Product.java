package vnu.uet.prodmove.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;
import vnu.uet.prodmove.custom.CustomProductlineSerializer;


@Entity
@Getter
@Setter
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productlineID")
public class Product {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 128)
    private String status;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Order> productOrders;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productlineID", nullable = false)
    // @JsonSerialize(using= CustomProductlineSerializer.class)
    @JsonManagedReference
    private Productline productline;
    
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Productdetail> productDetails;

}
