package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao implements OrderDao {
    private static final Logger log = Logger.getLogger(OrderDaoJdbcImpl.class);

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO orders (user_id) VALUE (?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
        } catch (SQLException exception) {
            log.error("Couldn't create an order");
        }
        addItems(order);
        return order;
    }

    @Override
    public Order get(Long id) {
        Long userId = null;
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM orders WHERE id = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getLong("user_id");
            }
        } catch (SQLException exception) {
            log.error("Couldn't get an order with id " + id);
        }
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT items.id, items.name, items.price FROM items INNER JOIN orders_items "
                        + "WHERE items.id = orders_items.item_id AND orders_items.order_id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"),
                        resultSet.getDouble("price"));
                item.setId(resultSet.getLong("id"));
                items.add(item);
            }
        } catch (SQLException exception) {
            log.error("Couldn't get an order with id " + id);
        }
        Order order = new Order(userId, items);
        order.setId(id);
        return order;
    }

    @Override
    public Order update(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM order_items WHERE order_id = ?")) {
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("Couldn't update an order");
        }
        addItems(order);
        return order;
    }

    @Override
    public Order delete(Long id) {
        Order order = get(id);
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM orders WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("Couldn't delete an order with id " + id);
        }
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM orders WHERE user_id = ?")) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(get(resultSet.getLong("id")));
            }
        } catch (SQLException exception) {
            log.error("Couldn't get orders for user " + userId);
        }
        return orders;
    }

    private List<Item> addItems(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO orders_items (order_id, item_id) VALUES (?, ?)")) {
            for (Item item : order.getItems()) {
                statement.setLong(1, order.getId());
                statement.setLong(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            log.error("Couldn't add items to an order");
        }
        return order.getItems();
    }
}
