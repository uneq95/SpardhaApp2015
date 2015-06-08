package com.ritesh.spardha.CustomizeGallery;

/**
 * Created by Abhishek on 06-06-2015.
 */
public enum FragmentTags {
    LIST_BUDDIES("SpardhaGalerryFragment"),
    CUSTOMIZE("CustomizeFragment");

    private String text;

    private FragmentTags(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

