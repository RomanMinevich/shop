package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exception.AuthenticationException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao implements UserDao {
    private static final Logger log = Logger.getLogger(UserDaoJdbcImpl.class);

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users ("
                        + "token, phone_number, password, name, adress, email) VALUES ("
                        + "?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getToken());
            statement.setString(2, user.getPhoneNumber());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getName());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getEmail());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException exception) {
            log.error("Couldn't create a user");
        }
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users_roles (user_id) VALUE (?)")) {
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("Couldn't add a role to a user");
        }
        return setRoles(user);
    }

    @Override
    public User get(Long id) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setToken(resultSet.getString("token"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setName(resultSet.getString("name"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException exception) {
            log.error("Couldn't get an item with id " + id);
        }
        user.setId(id);
        return setRoles(user);
    }

    @Override
    public User update(User user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE users "
                        + "SET phone_number = ?, password = ?, name = ?, adress = ?, email = ? "
                        + "WHERE id = ?")) {
            statement.setString(1, user.getPhoneNumber());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getEmail());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("Couldn't update a user");
        }
        return setRoles(user);
    }

    @Override
    public User delete(Long id) {
        User user = get(id);
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            log.error("Couldn't delete a user with id " + id);
        }
        return user;
    }

    @Override
    public User login(String phoneNumber, String password) throws AuthenticationException {
        Long id = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM users "
                        + "WHERE phone_number = ? AND password = ?")) {
            statement.setString(1, phoneNumber);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException exception) {
            log.error("Couldn't get user with phone number " + phoneNumber);
        }
        if (id == null) {
            throw new AuthenticationException("Incorrect phone number or password");
        }
        return get(id);
    }

    @Override
    public User getByToken(String token) {
        Long id = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM users WHERE token = ?")) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException exception) {
            log.error("Couldn't get user with token " + token);
        }
        return (id == null) ? null : get(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM users")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                list.add(get(id));
            }
        } catch (SQLException exception) {
            log.error("Couldn't get all users");
        }
        return list;
    }

    private User setRoles(User user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT roles.id, roles.name FROM roles "
                        + "INNER JOIN users_roles WHERE roles.id = users_roles.role_id "
                        + "AND users_roles.user_id = ?")) {
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role(
                        resultSet.getLong("id"), resultSet.getString("name"));
                user.getRoles().add(role);
            }
        } catch (SQLException exception) {
            log.error("Couldn't set roles for user with id " + user.getId());
        }
        return user;
    }
}
