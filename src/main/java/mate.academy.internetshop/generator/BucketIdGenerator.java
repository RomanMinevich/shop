package mate.academy.internetshop.generator;

public class BucketIdGenerator {
    private static Long idGenerator = 0L;

    private BucketIdGenerator() {
    }

    public static Long generateId() {
        return idGenerator++;
    }
}
