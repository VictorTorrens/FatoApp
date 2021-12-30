package com.fato.victor.fatoapp;

public class Meme implements Comparable<Meme>{
    final int audio;
    final boolean img;
    final String image;
    final String name;

    public Meme(int audio, boolean img, String image, String name) {
        this.audio = audio;
        this.img = img;
        this.image = image;
        this.name = name;
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
