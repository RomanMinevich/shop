package mate.academy.internetshop;

import java.util.List;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class Main {
    @Inject
    private static ItemService itemService;
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static OrderService orderService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Item salt = new Item("Salt", 300D);
        itemService.create(salt);
        Item spice = new Item("Spice", 250D);
        itemService.create(spice);
        Item mushrooms = new Item("Mushrooms", 400D);
        itemService.create(mushrooms);
        Item herbs = new Item("Herbs", 200D);
        itemService.create(herbs);

        User andrew = userService.create(new User());
        Bucket andrewBucket1 = bucketService.create(new Bucket(andrew.getId()));

        bucketService.addItem(andrewBucket1.getId(), salt.getId());
        bucketService.addItem(andrewBucket1.getId(), spice.getId());
        bucketService.addItem(andrewBucket1.getId(), mushrooms.getId());

        User sophie = userService.create(new User());
        Bucket sophieBucket1 = bucketService.create(new Bucket(sophie.getId()));

        bucketService.delete(sophieBucket1.getId());
        Bucket sophieBucket2 = bucketService.create(new Bucket(sophie.getId()));
        bucketService.addItem(sophieBucket2.getId(), mushrooms.getId());
        bucketService.addItem(sophieBucket2.getId(), herbs.getId());

        Order andrewOrder1 = orderService.completeOrder(
                bucketService.getAllItems(andrewBucket1.getId()), andrewBucket1.getUserId());
        orderService.create(andrewOrder1);

        Order sophieOrder1 = orderService.completeOrder(
                bucketService.getAllItems(sophieBucket2.getId()), sophieBucket2.getUserId());
        orderService.create(sophieOrder1);

        bucketService.addItem(andrewBucket1.getId(), herbs.getId());
        bucketService.addItem(andrewBucket1.getId(), herbs.getId());

        bucketService.addItem(sophieBucket2.getId(), mushrooms.getId());
        bucketService.addItem(sophieBucket2.getId(), herbs.getId());

        Order andrewOrder2 = orderService.completeOrder(
                bucketService.getAllItems(andrewBucket1.getId()), andrewBucket1.getUserId());
        orderService.create(andrewOrder2);

        List<Order> andrewOrders = userService.getOrders(andrew.getId());
        andrewOrders
                .stream()
                .peek(System.out::println)
                .map(Order::getItems)
                .forEach(System.out::println);

        System.out.println();

        List<Order> sophieOrders = userService.getOrders(sophie.getId());
        sophieOrders
                .stream()
                .peek(System.out::println)
                .map(Order::getItems)
                .forEach(System.out::println);
    }
}
