package es.webservice.modelo;
import java.io.Serializable;

public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String artist;
    private double price;

    public Album() {
        super();
    }

    public Album(String id, String title, String artist, double price) {
        super();
        this.artist = artist;
        this.id = id;
        this.price = price;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
