package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;

public class AbstractDao {
    protected final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }
}
