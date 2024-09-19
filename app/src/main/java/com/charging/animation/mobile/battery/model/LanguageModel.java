package com.charging.animation.mobile.battery.model;

public class LanguageModel {
    public String languageName;
    public String code;
    public Boolean active;
    public int image;

    public LanguageModel(String languageName, String code, Boolean active, int image) {
        this.languageName = languageName;
        this.code = code;
        this.active = active;
        this.image = image;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getCheck() {
        return active;
    }

    public void setCheck(Boolean check) {
        active = check;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
