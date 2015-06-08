package com.ritesh.spardha.CustomizeGallery;

/**
 * Created by Abhishek on 06-06-2015.
 */
public enum ExtraArgumentKeys {
    OPEN_ACTIVITES("OPEN_ACTIVITES");

    private String text;

    private ExtraArgumentKeys(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

