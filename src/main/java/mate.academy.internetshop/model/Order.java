package mate.academy.internetshop.model;

import java.util.List;
import mate.academy.internetshop.generator.OrderIdGenerator;

public class Order {
    private final Long id;
    private final Long userId;
    private final List<Item> items;

    public Order(List<Item> items, Long bucketId) {
        this.id = OrderIdGenerator.generateId();
        this.userId = bucketId;
        this.items = items;
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

    @Override
    public String toString() {
        return "Order{id=" + id + ",userId=" + userId + '}';
    }
}
