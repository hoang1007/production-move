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
    @Column(name="username", nullable = false, updatable = false, length = 128)
    private String username;

    @Column(name="password", nullable = false, length = 128)
    private String password;

    @Column(nullable = false, name = "role", length = 45)
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agencyID", referencedColumnName="ID")
    @JsonIgnore
    private Agency agency;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "factoryID", referencedColumnName = "ID")
    // @JsonIgnore
    private Factory factory;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warrantyID")
    @JsonIgnore
    private WarrantyCenter warrantyCenter;

    @JsonIgnore
    public boolean isAgency() {
        return role.toString().equalsIgnoreCase(UserRole.AGENCY.toString());
    }
    
    @JsonIgnore
    public boolean isFactory() {
        return role.toString().equalsIgnoreCase(UserRole.FACTORY.toString());
    }
    
    @JsonIgnore
    public boolean isWarranty() {
        return role.toString().equalsIgnoreCase(UserRole.WARRANTY.toString());
    }

    @JsonIgnore
    public Object getUser() {
        if (this.isAgency()) {
            return this.getAgency();
        } else if (this.isFactory()) {
            return this.getFactory();
        } else if (this.isWarranty()) {
            return this.getWarrantyCenter();
        } else
            return null;
    }

}
