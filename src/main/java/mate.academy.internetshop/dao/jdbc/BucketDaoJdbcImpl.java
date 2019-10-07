package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao implements BucketDao {
    private static final Logger log = Logger.getLogger(BucketDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO buckets (id) VALUE (?)")) {
            statement.setLong(1, bucket.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("Couldn't create a bucket");
        }
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        Bucket bucket = new Bucket(id);
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT items.id, items.name, items.price FROM items "
                        + "INNER JOIN buckets_items "
                        + "WHERE items.id = buckets_items.item_id "
                        + "AND buckets_items.bucket_id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"),
                        resultSet.getDouble("price"));
                item.setId(resultSet.getLong("id"));
                bucket.getItems().add(item);
            }
        } catch (SQLException exception) {
            log.error("Couldn't get a bucket with id " + id);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM buckets_items WHERE bucket_id = ?")) {
            statement.setLong(1, bucket.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("Couldn't clear a bucket ");
        }
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO buckets_items (bucket_id, item_id) VALUES (?, ?)")) {
            for (Item item : bucket.getItems()) {
                statement.setLong(1, bucket.getId());
                statement.setLong(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            log.error("Couldn't add items to a bucket");
        }
        return bucket;
    }

    @Override
    public Bucket delete(Long id) {
        Bucket bucket = get(id);
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM buckets WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("Couldn't delete a bucket item with id " + id);
        }
        return bucket;
    }
}
