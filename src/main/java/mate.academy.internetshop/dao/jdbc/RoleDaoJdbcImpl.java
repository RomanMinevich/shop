package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import org.apache.log4j.Logger;

@Dao
public class RoleDaoJdbcImpl extends AbstractDao implements RoleDao {
    private static final Logger log = Logger.getLogger(RoleDaoJdbcImpl.class);

    public RoleDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role get(Long id) {
        String name = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT name FROM roles WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException exception) {
            log.error("Couldn't get a role with id " + id);
        }
        return new Role(id, Role.RoleName.valueOf(name));
    }

    @Override
    public Set<Role> getUserRoles(Long userId) {
        Set<Role> roles = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT role_id FROM users_roles WHERE user_id = ?")) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role = get(resultSet.getLong("role_id"));
                roles.add(role);
            }
        } catch (SQLException exception) {
            log.error("Couldn't get roles for user with id " + userId);
        }
        return roles;
    }
}
