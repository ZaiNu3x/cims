package group.intelliboys.cims.Util;

import group.intelliboys.cims.configs.Database;
import group.intelliboys.cims.models.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Validator {
    public static boolean isUsernameValid(String username) {
        return true;
    }

    public static boolean isValidRegistrationForm(User user) throws SQLException {

        Connection conn = Database.getConnection();
        Statement stmt = conn.createStatement();

        String query = "SELECT * FROM users WHERE username = '" + user.getUsername() + "';";

        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            return false;
        } else {
            return true;
        }
    }
}
