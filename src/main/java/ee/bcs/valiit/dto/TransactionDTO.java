package ee.bcs.valiit.dto;


public class TransactionDTO {

    private String from_account;
    private String to_account;
    private Object date_time;
    private double transfer;
    private double deduction;

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "from_account='" + from_account + '\'' +
                ", to_account='" + to_account + '\'' +
                ", date_time=" + date_time +
                ", transfer=" + transfer +
                ", deduction=" + deduction +
                '}';
    }

    public String getFrom_account() {
        return from_account;
    }

    public void setFrom_account(String from_account) {
        this.from_account = from_account;
    }

    public String getTo_account() {
        return to_account;
    }

    public void setTo_account(String to_account) {
        this.to_account = to_account;
    }

    public Object getDate_time() {
        return date_time;
    }

    public void setDate_time(Object date_time) {
        this.date_time = date_time;
    }

    public double getTransfer() {
        return transfer;
    }

    public void setTransfer(double transfer) {
        this.transfer = transfer;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }
}




