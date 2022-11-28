package vnu.uet.prodmove.entity;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import vnu.uet.prodmove.utils.converter.db.ProductStageTypeConverter;
import vnu.uet.prodmove.utils.productStage.ProductStageType;


@Entity
@Getter
@Setter
public class Productdetail {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Convert(converter = ProductStageTypeConverter.class)
    private ProductStageType stage;

    @Column(name= "startAt", nullable = false)
    @Transient
    private OffsetDateTime startAt;

    @Column(name = "endAt")
    @Transient
    private OffsetDateTime endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warrantyCenterID")
    private WarrantyCenter warrantyCenter;

    @ManyToOne
    @JoinColumn(name = "warehouseID")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;
}