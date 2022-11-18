package vnu.uet.prodmove.domain;

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
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Product {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 128)
    private String status;

    @OneToMany(mappedBy = "product")
    private Set<Order> productOrders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productline_id", nullable = false)
    private Productline productline;

    @OneToMany(mappedBy = "productdetail")
    private Set<Productdetail> productdetailProductdetails;

}
