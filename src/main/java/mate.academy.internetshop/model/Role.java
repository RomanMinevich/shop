package mate.academy.internetshop.model;

import java.util.Objects;

public class Role {
    private final Long id;
    private final String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
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
        return name;
    }
}
