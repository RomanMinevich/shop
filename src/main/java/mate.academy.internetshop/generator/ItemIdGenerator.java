package mate.academy.internetshop.generator;

public class ItemIdGenerator {
    private static Long idGenerator = 0L;

    private ItemIdGenerator() {
    }

    public static Long generateId() {
        return idGenerator++;
    }
}
