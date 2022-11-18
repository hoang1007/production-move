package vnu.uet.prodmove.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Account {

    @Id
    @Column(nullable = false, updatable = false, length = 128)
    private String username;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(nullable = false, name = "role", length = 45)
    private String role;

}
