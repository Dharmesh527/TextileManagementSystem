import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction{// todo chage as dao
    private float meter,amount,warpBalance,weftBalance,amountCredit, weftConsumed,weight,amountBalance;

    public Transaction(float meter,float weight) {
        this.meter = meter;
        this.weight=weight;
    }

    public Transaction(float amount) {
        this.amount=amount;
    }

    //    public void insertCreditedCloth(String companyName, String clothName) {
//        String qry="SELECT pick,weft_count,warp_count,width,pick_rate,read_ FROM cloth"
//                + " WHERE cloth_nick_name='"+clothName+" ';";
//        ResultSet rs=cDB.fetchFromDb(qry);
//    }
    public void insertCreditedCloth(String companyName, String clothName) throws SQLException {
        TransactionDAO.insertCreditedCloth(companyName,clothName,meter);
    }

    public void insertWeftCredit(String companyName, String clothName) throws SQLException {
        TransactionDAO.insertWeftCredit(companyName,clothName,weight);
    }

    public void insertWarpCredit(String companyName) throws SQLException {
        TransactionDAO.insertWarpCredit(companyName,meter);
    }

    public void insertAmountDebit(String companyName) throws SQLException {
        TransactionDAO.insertAmountDebit(amount,companyName);
    }
}