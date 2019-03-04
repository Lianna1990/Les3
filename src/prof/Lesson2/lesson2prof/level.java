package prof.Lesson2.lesson2prof;







import javax.swing.*;
import java.sql.*;

import static sun.misc.Launcher.getLauncher;

public class level {
        private static final String URL = "jdbc:mysql://localhost:7777/project";
        private static final String USERNAME = "Carry";
        private static final String PASSWORD = "emozi15";

        private static final String INSERT_NEW_USER = "INSERT INTO users(login, password,  name,  status, session) VALUES(  ?, ?, ?, ?, ?);";
        private static final String LOGIN = "SELECT * FROM users WHERE login = ? AND password = ?;";
        private static final String OPEN_SESSION = "UPDATE users set session = 'online' WHERE id = ?;";
        private static Connection connection;

        private static int id;
        private static String login;

        private static String name;



        private static String status;

        public static void connectionToData() {
            try {
                Driver driver = new FabricMySQLDriver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static void register(String login, String password, String email, String name,  String status) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);

                preparedStatement.setString(3, name);



                preparedStatement.setString(4, status);
                preparedStatement.setString(5, "offline");
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static void login(String login, String password) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(LOGIN);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                setValues(resultSet);
                JOptionPane.showMessageDialog(getLauncher(), "Welcome");
                preparedStatement = connection.prepareStatement(OPEN_SESSION);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static void setValues(ResultSet resultSet) {
            try {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                    login = resultSet.getString("login");

                    name = resultSet.getString("name");



                    status = resultSet.getString("status");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static String getStatus() {
            return status;
        }








        public static String getName() {
            return name;
        }



        public static String getLogin() {
            return login;
        }

        public static int getId() {
            return id;
        }
    }


