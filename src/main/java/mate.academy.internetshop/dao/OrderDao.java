package mate.academy.internetshop.dao;

import java.util.List;
import mate.academy.internetshop.model.Order;

public interface OrderDao {

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    Order delete(Long id);

    List<Order> getUserOrders(Long userId);
}
