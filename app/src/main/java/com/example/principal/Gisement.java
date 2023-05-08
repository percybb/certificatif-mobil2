package com.example.principal;

public class Gisement {
    private int id;
    private String nom;
    private String type;
    private double luminosite;
    private String status;
    private int util;

    public Gisement(int id, String nom, String type, double luminosite, String status, int util) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.luminosite = luminosite;
        this.status = status;
        this.util = util;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLuminosite() {
        return luminosite;
    }

    public void setLuminosite(double luminosite) {
        this.luminosite = luminosite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUtil() {
        return util;
    }

    public void setUtil(int util) {
        this.util = util;
    }
}
