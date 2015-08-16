package com.ritesh.spardha.sponsers;

/**
 * Created by ritesh_kumar on 16-Aug-15.
 */
public class Sponser {

    String filePath;
    String header;
    boolean isHeader;

    public Sponser(String filePath) {
        this.filePath = filePath;
    }

    public Sponser(String header, boolean isheader) {
        this.header = header;
        this.isHeader = isheader;
    }
}
