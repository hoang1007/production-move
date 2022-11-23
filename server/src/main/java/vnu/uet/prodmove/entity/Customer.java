package vnu.uet.prodmove.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Customer {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 85)
    private String fullname;

    @Column(unique = true, length = 45)
    private String phoneNumber;

    @Column(unique = true, length = 45)
    private String email;

    @OneToMany(mappedBy = "customer")
    private Set<Order> customerOrders;

}
