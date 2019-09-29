package mate.academy.internetshop.dao.jdbc;

import static java.lang.String.format;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao implements ItemDao {
    private static final String TABLE = "Shop.Items";
    private static final Logger log = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    format("INSERT INTO %s ('Name', 'Price') VALUES (%s, %f);",
                            TABLE, item.getName(), item.getPrice()));
        } catch (SQLException exception) {
            log.error("Couldn't add an item");
        }
        return item;
    }

    @Override
    public Item get(Long id) {
        Item item = null;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    format("SELECT * FROM %s WHERE Id = %d;", TABLE, id));
            while (resultSet.next()) {
                item = new Item(
                        resultSet.getLong("Id"),
                        resultSet.getString("Name"),
                        resultSet.getDouble("Price"));
            }
        } catch (SQLException exception) {
            log.error("Couldn't find an item with id " + id);
        }
        return item;
    }

    @Override
    public Item update(Item item) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    format("UPDATE %s SET Price = %f WHERE Id = %d",
                    TABLE, item.getPrice(), item.getId()));
        } catch (SQLException exception) {
            log.error("Couldn't find an item with id " + item.getId());
        }
        return item;
    }

    @Override
    public Item delete(Long id) {
        Item item = get(id);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    format("DELETE FROM %s WHERE Id = %d", TABLE, id));
        } catch (SQLException exception) {
            log.error("Couldn't find an item with id " + id);
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    format("SELECT * FROM %s;", TABLE));
            while (resultSet.next()) {
                list.add(new Item(
                        resultSet.getLong("Id"),
                        resultSet.getString("Name"),
                        resultSet.getDouble("Price")));
            }
        } catch (SQLException exception) {
            log.error("Couldn't find a table " + TABLE);
        }
        return list;
    }
}
