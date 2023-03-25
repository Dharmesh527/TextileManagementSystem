import java.sql.*;

public class ClothDAO {

    public static void addCloth(int pick, int weftCount, int warpCount, int warpEnds, int width, int pickRate, int read, String clothName) throws SQLException {
        String query="INSERT INTO cloth "
                + "(Cloth_nick_name,Pick,Weft_count,"
                + "Warp_count,Warp_ends,Width,Pick_rate,read_) "
                + "VALUES ('"+clothName+"',"
                +pick+","+weftCount+","+warpCount+","+warpEnds+
                ","+width+","+pickRate+","+read+");";
        Connection con=ConnectionDB.createConnection();
        Statement stmt=con.createStatement();
        stmt.execute(query);
    }

}
