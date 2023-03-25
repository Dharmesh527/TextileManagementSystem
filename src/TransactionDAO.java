import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDAO {
    public static void insertCreditedCloth(String companyName, String clothName,float meter) throws SQLException {
        // clth details
        String query="SELECT pick,weft_count,warp_count,width,pick_rate,read_ FROM cloth"
                + " WHERE cloth_nick_name='"+clothName+" ';";
        Connection con=ConnectionDB.createConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            float pick=rs.getInt("pick");
            float weftCount=rs.getInt("weft_count");
            float warpCount=rs.getInt("warp_count");
            float width=rs.getInt("width");
            float pickRate=rs.getInt("pick_rate");
            float read=rs.getInt("read_");
        }

//to take blance:
        String qryAmountBalance="SELECT amount_balance FROM transaction " +
                "where company_name = '"+companyName+"' ORDER BY date DESC;";
        ResultSet rsAmountBalance=stmt.executeQuery(qryAmountBalance);
        float  amt=0;
        while(rsAmountBalance.next()) {
            amt =rsAmountBalance.getFloat("amount_balance");
            if(amt>0)
                break;
        }
        float amountBalance=amt;

        //to take warp_balance
        String qryWarpBalance="SELECT warp_balance FROM transaction where company_name = '"+companyName+
                "' ORDER BY date DESC ;";
        ResultSet rsWarpBalance=stmt.executeQuery(qryWarpBalance);
        float wab=0;
        while(rsWarpBalance.next()) {
            wab=rsWarpBalance.getFloat("warp_balance");
            if(wab>0)
                break;
        }
        float warpBalance=wab;

        //to take weft_balance
        String qryWeftBalance="SELECT weft_balance FROM transaction where company_name = '"
                +companyName+"' and cloth_nick_name = '"+clothName+"' ORDER BY date DESC;";
        ResultSet rsWeftBalance=stmt.executeQuery(qryWeftBalance);
        float web=0;
        while(rsWeftBalance.next()) {
            web=rsWeftBalance.getFloat("weft_balance");
            if(web>0)
                break;
        }
        float weftBalance=web;
        //insert
        float weftConsumed = meter/10;
        float amountCredit= meter*10;
        String qryInsert="INSERT INTO transaction "
                + "(date,company_name,cloth_nick_name,trans_id,amount_credit,amount_balance,"
                + "warp_credit,"+"warp_balance, weft_credit,weft_balance"
                + ") VALUES(now(),'"+companyName+"','"+ clothName+"',"+"'CLREC',"
                +amountCredit+","+(amountBalance)+","+meter+","
                +warpBalance+","+weftConsumed+","+weftBalance+");";
        System.out.println(qryInsert);
        stmt.execute(qryInsert);
    }

    public static void insertWeftCredit(String companyName, String clothName, float weight) throws SQLException {
        String qryWeftBalance="SELECT "+"weft_balance"+
                " FROM transaction WHERE company_name ='"+  companyName+
                "' and Cloth_nick_name like'%"+  clothName+"%' ORDER BY date DESC;";
        System.out.println(qryWeftBalance);
        Connection con=ConnectionDB.createConnection();
        Statement stmt=con.createStatement();
        ResultSet rs_weft_balance =stmt.executeQuery(qryWeftBalance);
        float data=0;
        while(rs_weft_balance.next()) {
            data=rs_weft_balance.getFloat("weft_balance");
            System.out.println(data);
            if(data>0)
                break;
        }
        float weftBalance =  weight+data;

        String qryInsert="INSERT INTO transaction (date,company_name,Cloth_nick_name,trans_id," +
                "weft_debit,weft_balance" +
                ") VALUES (now(), '"+  companyName+"','"+  clothName+" ','WYDLV',"
                +  weight+","+  weftBalance+");";
        System.out.println(qryInsert);
        stmt.execute(qryInsert);

    }

    public static void insertWarpCredit(String companyName, float meter) throws SQLException {
        String qryWarpBalance="SELECT warp_balance FROM transaction " +
                "where company_name='"+  companyName+"' ORDER BY " +
                "date DESC;";
        Connection con=ConnectionDB.createConnection();
        Statement stmt=con.createStatement();
        ResultSet rsWeftBalance=stmt.executeQuery(qryWarpBalance);
        float wrpb=0;
        while(rsWeftBalance.next()) {
            wrpb=rsWeftBalance.getFloat("warp_balance");
            if(Math.abs(wrpb)>0)
                break;
        }
        float warpBalance=wrpb;
        String query="INSERT INTO transaction (date,company_Name,trans_id,warp_debit,warp_balance )VALUES (now(),'"+  companyName
                        + "','WPDLV',"+  meter+","+(  meter+  warpBalance)+");";
        System.out.println(query);
        stmt.execute(query);
    }

    public static void insertAmountDebit(float amount, String companyName) throws SQLException {
        String qryAmountBalance="SELECT amount_balance FROM transaction where company_name"
                +"='"+  companyName+"' ORDER BY date DESC ;";
        Connection con=ConnectionDB.createConnection();
        Statement stmt=con.createStatement();
        ResultSet rsWeftBalance=stmt.executeQuery(qryAmountBalance);
        float f=0;
        while(rsWeftBalance.next()) {
            f=rsWeftBalance.getFloat("amount_balance");
            if(Math.abs(f)>0)
                break;
        }
        String query="INSERT INTO transaction (date,company_Name,trans_id," +
                        "amount_debit,amount_balance)VALUES (now(),'"+  companyName
                        + "','WAGES',"+  amount+","+(f-  amount)+");";
        stmt.execute(query);
    }
}
