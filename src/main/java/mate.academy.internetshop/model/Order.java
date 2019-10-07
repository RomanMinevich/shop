package mate.academy.internetshop.model;

import java.util.List;

public class Order {
    private Long id;
    private final Long userId;
    private final List<Item> items;

    public Order(Long userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
