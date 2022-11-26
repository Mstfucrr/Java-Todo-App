package DbHelper;
import Database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDbHelper {

    private Database database;
    public UserDbHelper(Database database) {
        this.database = database;
    }

    public int login(String username, String password) throws SQLException {

        database
                .statement
                .execute(
                        "SELECT * FROM users " +
                                "WHERE username = '" + username + "' AND password = '" + password + "'");

        ResultSet result = database.statement.getResultSet();
        if (result.next()){
            return result.getInt("id");
        }
        return -1;
    }
    public boolean register(String username, String password) throws SQLException {

        database
                .statement
                .execute(
                        "INSERT INTO users (username, password) " +
                                "VALUES ('" + username + "', '" + password + "')");
        return database.statement.getUpdateCount() > 0;
        // Veritabanına kullanıcı adı ve şifre kaydedilecek.
    }


}
