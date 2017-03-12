package db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author kawasima
 */
public class V6__InsertSignature implements JdbcMigration {
    @Override
    public void migrate(Connection connection) throws Exception {
        String sql = "INSERT INTO signature(signature_id, name, signature_comment, campaign_id) " +
                "VALUES(?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, 0L);
            stmt.setString(2, "kawasima");
            stmt.setString(3, "賛同します！");
            stmt.setLong(4, 1L);
            stmt.executeUpdate();
            connection.commit();
        }
    }
}
