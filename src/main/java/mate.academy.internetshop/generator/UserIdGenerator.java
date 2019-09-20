package mate.academy.internetshop.generator;

public class UserIdGenerator {
    private static Long idGenerator = 0L;

    private UserIdGenerator() {
    }

    public static Long generateId() {
        return idGenerator++;
    }
}
