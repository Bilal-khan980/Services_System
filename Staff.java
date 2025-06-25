public class Staff {
    private int id;
    private String password;
    private String name;
    private String cnic;
    private int age;
    private String address;
    private String contact_no;
    private String availability; // Represented as a single string

    public Staff(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public Staff(int id, String password, String name, String cnic, int age, String address, String contact_no,
            String availability) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.cnic = cnic;
        this.age = age;
        this.address = address;
        this.contact_no = contact_no;
        this.availability = availability;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getCnic() {
        return cnic;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getAvailability() {
        return availability;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}