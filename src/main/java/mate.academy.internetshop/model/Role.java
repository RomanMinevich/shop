package mate.academy.internetshop.model;

import mate.academy.internetshop.generator.RoleIdGenerator;

public class Role {
    private final Long roleId;
    private final RoleName roleName;

    public Role(String roleName) {
        this.roleId = RoleIdGenerator.generateId();
        this.roleName = RoleName.valueOf(roleName);
    }

    public Long getRoleId() {
        return roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public enum RoleName {
        USER, ADMIN
    }
}
