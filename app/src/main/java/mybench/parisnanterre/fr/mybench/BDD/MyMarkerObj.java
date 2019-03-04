
package mybench.parisnanterre.fr.mybench.BDD;


import java.util.ArrayList;
import java.util.List;

public class MyMarkerObj {
    private int id;
    private String title;
    private String position;
    private String environnement;
    private String pollution;
    private String bruit;


    public MyMarkerObj() {

    }

    public MyMarkerObj(int id, String title, String position,String environnement,String pollution,String bruit) {
        this.id = id;
        this.title = title;
        this.position = position;
        this.environnement = environnement;
        this.pollution = pollution;
        this.bruit = bruit;

    }
    public MyMarkerObj(int id)
    {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEnvironnement() {
        return environnement;
    }

    public void setEnvironnement(String environnement) {
        this.environnement = environnement;
    }

    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
    }

    public String getBruit() {
        return bruit;
    }

    public void setBruit(String bruit) {
        this.bruit = bruit;
    }




}
