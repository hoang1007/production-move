package vnu.uet.prodmove.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import vnu.uet.prodmove.enums.UserRole;
import vnu.uet.prodmove.utils.converter.db.UserRoleConverter;

@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
    @Column(name = "username", nullable = false, updatable = false, length = 128)
    private String username;

    @Column(name = "password", nullable = false, length = 128)
    @JsonIgnore
    private String password;

    @Column(nullable = false, name = "role", length = 45)
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agencyID")
    private Agency agency;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "factoryID")
    private Factory factory;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warrantyID")
    private WarrantyCenter warrantyCenter;

    public boolean isAgency() {
        return role.toString().equalsIgnoreCase(UserRole.AGENCY.toString());
    }

    public boolean isFactory() {
        return role.toString().equalsIgnoreCase(UserRole.FACTORY.toString());
    }

    public boolean isWarranty() {
        return role.toString().equalsIgnoreCase(UserRole.WARRANTY.toString());
    }

    public Integer getIdUser() {
        if (this.isAgency()) {
            return this.getAgency().getId();
        } else if (this.isFactory()) {
            return this.getFactory().getId();
        } else if (this.isWarranty()) {
            return this.getWarrantyCenter().getId();
        } else
            return -1;
    }

}
