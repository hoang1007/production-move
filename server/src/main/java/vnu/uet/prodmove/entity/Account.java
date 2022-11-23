package vnu.uet.prodmove.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import vnu.uet.prodmove.config.UserRole;
import vnu.uet.prodmove.utils.converter.db.UserRoleConverter;


@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
    @Column(name="username", nullable = false, updatable = false, length = 128)
    private String username;

    @Column(name="password", nullable = false, length = 128)
    @JsonIgnore
    private String password;

    @Column(nullable = false, name = "role", length = 45)
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

}
