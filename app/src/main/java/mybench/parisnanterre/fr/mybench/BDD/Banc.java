package mybench.parisnanterre.fr.mybench.BDD;

// classe qui décrit les elements scomposant un banc
public class Banc {

    private int id;
    private String titre;
    private double latitude;
    private double longitude;
    private String info;

    public Banc(int id) {this.id = id;};

    public Banc(int id,String titre,double latitude,double longitude,String info){

        this.id = id;
        this.titre = titre;
        this.latitude = latitude;
        this.longitude = longitude;
        this.info = info;
    }

    public int getID()
    {
        return this.id;
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

    public void setId(int id)
    {
        this.id = id;
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


    @Override
    public String toString()
    {
        return "Id " + this.id + "Titre " + this.titre + "Latitude " + this.latitude + "Longitude" + this.longitude + "info" + this.info;
    }
}
