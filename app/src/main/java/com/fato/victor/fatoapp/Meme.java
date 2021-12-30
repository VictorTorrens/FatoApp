package com.fato.victor.fatoapp;

import java.io.File;

public class Meme implements Comparable<Meme>{
    int audio;
    boolean img;
    String image;
    String name;
    File file;

    public Meme(int audio, boolean img, String image, String name, File file) {
        this.audio = audio;
        this.img = img;
        this.image = image;
        this.name = name;
        this.file = file;
    }

    public File getFile() {
        return file;
    }
    public int getAudio() {
        return audio;
    }

    public boolean isImg() {
        return img;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Meme o) {
        return name.compareToIgnoreCase(o.name);
    }
}
