package mybench.parisnanterre.fr.mybench;

public class Bench {
    private double x;
    private double y;
    private double distance;
    private String nom;

    public Bench(double x, double y, String nom) {
        this.x = x;
        this.y = y;
        this.distance = 0.00;
        this.nom = nom;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
