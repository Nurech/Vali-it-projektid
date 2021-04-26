package ee.bcs.valiit.hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "table_transfer")
@Entity
public class TransactionEntity {
    @Id
    //auto increment not ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transferId;
    private String fromAccount;
    private String toAccount;
    private double transfer;
    private double deduction;
    private LocalDateTime dateTime;

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
