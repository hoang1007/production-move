package vnu.uet.prodmove.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.utils.converter.db.ProductStageConverter;

@Entity
@Table(name = "productdetail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Builder(toBuilder=true)
public class ProductDetail {

    @Id
    @Column(name="ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Convert(converter = ProductStageConverter.class)
    private ProductStage stage;

    @Column(name= "startAt", nullable = false)
    @Transient
    private LocalDate startAt;

    @Column(name = "end_at")
    private LocalDate end_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productID", referencedColumnName = "ID")
    private Product product;

    @ManyToOne
    @Transient
    @JoinColumn(name = "warrantyCenterID")
    @JsonIgnore
    private WarrantyCenter warrantyCenter;
    
    @ManyToOne
    @JoinColumn(name = "warehouseID")
    @JsonIgnore
    private Warehouse warehouse;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerID")
    @JsonIgnore
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "factoryID")
    @JsonIgnore
    private Factory factory;
    
    @ManyToOne
    @JoinColumn(name = "agencyID")
    @JsonIgnore
    private Agency agency;

    /**
     * Kiểm tra xem trạng thái đã hoàn thành hay chưa.
     * @return true nếu đã hoàn thành, false nếu chưa hoàn thành.
     */
    public boolean completed() {
        return this.end_at != null;
    }

    /**
     * Đánh dấu trạng thái đã hoàn thành.
     * Cập nhật trường {@value #end_at} thành thời gian hiện tại.
     */
    public void markCompleted() {
        this.end_at = LocalDate.now();
    }

    /**
     * Đánh dấu trạng thái chưa hoàn thành.
     * Cập nhật trường {@value #end_at} thành null.
     */
    public void markUncompleted() {
        this.end_at = null;
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