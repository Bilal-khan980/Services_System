
public class LoggedInAcc {
    private static int loggedInCustomerId;
    private static int paymentid;
    public static int getLoggedInCustomerId() {
        return loggedInCustomerId;
    }

    public static void setLoggedInCustomerId(int customerId) {
        loggedInCustomerId = customerId;
    }



    public static int getpaymentid() {
        return paymentid;
    }

    public static void setpaymentid(int id) {
        paymentid = id;
    }
}