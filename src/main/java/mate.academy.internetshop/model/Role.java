package mate.academy.internetshop.model;

public class Role {
    private Long id;
    private final RoleName name;

    public Role(Long id, RoleName name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public RoleName getName() {
        return name;
    }

    public enum RoleName {
     USER, ADMIN;
    }
}
