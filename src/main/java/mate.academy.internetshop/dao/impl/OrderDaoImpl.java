package mate.academy.internetshop.dao.impl;

import static java.util.stream.IntStream.range;

import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        if (Storage.orders
                .stream()
                .anyMatch(element -> element.getId().equals(order.getId()))) {
            throw new IllegalArgumentException("Can't add order with id " + order.getId());
        }
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Order get(Long id) {
        return Storage.orders
                .stream()
                .filter(element -> element.getId().equals(id))
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find order with id " + id));
    }

    @Override
    public Order update(Order order) {
        int index = range(0, Storage.orders.size())
                .filter(element -> Storage.orders.get(element).getId().equals(order.getId()))
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find order with id " + order.getId()));
        Storage.orders.set(index, order);
        return order;
    }

    @Override
    public Order delete(Long id) {
        Order order = get(id);
        Storage.orders.removeIf(element -> element.equals(order));
        return order;
    }
}
