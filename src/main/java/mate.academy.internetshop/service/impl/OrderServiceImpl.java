package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserDao userDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public Order delete(Long id) {
        return orderDao.delete(id);
    }

    @Override
    public Order completeOrder(Long bucketId) {
        Order order = new Order(bucketService.addItemsToOrder(bucketId), bucketId);
        create(order);
        User user = userDao.get(bucketId);
        user.getOrders().add(order);
        userDao.update(user);
        return order;
    }

    @Override
    public Order deleteOrder(Long id) {
        Order order = get(id);
        User user = userDao.get(order.getUserId());
        user.getOrders().remove(order);
        userDao.update(user);
        delete(id);
        return order;
    }
}
