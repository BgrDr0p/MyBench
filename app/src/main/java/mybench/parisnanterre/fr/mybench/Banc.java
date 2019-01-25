package mybench.parisnanterre.fr.mybench;

// classe qui décrit les elements scomposant un banc
public class Banc {

    private String titre;
    private double latitude;
    private double longitude;
    private String info;

    public Banc() {};

    public Banc(String titre,double latitude,double longitude,String info){

        this.titre = titre;
        this.latitude = latitude;
        this.longitude = longitude;
        this.info = info;
    }

        // correspond au title des Markers
    public String getTitre() {
        return  this.titre;

    }
    public double getLatitude(){
        return  this.latitude;
    }
    public double getLongitude(){
        return this.longitude;
    }
    public String getInfo(){
        return this.info;
    }


    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
