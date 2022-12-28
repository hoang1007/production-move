package vnu.uet.prodmove.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "factory")
@Getter
@Setter
public class Factory {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 256)
    private String name;

    @Column
    private String address;

    @OneToMany(mappedBy = "factory", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Warehouse> warehouses;

}
