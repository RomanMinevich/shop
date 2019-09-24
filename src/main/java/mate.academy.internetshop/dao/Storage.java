package mate.academy.internetshop.dao;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public class Storage {
    public static final List<Item> items;
    public static final List<Bucket> buckets;
    public static final List<Order> orders;
    public static final List<User> users;

    static {
        items = new ArrayList<>();
        buckets = new ArrayList<>();
        orders = new ArrayList<>();
        users = new ArrayList<>();

        Item salt = new Item("Salt", 300D);
        items.add(salt);
        Item spice = new Item("Spice", 250D);
        items.add(spice);
        Item mushrooms = new Item("Mushrooms", 400D);
        items.add(mushrooms);
        Item herbs = new Item("Herbs", 200D);
        items.add(herbs);

        /*User user = new User();
        users.add(user);

        Bucket bucket = new Bucket(user.getId());
        buckets.add(bucket)*/
    }

}
