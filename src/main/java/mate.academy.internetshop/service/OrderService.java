package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;

public interface OrderService {

    Order complete(Long userId, List<Item> items);

    Order get(Long id);

    Order update(Order order);

    Order delete(Long id);
}
