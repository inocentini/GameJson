package br.edu.ifsp.inocentini.gamejson;

public class Game {
    private String image;
    private String name;
    private String release_data;

    public Game(String image, String name, String release_data) {
        this.image = image;
        this.name = name;
        this.release_data = release_data;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_data() {
        return release_data;
    }

    public void setRelease_data(String release_data) {
        this.release_data = release_data;
    }
}
