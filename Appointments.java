import java.util.Date;

public class Appointments {
    private int code;
    private int duration;
    private Date date;
    private Date time;
    private int staffId;
    private String serviceName;
    private int customerId;
    private String status; 

  
    public Appointments(int code, int duration, Date date, Date time, int staffId, String serviceName, int customerId, String status) {
        this.code = code;
        this.duration = duration;
        this.date = date;
        this.time = time;
        this.staffId = staffId;
        this.customerId = customerId;
        this.serviceName = serviceName;
        this.status = status; 
    }

  
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
