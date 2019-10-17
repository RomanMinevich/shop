package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao implements ItemDao {
    private static final Logger log = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO items (name, price) VALUES (?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getLong(1));
            }
        } catch (SQLException exception) {
            log.error("Couldn't create an item");
        }
        return item;
    }

    @Override
    public Item get(Long id) {
        Item item = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM items WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                item = new Item();
                item.setId(id);
            }
        } catch (SQLException exception) {
            log.error("Couldn't get an item with id " + id);
        }
        return item;
    }

    @Override
    public Item update(Item item) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE items SET price = ? WHERE id = ?")) {
            statement.setDouble(1, item.getPrice());
            statement.setLong(2, item.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("Couldn't update an item");
        }
        return item;
    }

    @Override
    public Item delete(Long id) {
        Item item = get(id);
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM items WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("Couldn't delete an item with id " + id);
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM items")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                list.add(get(id));
            }
        } catch (SQLException exception) {
            log.error("Couldn't get all items");
        }
        return list;
    }
}
