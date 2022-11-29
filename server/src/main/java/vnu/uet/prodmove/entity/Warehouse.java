package vnu.uet.prodmove.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "warehouse")
@Getter
@Setter
public class Warehouse {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agencyID")
    private Agency agency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factoryID")
    private Factory factory;

    public boolean isAgency() {
        return agency != null && factory == null;
    }

    public boolean isFactory() {
        return agency == null && factory != null;
    }
}
