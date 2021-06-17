package com.example.messedup.jokes;

public class JokeData {
    private boolean error;
    private String category;
    private String type;
    private String setup;
    private String delivery;
    private float id;
    private boolean safe;
    private String lang;


    // Getter Methods

    public boolean getError() {
        return error;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }

    public String getDelivery() {
        return delivery;
    }


    public float getId() {
        return id;
    }

    public boolean getSafe() {
        return safe;
    }

    public String getLang() {
        return lang;
    }

    // Setter Methods

    public void setError(boolean error) {
        this.error = error;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}
