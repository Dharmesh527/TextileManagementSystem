import java.sql.SQLException;

public class Cloth {
    private int pick,weftCount,warpCount
            ,warpEnds,width,pickRate,read;

    public Cloth(int pick, int weftCount, int warpCount, int warpEnds, int width, int pickRate, int read, String clothName) {
        this.pick = pick;
        this.weftCount = weftCount;
        this.warpCount = warpCount;
        this.warpEnds = warpEnds;
        this.width = width;
        this.pickRate = pickRate;
        this.read = read;
        this.clothName = clothName;
    }
    public boolean addCloth(){
        try{
            ClothDAO.addCloth(pick,weftCount,warpCount,warpEnds,width,pickRate,read,clothName);
            return true;
        } catch (SQLException e) {
            System.out.println("Creation Failed\n"+e);
        }
        return  false;
    }

    public int getPick() {
        return pick;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }

    public int getWeftCount() {
        return weftCount;
    }

    public void setWeftCount(int weftCount) {
        this.weftCount = weftCount;
    }

    public int getWarpCount() {
        return warpCount;
    }

    public void setWarpCount(int warpCount) {
        this.warpCount = warpCount;
    }

    public int getWarpEnds() {
        return warpEnds;
    }

    public void setWarpEnds(int warpEnds) {
        this.warpEnds = warpEnds;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPickRate() {
        return pickRate;
    }

    public void setPickRate(int pickRate) {
        this.pickRate = pickRate;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public String getClothName() {
        return clothName;
    }

    public void setClothName(String clothName) {
        this.clothName = clothName;
    }

    private String clothName;
}
