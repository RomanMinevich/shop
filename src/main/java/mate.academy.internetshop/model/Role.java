package mate.academy.internetshop.model;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Enumerated(STRING)
    private RoleName name;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = RoleName.valueOf(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(String name) {
        this.name = RoleName.valueOf(name);
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
        return name.toString();
    }

    public enum RoleName {
        USER, ADMIN
    }
}
