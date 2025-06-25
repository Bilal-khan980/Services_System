import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;

import com.mysql.cj.jdbc.DatabaseMetaData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Database1 {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9167909@mysql";
    private Connection connection = null;
    private Statement statement = null;

    public Database1() {

    }

    public boolean doesCustomerIdExist(int customerId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement.executeUpdate(useDatabaseQuery);

            String checkIdQuery = "SELECT COUNT(*) FROM Customer WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkIdQuery)) {
                preparedStatement.setInt(1, customerId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);

                    return count > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean doesCustomerExist(int id, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            String checkCustomerQuery = "SELECT COUNT(*) FROM customer WHERE id = ? AND password = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(checkCustomerQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);

                    return count > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertCustomer(Customer customer) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String dbName = "Project";
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + dbName;
            statement.executeUpdate(createDatabaseQuery);

            String useDatabaseQuery = "USE " + dbName;
            statement.executeUpdate(useDatabaseQuery);

            String createTableQuery = "CREATE TABLE IF NOT EXISTS Customer ("
                    + "id INT PRIMARY KEY, "
                    + "password VARCHAR(255), "
                    + "name VARCHAR(255), "
                    + "cnic VARCHAR(255), "
                    + "age INT, "
                    + "address VARCHAR(255), "
                    + "contact_no VARCHAR(255))";
            statement.executeUpdate(createTableQuery);

            String insertDataQuery = "INSERT INTO Customer (id, password, name, cnic, age, address, contact_no) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getCnic());
            preparedStatement.setInt(5, customer.getAge());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setString(7, customer.getContact_no());

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkAdminCredentials(Staff A) {
        int adminId = A.getId();
        String adminPassword = A.getPassword();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            String checkAdminQuery = "SELECT COUNT(*) FROM staff WHERE id = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkAdminQuery)) {
                preparedStatement.setInt(1, adminId);
                preparedStatement.setString(2, adminPassword);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);

                    return count > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {

                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList<services> getServices() {
        ObservableList<services> servicesList = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;

            try (PreparedStatement statement = connection.prepareStatement(useDatabaseQuery)) {
                statement.execute();

                String selectQuery = "SELECT name, duration, description, cost FROM services";
                try (ResultSet resultSet = statement.executeQuery(selectQuery)) {
                    while (resultSet.next()) {
                        String name = resultSet.getString("name");
                        int duration = resultSet.getInt("duration");
                        String description = resultSet.getString("description");
                        double cost = resultSet.getDouble("cost");

                        services service = new services(name, duration, description, cost);
                        servicesList.add(service);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicesList;
    }

    public boolean doesServiceExist(String serviceName) {
        String dbName = "Project";
        String useDatabaseQuery = "USE " + dbName;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(useDatabaseQuery)) {
            preparedStatement.execute();

            String selectQuery = "SELECT COUNT(*) FROM Services WHERE name = ?";
            try (PreparedStatement preparedStatementSelect = connection.prepareStatement(selectQuery)) {
                preparedStatementSelect.setString(1, serviceName);

                try (ResultSet resultSet = preparedStatementSelect.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertService(services service) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String dbName = "Project";
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + dbName;
            statement.executeUpdate(createDatabaseQuery);

            String useDatabaseQuery = "USE " + dbName;
            statement.executeUpdate(useDatabaseQuery);

            String createTableQuery = "CREATE TABLE IF NOT EXISTS services ("
                    + "name VARCHAR(255), "
                    + "duration INT, "
                    + "description VARCHAR(255), "
                    + "cost DOUBLE)";
            statement.executeUpdate(createTableQuery);

            String checkServiceQuery = "SELECT COUNT(*) FROM services WHERE LOWER(name) = LOWER(?)";
            PreparedStatement checkServiceStatement = connection.prepareStatement(checkServiceQuery);
            checkServiceStatement.setString(1, service.getName());
            ResultSet resultSet = checkServiceStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {

                showAlert("Service Already Exists", "The service already exists in the database.");
                return;
            }

            String insertDataQuery = "INSERT INTO services (name, duration, description, cost) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setInt(2, service.getDuration());
            preparedStatement.setString(3, service.getDescription());
            preparedStatement.setDouble(4, service.getCost());

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean doesServiceExistIgnoreCase(String serviceName) {
        String dbName = "Project";
        String useDatabaseQuery = "USE " + dbName;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(useDatabaseQuery)) {
            preparedStatement.execute();

            String selectQuery = "SELECT COUNT(*) FROM Services WHERE LOWER(name) = LOWER(?)";
            try (PreparedStatement preparedStatementSelect = connection.prepareStatement(selectQuery)) {
                preparedStatementSelect.setString(1, serviceName);

                try (ResultSet resultSet = preparedStatementSelect.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertAppointment(Appointments appointment) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;

            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            String createTableQuery = "CREATE TABLE IF NOT EXISTS Appointments ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "duration INT, "
                    + "date DATE, "
                    + "time TIME, "
                    + "staffId INT, "
                    + "serviceName VARCHAR(255), "
                    + "customerId INT, "
                    + "status VARCHAR(255))";
            statement.executeUpdate(createTableQuery);

            String insertAppointmentQuery = "INSERT INTO Appointments (duration, date, time, staffId, serviceName, customerId, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertAppointmentQuery);
            preparedStatement.setInt(1, appointment.getDuration());
            preparedStatement.setDate(2, new java.sql.Date(appointment.getDate().getTime()));
            preparedStatement.setTime(3, new java.sql.Time(appointment.getTime().getTime()));
            preparedStatement.setInt(4, appointment.getStaffId());
            preparedStatement.setString(5, appointment.getServiceName());
            preparedStatement.setInt(6, appointment.getCustomerId());
            preparedStatement.setString(7, appointment.getStatus());

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList<Appointments> getAppointments() {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;

            try (PreparedStatement statement = connection.prepareStatement(useDatabaseQuery)) {
                statement.execute();

                String selectQuery = "SELECT id, duration, date, time, staffId, serviceName, customerId, status FROM Appointments WHERE status = 'pending'";
                try (ResultSet resultSet = statement.executeQuery(selectQuery)) {
                    while (resultSet.next()) {
                        int code = resultSet.getInt("id");
                        int duration = resultSet.getInt("duration");
                        Date date = resultSet.getDate("date");
                        Date time = resultSet.getTime("time");
                        int staffId = resultSet.getInt("staffId");
                        String serviceName = resultSet.getString("serviceName");
                        int customerId = resultSet.getInt("customerId");
                        String status = resultSet.getString("status");

                        Appointments appointment = new Appointments(code, duration, date, time, staffId, serviceName,
                                customerId, status);
                        appointmentsList.add(appointment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsList;
    }

    public boolean updateAppointmentStatus(int appointmentId, String status) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            PreparedStatement useDatabaseStatement = connection.prepareStatement(useDatabaseQuery);
            useDatabaseStatement.executeUpdate();

            String updateQuery = "UPDATE Appointments SET status = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, appointmentId);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            useDatabaseStatement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<Appointments> getAppointmentsx() {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;

            try (PreparedStatement statement = connection.prepareStatement(useDatabaseQuery)) {
                statement.execute();

                String selectQuery = "SELECT id, duration, date, time, staffId, serviceName, customerId, status FROM Appointments WHERE status = 'Approved'";
                try (ResultSet resultSet = statement.executeQuery(selectQuery)) {
                    while (resultSet.next()) {
                        int code = resultSet.getInt("id");
                        int duration = resultSet.getInt("duration");
                        Date date = resultSet.getDate("date");
                        Date time = resultSet.getTime("time");
                        int staffId = resultSet.getInt("staffId");
                        String serviceName = resultSet.getString("serviceName");
                        int customerId = resultSet.getInt("customerId");
                        String status = resultSet.getString("status");

                        Appointments appointment = new Appointments(code, duration, date, time, staffId, serviceName,
                                customerId, status);
                        appointmentsList.add(appointment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsList;
    }

    public boolean updateAppointmentstaff(int id, int staff_id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            PreparedStatement useDatabaseStatement = connection.prepareStatement(useDatabaseQuery);
            useDatabaseStatement.executeUpdate();

            String updateQuery = "UPDATE Appointments SET staffId = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, staff_id);
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            useDatabaseStatement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<Appointments> getAppointmentsWithId(int customerID) {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;

            try (PreparedStatement statement = connection.prepareStatement(useDatabaseQuery)) {
                statement.execute();

                String selectQuery = "SELECT id, duration, date, time, staffId, serviceName, customerId, status FROM Appointments WHERE staffId>0 AND status = 'Approved' AND customerId = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setInt(1, customerID);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            int code = resultSet.getInt("id");
                            int duration = resultSet.getInt("duration");
                            Date date = resultSet.getDate("date");
                            Time time = resultSet.getTime("time");
                            int staffId = resultSet.getInt("staffId");
                            String serviceName = resultSet.getString("serviceName");
                            String status = resultSet.getString("status");

                            Appointments appointment = new Appointments(code, duration, date, time, staffId,
                                    serviceName,
                                    customerID, status);
                            appointmentsList.add(appointment);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsList;
    }

    public boolean updateAppointment(int appointmentId, int duration, Date appointmentDate, Time appointmentTime) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            PreparedStatement useDatabaseStatement = connection.prepareStatement(useDatabaseQuery);
            useDatabaseStatement.executeUpdate();

            String updateQuery = "UPDATE Appointments SET duration = ?, date = ?, time = ? , status = 'pending' WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, duration);
            preparedStatement.setDate(2, new java.sql.Date(appointmentDate.getTime()));
            preparedStatement.setTime(3, appointmentTime);
            preparedStatement.setInt(4, appointmentId);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            useDatabaseStatement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addStaffMember(Staff staff) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            PreparedStatement useDatabaseStatement = connection.prepareStatement(useDatabaseQuery);
            useDatabaseStatement.executeUpdate();

            String insertQuery = "INSERT INTO staff (id, password, name, cnic, age, address, contact_no, availability) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, staff.getId());
            preparedStatement.setString(2, staff.getPassword());
            preparedStatement.setString(3, staff.getName());
            preparedStatement.setString(4, staff.getCnic());
            preparedStatement.setInt(5, staff.getAge());
            preparedStatement.setString(6, staff.getAddress());
            preparedStatement.setString(7, staff.getContact_no());
            preparedStatement.setString(8, staff.getAvailability());

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            useDatabaseStatement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createStaffTableAndInsertDefaults() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            PreparedStatement useDatabaseStatement = connection.prepareStatement(useDatabaseQuery);
            useDatabaseStatement.executeUpdate();

            DatabaseMetaData metaData = (DatabaseMetaData) connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "staff", null);
            if (!tables.next()) {

                String createTableQuery = "CREATE TABLE staff (" +
                        "id INT PRIMARY KEY," +
                        "password VARCHAR(255)," +
                        "name VARCHAR(255)," +
                        "cnic VARCHAR(20)," +
                        "age INT," +
                        "address VARCHAR(255)," +
                        "contact_no VARCHAR(20)," +
                        "availability VARCHAR(50)" +
                        ")";
                PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery);
                createTableStatement.executeUpdate();
                createTableStatement.close();

                String insertQuery = "INSERT INTO staff (id, password, name, cnic, age, address, contact_no, availability) "
                        +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, 1);
                preparedStatement.setString(2, "123");
                preparedStatement.setString(3, "admin");
                preparedStatement.setString(4, "3740502682719");
                preparedStatement.setInt(5, 30);
                preparedStatement.setString(6, "Street # 2");
                preparedStatement.setString(7, "1234567890");
                preparedStatement.setString(8, "yes");

                preparedStatement.executeUpdate();
                preparedStatement.close();
            }

            useDatabaseStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}