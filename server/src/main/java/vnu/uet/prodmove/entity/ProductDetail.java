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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
    private OffsetDateTime startAt;

    @Column(name = "endAt")
    @Transient
    private OffsetDateTime endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID", referencedColumnName = "ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @Transient
    @JoinColumn(name = "warrantyCenterID")
    private WarrantyCenter warrantyCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouseID")
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factoryID")
    private Factory factory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agencyID")
    private Agency agency;

    /**
     * Kiểm tra xem trạng thái đã hoàn thành hay chưa.
     * @return true nếu đã hoàn thành, false nếu chưa hoàn thành.
     */
    public boolean completed() {
        return this.endAt != null;
    }

    /**
     * Đánh dấu trạng thái đã hoàn thành.
     * Cập nhật trường {@value #endAt} thành thời gian hiện tại.
     */
    public void markCompleted() {
        this.endAt = OffsetDateTime.now();
    }

    /**
     * Đánh dấu trạng thái chưa hoàn thành.
     * Cập nhật trường {@value #endAt} thành null.
     */
    public void markUncompleted() {
        this.endAt = null;
    }

}