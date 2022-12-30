package vnu.uet.prodmove.entity;

import java.time.LocalDateTime;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.utils.converter.db.ProductStageConverter;

@Entity
@Table(name = "productdetail")
@Getter
@Setter
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Builder(toBuilder = true)
public class ProductDetail {

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stage", nullable = false)
    @Convert(converter = ProductStageConverter.class)
    private ProductStage stage;

    @Column(name = "startAt", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "endAt")
    private LocalDateTime endAt;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productID", referencedColumnName = "ID")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warrantyCenterID")
    private WarrantyCenter warrantyCenter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouseID")
    @JsonIgnore
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerID")
    @JsonIgnore
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "factoryID")
    @JsonIgnore
    private Factory factory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agencyID")
    @JsonIgnore
    private Agency agency;

    public ProductDetail() {
        this.startAt = LocalDateTime.now();
    }

    /**
     * Kiểm tra xem trạng thái đã hoàn thành hay chưa.
     * 
     * @return true nếu đã hoàn thành, false nếu chưa hoàn thành.
     */
    public boolean completed() {
        return this.endAt != null;
    }

    /**
     * Đánh dấu trạng thái đã hoàn thành.
     * Cập nhật trường {@value #end_at} thành thời gian hiện tại.
     */
    public void markCompleted() {
        this.endAt = LocalDateTime.now();
    }

    /**
     * Đánh dấu trạng thái chưa hoàn thành.
     * Cập nhật trường {@value #end_at} thành null.
     */
    public void markUncompleted() {
        this.endAt = null;
    }

    public void copyForeignKey(ProductDetail another) {
        this.setProduct(another.getProduct());
        this.setWarehouse(another.getWarehouse());
        this.setWarrantyCenter(another.getWarrantyCenter());
        this.setFactory(another.getFactory());
        this.setAgency(another.getAgency());
    }

    public ProductDetail clone() {
        ProductDetail clone = new ProductDetail();
        clone.setStage(this.getStage());
        clone.copyForeignKey(this);
        return clone;
    }
}