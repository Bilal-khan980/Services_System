import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainSceneController 
{
   
    Database1 D=new Database1();
    
    Customer C=new Customer();
    @FXML
    void gotocustomermenu(ActionEvent event) 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer_interface.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Customer Interface");

            stage.setScene(new Scene(root));

            stage.show();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    @FXML
    void gotoadminmenu(ActionEvent event) 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_menu.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));

            stage.show();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField admin_id_txt;

    @FXML
    private TextField admin_password_txt;

    @FXML
    void admin_login(ActionEvent event)
    {
        int adminId =  Integer.parseInt(admin_id_txt.getText());
        String adminPassword = admin_password_txt.getText();

        Staff A=new Staff(adminId, adminPassword);
        D = new Database1();

        if (D.checkAdminCredentials(A)) 
        {
            
            load_admin_menu();
        } 
        else 
        {
            System.out.println("Invalid admin credentials");
        }
    }

    void load_admin_menu()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_menu.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
                stage.show();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
    }

    @FXML
    void admin_add_staff(ActionEvent event) 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_staff_add.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
                stage.show();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
    }

    @FXML
    private TextField txtAddresss;

    @FXML
    private TextField txtAges;

    @FXML
    private TextField txtIDs;

    @FXML
    private TextField txtNames;

    @FXML
    private TextField txtcnics;

    @FXML
    private TextField txtcontactnos;

    @FXML
    private TextField txtpasswords;

    @FXML
    void add_staff_in_db(ActionEvent event) {
    int id = Integer.parseInt(txtIDs.getText());
    String password = txtpasswords.getText();
    String name = txtNames.getText();
    String cnic = txtcnics.getText();
    int age = Integer.parseInt(txtAges.getText());
    String address = txtAddresss.getText();
    String contactNo = txtcontactnos.getText();
    String availability = "yes"; 

    Staff staff = new Staff(id, password, name, cnic, age, address, contactNo, availability);

  
    boolean success = D.addStaffMember(staff);
    if (success) {
        showAlert("Success", "Staff member added successfully.");
    } else {
        showAlert("Error", "Failed to add staff member.");
    }
    }


    private void showAlert(String title, String content)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void gotomain(ActionEvent event) 
    {    
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FirstPage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("FirstPage.fxml");

            stage.setScene(new Scene(root));

            stage.show();
        } 

        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    @FXML
    void Adminopen(ActionEvent event) 
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin_login.fxml");
            stage.setScene(new Scene(root));

            stage.show();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void customeropen(ActionEvent event)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer_login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("customer_login.fxml");

            stage.setScene(new Scene(root));

            stage.show();
        } 

        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    @FXML
    void admin_register_customer(ActionEvent event) 
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Customer_register_page.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtcnic;

    @FXML
    private TextField txtcontactno;

    @FXML
    private TextField txtpassword;

   
    @FXML
    void register_button(ActionEvent event) 
    {
        int id = Integer.parseInt(txtID.getText());

            String name = txtName.getText();
            int age = Integer.parseInt(txtAge.getText());
            String address = txtAddress.getText();
            String password = txtpassword.getText();
            String cnic = txtcnic.getText();
            String contactno = txtcontactno.getText();

            Customer customer = new Customer(id,password,name,cnic, age, address,contactno);
            D.insertCustomer(customer);
    
            load_admin_menu();
            showAlert("SUCCESS", "NEW CUSTOMER REGISTERED.");
        
    }

    @FXML
    private TextField login_customer_id;

    @FXML
    private TextField login_customer_password;


    @FXML
    void login_customer(ActionEvent event) 
    {
        int customerId = Integer.parseInt(login_customer_id.getText());
        String password = login_customer_password.getText();

        if (D.doesCustomerExist(customerId, password)) 
        {
            try 
            {
                LoggedInAcc.setLoggedInCustomerId(customerId);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("customer_interface.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Customer Interface");
                stage.setScene(new Scene(root));
                stage.show();
            } 

            catch (IOException e) 
            {
                e.printStackTrace();
            }
        } 

        else 
        {
            showAlert("Error 404", "WRONG LOGIN CREDENTIALS");
        }
    } 

    @FXML
    void register_customer(ActionEvent event) {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer_register_page.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("customer_login.fxml");

            stage.setScene(new Scene(root));

            stage.show();
        } 

        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    @FXML
    private TableView<services> servicesTableView;

    @FXML
    private TableColumn<services, String> nameColumn;

    @FXML
    private TableColumn<services, Integer> durationColumn;

    @FXML
    private TableColumn<services, String> descriptionColumn;

    @FXML
    private TableColumn<services, Double> costColumn;

    @FXML
    public void initializetable() {
        servicesTableView.setItems(D.getServices());
    }

    @FXML
    void customer_customer_services(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer_check_services.fxml"));
            Parent root = loader.load();

            MainSceneController servicesController = loader.getController();

            servicesController.servicesTableView.setItems(D.getServices());

            Stage stage = new Stage();
            stage.setTitle("Customer Services");
            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void customer_request_quote(ActionEvent event)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer_req_quote.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("customer_login.fxml");

            stage.setScene(new Scene(root));

            stage.show();
        } 

        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    @FXML
    private DatePicker service_date;

    @FXML
    private TextField service_name;

    @FXML
    private TextField time_of_service;

    @FXML
    private TextField service_duration;

    private Database1 database = new Database1(); 

    int customer_id = LoggedInAcc.getLoggedInCustomerId();

    @FXML
    void reuqest_service(ActionEvent event) {
       
        String serviceName = service_name.getText();
        String serviceDurationText = service_duration.getText();
        String timeOfServiceText = time_of_service.getText();

       
        int serviceDuration = Integer.parseInt(serviceDurationText);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date timeOfService = null;
        try {
            timeOfService = timeFormat.parse(timeOfServiceText);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        
        LocalDate serviceDate = service_date.getValue();

                Date dateOfService = Date.from(serviceDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        
        Appointments appointment = new Appointments(0, serviceDuration, dateOfService, timeOfService, 0, serviceName, customer_id, "pending");

        
        if (database != null) {
            database.insertAppointment(appointment);
        } else {
            System.err.println("Database instance not set.");
        }
    }

    @FXML
    void customer_reschedule_appointments(ActionEvent event)  
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer_update_appointment.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Service");

            stage.setScene(new Scene(root));

            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private TextField appointment_id;

    @FXML
    private DatePicker service_date_up;

    @FXML
    private TextField service_duration_up;

    @FXML
    private TextField time_of_service_up;

    @FXML
     void update_appointment(ActionEvent event) {
    int appointmentId = Integer.parseInt(appointment_id.getText());
    int duration = Integer.parseInt(service_duration_up.getText());
    Date appointmentDate = java.sql.Date.valueOf(service_date_up.getValue());
    
   
    LocalTime localTime = LocalTime.parse(time_of_service_up.getText());
    Time appointmentTime = Time.valueOf(localTime);

    boolean success = D.updateAppointment(appointmentId, duration, appointmentDate, appointmentTime);
    if (success) {
        showAlert("Success", "Appointment updated successfully.");
    } else {
        showAlert("Error", "Failed to update appointment.");
    }
}
    

  


    @FXML
    void add_service(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_add_service.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Service");

            stage.setScene(new Scene(root));

            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField staff_add_service_cost;

    @FXML
    private TextField staff_add_service_description;

    @FXML
    private TextField staff_add_service_duration;

    @FXML
    private TextField staff_add_service_name;

    @FXML
    void add_service_in_db(ActionEvent event) 
    {
        String name = staff_add_service_name.getText();
        int duration = Integer.parseInt(staff_add_service_duration.getText());
        String description = staff_add_service_description.getText();
        double cost = Double.parseDouble(staff_add_service_cost.getText());

        services newService = new services(name, duration, description, cost);

        D.insertService(newService);
    }


    @FXML
    private TableView<Appointments> appointmentsTableView;

    @FXML
    private TableColumn<Appointments, Integer> codeColumn;

    @FXML
    private TableColumn<Appointments, Integer> durationColumnn;

    @FXML
    private TableColumn<Appointments, Date> dateColumn;

    @FXML
    private TableColumn<Appointments, Date> timeColumn;

    @FXML
    private TableColumn<Appointments, Integer> staffIdColumn;

    @FXML
    private TableColumn<Appointments, String> serviceNameColumn;

    @FXML
    private TableColumn<Appointments, Integer> customerIdColumn;

    @FXML
    private TableColumn<Appointments, String> statusColumn;

    public void initializeee() {
        appointmentsTableView.setItems(D.getAppointments());
    }

    @FXML
    void admin_requested_services(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_requested_services.fxml"));
            Parent root = loader.load();

            MainSceneController reqservicesController = loader.getController();

            reqservicesController.appointmentsTableView.setItems(D.getAppointments());

            Stage stage = new Stage();
            stage.setTitle("Add Service");

            stage.setScene(new Scene(root));

            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    @FXML
    private TextField request_id;

    @FXML
    private TextField request_status;

    @FXML
    void update_queries_status(ActionEvent event) {
        String idText = request_id.getText();
        String status = request_status.getText();

       
        if (!idText.isEmpty() && !status.isEmpty()) {
            try {
                int id = Integer.parseInt(idText);
               
                Database1 database = new Database1();
                boolean updated = database.updateAppointmentStatus(id, status);

                if (updated) {
                    showAlert("Success", "Appointment status updated successfully.");
                } else {
                    showAlert("Error", "Failed to update appointment status. Appointment ID not found.");
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid appointment ID. Please enter a valid number.");
            }
        } else {
            showAlert("Error", "Please provide both appointment ID and status.");
        }
    }
   


    


    public void initializeeee() {
        appointmentsTableView.setItems(D.getAppointments());
    }

    @FXML
    void admin_schedule_appointments(ActionEvent event) 
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_show_appointments.fxml"));
            Parent root = loader.load();

            MainSceneController reqservicesController = loader.getController();

            reqservicesController.appointmentsTableView.setItems(D.getAppointmentsx());

            Stage stage = new Stage();
            stage.setTitle("Add Service");

            stage.setScene(new Scene(root));

            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }   
    }

    @FXML
    private TextField re_id;

    

    @FXML
    private TextField staff_id;

    @FXML
    void update_staff(ActionEvent event)
    {
        String  idText = re_id.getText();
        String staffId = staff_id.getText();

       
        if (!idText.isEmpty() ) {
            try {
                int id = Integer.parseInt(idText);
                int staff_id = Integer.parseInt(staffId);
               
                Database1 database = new Database1();
                boolean updated = database.updateAppointmentstaff(id, staff_id);

                if (updated) {
                    showAlert("Success", "Appointment status updated successfully.");
                } else {
                    showAlert("Error", "Failed to update appointment status. Appointment ID not found.");
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid appointment ID. Please enter a valid number.");
            }
        } else {
            showAlert("Error", "Please provide both appointment ID and status.");
        }
    }

    public void initializee() {

        appointmentsTableView.setItems(D.getAppointmentsWithId(LoggedInAcc.getLoggedInCustomerId()));
    }

    @FXML
    void customer_show_appointments(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer_show_appointments.fxml"));
            Parent root = loader.load();

            MainSceneController reqservicesController = loader.getController();
            reqservicesController.appointmentsTableView
                    .setItems(D.getAppointmentsWithId(LoggedInAcc.getLoggedInCustomerId()));

            Stage stage = new Stage();
            stage.setTitle("Add Service");

            stage.setScene(new Scene(root));

            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
