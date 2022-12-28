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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "warehouse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Builder(toBuilder = true)
public class Warehouse {

    @Id
    @Column(name="ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="address", length = 500)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agencyID")
    @JsonIgnore
    private Agency agency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "factoryID")
    @JsonIgnore
    private Factory factory;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER)
    @OrderBy("startAt DESC")
    @JsonIgnore
    private Set<ProductDetail> productdetails;
    
    public boolean isAgency() {
        return agency != null && factory == null;
    }

    public boolean isFactory() {
        return agency == null && factory != null;
    }
}
