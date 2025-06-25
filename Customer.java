public class Customer {
    int id;
    String password;
    String name;
    String cnic;
    int age;
    String address;
    String contact_no;

    Database1 D=new Database1();
    
    public Customer()
    {

    }

    public Customer(int id, String password, String name, String cnic, int age, String address,String contact_no) 
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.cnic = cnic;
        this.age = age;
        this.address = address;
        this.contact_no = contact_no;
    }

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

    public String getContact_no()
    {
        return contact_no;
    }

    public void display_customer_info() {
        System.out.println("ID: " + id);
        System.out.println("NAME : " + name);
        System.out.println("CNIC: " + cnic);
        System.out.println("AGE : " + age);
        System.out.println("ADDRESS : " + address);
    }

    public void StoreCustomer(Customer C) 
    {
        D.insertCustomer(null);
    }

}