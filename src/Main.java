import Database.Database;
import DbHelper.UserDbHelper;
import DbHelper.TodoDbHelper;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static Database database;
    public static UserDbHelper userDbHelper;
    public static TodoDbHelper todoDbHelper;


    public static void main(String[] args) throws SQLException {
        database = new Database();
        database.connect();
        userDbHelper = new UserDbHelper(database);
        todoDbHelper = new TodoDbHelper(database);
        run();
    }

    public static void menu(){
        System.out.println("1. Giriş yap");
        System.out.println("2. Kayıt ol");
        System.out.println("3. Çıkış");

    }
    public static void run() throws SQLException {
        while (true){
            menu();
            System.out.println("Seçiminiz: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("Hatalı seçim");
            }
        }
    }
    public static void login() throws SQLException {
        System.out.println("Kullanıcı adı: ");
        String username = scanner.nextLine();
        System.out.println("Şifre: ");
        String password = scanner.nextLine();

        int isLogin = userDbHelper.login(username, password);
        if (isLogin != -1){
            System.out.println("Giriş başarılı");
            int id = isLogin;
            UserMenu(id);
            // TODO: Todo listesini göster
            // TODO: Todo listesine ekleme yap
            // TODO: Todo listesinden silme yap
            // TODO: Todo listesini güncelle
            // TODO: Todo listesini tamamla
        }
        else{
            System.out.println("Giriş başarısız");
        }
    }
    public static void register() throws SQLException {
        System.out.println("Kullanıcı adı: ");
        String username = scanner.nextLine();
        System.out.println("Şifre: ");
        String password = scanner.nextLine();
        System.out.println("Şifre tekrar: ");
        String passwordRe = scanner.nextLine();

        System.out.println(password.equals(passwordRe)
                ? "Kayıt başarılı"
                : "Şifreler uyuşmuyor");

        userDbHelper.register(username, password);
    }
    public static void exit(){
        System.out.println("Çıkış yapılıyor...");
        System.exit(0);
    }

    public static void UserMenu(int userId) throws SQLException {
        while (true){
            System.out.println("1. Todo listesini göster");
            System.out.println("2. Todo listesine ekleme yap");
            System.out.println("3. Todo listesinden silme yap");
            System.out.println("4. Todo güncelle");
            System.out.println("5. Todo tamamla");
            System.out.println("6. Todo Durumunu Tamamlanmamış Olarak Güncelle");
            System.out.println("7. Tamamlanmış todo listesini göster");
            System.out.println("8. Tamamlanmayan todo listesini göster");
            System.out.println("9. Çıkış");

            int user_select = scanner.nextInt();
            scanner.nextLine();

            switch(user_select){
                case 1:
                    showTodoList(userId);
                    break;
                case 2:
                    addTodo(userId);
                    break;
                case 3:
                    deleteTodo(userId);
                    break;
                case 4:
                    updateTodo(userId);
                    break;
                case 5:
                    completeTodo(userId);
                    break;
                case 6:
                    uncompletedTodo(userId);
                    break;
                case 7:
                    showCompletedTodoList(userId);
                    break;
                case 8:
                    showUncompletedTodoList(userId);
                    break;
                case 9:
                    exit();
                    break;
                default:
                    System.out.println("Hatalı seçim");
            }
        }
    }



    private static void showTodoList(int userId) throws SQLException {
        todoDbHelper.showTodos(userId);
    }

    private static void showCompletedTodoList(int userId) throws SQLException {
        todoDbHelper.showCompletedTodos(userId);
    }

    private static void showUncompletedTodoList(int userId) throws SQLException {
        todoDbHelper.showUncompletedTodos(userId);
    }

    private static void addTodo(int userId) throws SQLException {
        System.out.println("Başlığı: ");
        String title = scanner.nextLine();
        System.out.println("İçeriği: ");
        String content = scanner.nextLine();
        if (todoDbHelper.addTodo(userId, title, content)){
            System.out.println("Todo başarıyla eklendi");
        }
        else{
            System.out.println("Todo eklenemedi");
        }
        showTodoList(userId);
    }
    private static void completeTodo(int userId) throws SQLException {
        System.out.println("Tamamlanacak todo id: ");
        int todoId = scanner.nextInt();
        scanner.nextLine();
        if (todoDbHelper.completeTodo(userId, todoId)){
            System.out.println("Todo başarıyla tamamlandı");
        }
        else{
            System.out.println("Todo tamamlanamadı");
        }
        showTodoList(userId);

    }
    private static void uncompletedTodo(int userId) throws SQLException {
        System.out.println("Durumu Tamamlanmadı olarak değiştirilecek todo id: ");
        int todoId = scanner.nextInt();
        scanner.nextLine();
        if (todoDbHelper.uncompleteTodo(userId, todoId)){
            System.out.println("Todo başarıyla tamamlanmadı olarak değiştirildi");
        }
        else{
            System.out.println("Todo tamamlanmadı olarak değiştirilemedi");
        }
        showTodoList(userId);
    }
    private static void updateTodo(int userId) throws SQLException {
        System.out.println("Güncellenecek todo id: ");
        int todoId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Başlığı: ");
        String title = scanner.nextLine();
        System.out.println("İçeriği: ");
        String content = scanner.nextLine();
        if (todoDbHelper.updateTodo(userId, todoId, title, content)){
            System.out.println("Todo başarıyla güncellendi");
        }
        else{
            System.out.println("Todo güncellenemedi");
        }
        showTodoList(userId);

    }
    private static void deleteTodo(int userId) throws SQLException {
        System.out.println("Silinecek todo id: ");
        int todoId = scanner.nextInt();
        scanner.nextLine();
        if (todoDbHelper.deleteTodo(userId, todoId)){
            System.out.println("Todo başarıyla silindi");
        }
        else{
            System.out.println("Todo silinemedi");
        }
        showTodoList(userId);


    }


}