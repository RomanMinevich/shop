package mate.academy.internetshop.model;

import java.util.ArrayList;

import java.util.List;

public class Bucket {
    private final Long id;
    private final List<Item> items;

    public Bucket(Long id) {
        this.id = id;
        this.items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }
}
