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
@Table(name = "customer")
@Getter
@Setter
public class Customer {

    @Id
    @Column(name="ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="fullname", length = 85)
    private String fullname;

    @Column(name="phoneNumber", unique = true, length = 45)
    private String phoneNumber;

    @Column(name="email", unique = true, length = 45)
    private String email;

    @OneToMany(mappedBy = "customer")
    private Set<Order> customerOrders;

}
