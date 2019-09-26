package mate.academy.internetshop.generator;

public class RoleIdGenerator {
    private static Long idGenerator = 0L;

    private RoleIdGenerator() {
    }

    public static Long generateId() {
        return idGenerator++;
    }
}

