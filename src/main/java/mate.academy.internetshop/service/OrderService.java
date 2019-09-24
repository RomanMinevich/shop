package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Order;

public interface OrderService {

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    Order delete(Long id);

    Order completeOrder(Long bucketId);

    Order deleteOrder(Long id);
}
