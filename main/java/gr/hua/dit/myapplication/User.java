package gr.hua.dit.myapplication;

public class User {
    public int id;
    public double latitude;
    public double longtitude;
    public String unix_imestamp;

    public User(){

    }

    public User(double latitude,double longtitude,String unix_imestamp) {
        this.latitude = latitude;
        this.longtitude=longtitude;
        this.unix_imestamp=unix_imestamp;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public String getUnix_imestamp() {
        return unix_imestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public void setUnix_imestamp(String unix_imestamp) {
        this.unix_imestamp = unix_imestamp;
    }
}

