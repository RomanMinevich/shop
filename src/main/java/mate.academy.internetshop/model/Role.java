package mate.academy.internetshop.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR", name = "name")
    private RoleName roleName;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.roleName = RoleName.valueOf(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(String name) {
        this.roleName =  RoleName.valueOf(name);;
    }

    @Override
    public boolean equals(Object role) {
        if (role instanceof Role) {
            return id.equals(((Role)role).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return roleName.toString();
    }

    public enum RoleName {
        USER, ADMIN
    }
}
