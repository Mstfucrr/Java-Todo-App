package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Database {

    private String url = "jdbc:sqlite:D:/programlar/YAZILIM/java/java-todo-app/src/Database/todo.db";
    public Connection connection = null;

    public Statement statement;

    public void connect() throws SQLException {
        try {
            connection = DriverManager.getConnection(url); // Veritabanına bağlanıyoruz.
            System.out.println("Veritabanına bağlanıldı.");

        } catch (Exception e) {

            e.printStackTrace();
        }
        assert connection != null;
        statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS Users " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "password TEXT)"); // Kullanıcı tablosu oluşturuldu.


        statement.execute("CREATE TABLE IF NOT EXISTS todos " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "content TEXT, " +
                "user_id INTEGER, " +
                "complete Bool," +
                "created_date Date, " +
                "FOREIGN KEY(user_id) REFERENCES users(id))"); // todo tablosu oluşturuldu.

    }

}
