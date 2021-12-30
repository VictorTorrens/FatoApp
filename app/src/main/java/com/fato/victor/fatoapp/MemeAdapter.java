package com.fato.victor.fatoapp;


import static androidx.core.content.FileProvider.getUriForFile;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MemeAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private final ArrayList<Meme> versiones;
    MediaPlayer mp;
    String ultimoAudio = "";


    public MemeAdapter(Context context, List<Meme> vers) {
        super();
        this.mInflater = LayoutInflater.from(context);
        this.versiones = (ArrayList<Meme>) vers;
    }

    public int getCount() {
        return versiones.size();
    }

    public Meme getItem(int position) {
        return versiones.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.buttonlist, null);
            holder = new ViewHolder();
            holder.button = convertView.findViewById(R.id.button3);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Meme version = getItem(position);
        holder.button.setText(version.getName());
        final View finalConvertView = convertView;

        holder.button.setOnClickListener(v -> {
            boolean playing = false;

            if (mp != null) { //No nulo
                if (mp.isPlaying()){ //Esta reproduciendo
                    String test = version.getName();
                    if(test.equals(ultimoAudio)){ //Igual audio anterior
                        ultimoAudio ="";
                        playing =true;
                    }
                    mp.stop();
                }
            }
            if (!playing) {
                mp = MediaPlayer.create(finalConvertView.getContext(), version.getAudio());
                mp.start();
                ultimoAudio = version.getName();

            }

        });
        final View finalConvertView1 = convertView;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        holder.button.setOnLongClickListener(v -> {

            File sound;
            try {
                InputStream inputStream = finalConvertView1.getResources().openRawResource(version.getAudio());
                sound = File.createTempFile(version.getName(), ".mp3");
                copyFile(inputStream, new FileOutputStream(sound));
            } catch (IOException e) {
                throw new RuntimeException("Can't create temp file", e);
            }

            final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";
            Uri uri = getUriForFile(finalConvertView1.getContext().getApplicationContext(), AUTHORITY, sound);
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("audio/mp3"); // or whatever.
            share.putExtra(Intent.EXTRA_STREAM, uri);
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            finalConvertView1.getContext().startActivity(Intent.createChooser(share, "Share"));

            return false;
        });
        return convertView;
    }

    static class ViewHolder {
        Button button;

    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
            out.write(buffer, 0, read);
    }
}

