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
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.utils.converter.db.ProductStageConverter;

@Entity
@Table(name = "productdetail")
@Getter
@Setter
public class Productdetail {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Convert(converter = ProductStageConverter.class)
    private ProductStage stage;

    @Column(nullable = false, columnDefinition = "longtext")
    private String detail;

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