package vnu.uet.prodmove.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {

    @Id
    @NonNull
    @Column(name = "ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "status", nullable = false, length = 128)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productlineID", nullable = false)
    private Productline productline;

    @OrderBy("startAt ASC")
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<ProductDetail> productDetails;

    @OneToOne(mappedBy = "product", fetch = FetchType.EAGER)
    private Order order;

    @ManyToOne
    @JoinTable(name = "orders", joinColumns = {
            @JoinColumn(name = "productID", referencedColumnName = "ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "customerID", referencedColumnName = "ID") })
    private Customer customer;

    public void addProductDetail(ProductDetail productDetail) {
        productDetails.add(productDetail);
    }
}
