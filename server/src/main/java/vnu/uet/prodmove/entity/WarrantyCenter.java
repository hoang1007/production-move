package vnu.uet.prodmove.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "warranty_center")
@Getter
@Setter
public class WarrantyCenter {

    @Id
    @Column(name="ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", length = 256)
    private String name;

    @Column(name="address", length = 45)
    private String address;

    @OneToMany(mappedBy="warrantyCenter")
    private Set<ProductDetail> productDetails;

}
