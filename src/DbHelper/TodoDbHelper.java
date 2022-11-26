package DbHelper;
import Database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoDbHelper {

    private Database database;

    public TodoDbHelper(Database database) {
        this.database = database;
    }
    public void showTodos(int userId) throws SQLException {
        database
                .statement
                .execute(
                        "SELECT * FROM todos where user_id = " + userId);
        ResultSet result = database.statement.getResultSet();
        while (result.next()){
            System.out.println( "----------------------------------------" +
                    "\nid: " +
                    result.getInt("id") + " " +
                            "  \nBaşlığı : " + result.getString("title") + "" +
                            "\n\tIçeriği " + result.getString("content") + "" +
                            "\n\tTamamlanma Durumu : " + result.getBoolean("complete") + "" +
                            "\n\tOluşturma Tarihi : " + result.getString("created_date"));
        }
        System.out.println( "\n" );
    }

    public boolean addTodo(int userId, String title, String content) throws SQLException {

        database
                .statement
                .execute(
                        "INSERT INTO todos (title, content, user_id, complete, created_date) " +
                                "VALUES ('" + title + "', '" + content + "', '" + userId + "', 0, datetime('now'))");
        return database.statement.getUpdateCount() > 0;
    }

    public boolean deleteTodo(int userId,int todoId) throws SQLException {

        database
                .statement
                .execute(
                        "DELETE FROM todos WHERE id = " + todoId + " AND user_id = " + userId);
        return database.statement.getUpdateCount() > 0;
    }

    public boolean updateTodo(int userId,int todoId, String title, String content) throws SQLException {

        database
                .statement
                .execute(
                        "UPDATE todos SET title = '" + title + "', content = '" + content + "' WHERE id = " + todoId + " AND user_id = " + userId);
        return database.statement.getUpdateCount() > 0;
    }

    public boolean completeTodo (int userId ,int todoId) throws SQLException {

        database
                .statement
                .execute(
                        "UPDATE todos SET complete = 1 WHERE id = " + todoId + " AND user_id = " + userId);
        return database.statement.getUpdateCount() > 0;
    }

    public boolean uncompleteTodo (int userId ,int todoId) throws SQLException {

        database
                .statement
                .execute(
                        "UPDATE todos SET complete = 0 WHERE id = " + todoId + " AND user_id = " + userId);
        return database.statement.getUpdateCount() > 0;
    }

    public void showCompletedTodos(int userId) throws SQLException {
        database
                .statement
                .execute(
                        "SELECT * FROM todos where user_id = " + userId + " AND complete = 1");
        ResultSet result = database.statement.getResultSet();
        while (result.next()){
            System.out.println( "----------------------------------------" +
                    "\nid: " +
                    result.getInt("id") + " " +
                    "  \nBaşlığı : " + result.getString("title") + "" +
                    "\n\tIçeriği " + result.getString("content") + "" +
                    "\n\tTamamlanma Durumu : " + result.getBoolean("complete") + "" +
                    "\n\tOluşturma Tarihi : " + result.getString("created_date"));
        }
        System.out.println( "\n" );

    }

    public void showUncompletedTodos(int userId) throws SQLException {
        database
                .statement
                .execute(
                        "SELECT * FROM todos where user_id = " + userId + " AND complete = 0");
        ResultSet result = database.statement.getResultSet();
        while (result.next()){
            System.out.println( "----------------------------------------" +
                    "\nid: " +
                    result.getInt("id") + " " +
                    "  \nBaşlığı : " + result.getString("title") + "" +
                    "\n\tIçeriği " + result.getString("content") + "" +
                    "\n\tTamamlanma Durumu : " + result.getBoolean("complete") + "" +
                    "\n\tOluşturma Tarihi : " + result.getString("created_date"));
        }
        System.out.println( "\n" );

    }

}
