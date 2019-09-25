package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.generator.BucketIdGenerator;

public class Bucket {
    private final Long id;
    private final Long userId;
    private final List<Item> items;

    public Bucket(Long userId) {
        this.id = BucketIdGenerator.generateId();
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }
}
