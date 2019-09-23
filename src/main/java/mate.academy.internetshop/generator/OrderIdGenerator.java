package mate.academy.internetshop.generator;

public class OrderIdGenerator {
    private static Long idGenerator = 0L;

    private OrderIdGenerator() {
    }

    public static Long generateId() {
        return idGenerator++;
    }
}
