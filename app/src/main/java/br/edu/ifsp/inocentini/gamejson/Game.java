package br.edu.ifsp.inocentini.gamejson;

import java.util.List;

public class Game {
    private String image;
    private String name;
    private String release_date;
    private String trailer;
    private List<Plataform> plataformList;

    public Game(String image, String name, String release_date, String trailer, List<Plataform> plataformList) {
        this.image = image;
        this.name = name;
        this.release_date = release_date;
        this.trailer = trailer;
        this.plataformList = plataformList;
    }
    public Game(){

    }
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public List<Plataform> getPlataformList() {
        return plataformList;
    }

    public void setPlataformList(List<Plataform> plataformList) {
        this.plataformList = plataformList;
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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public static class Plataform {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
