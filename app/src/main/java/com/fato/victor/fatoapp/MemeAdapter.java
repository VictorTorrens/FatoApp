package com.fato.victor.fatoapp;

import static android.os.Environment.DIRECTORY_RINGTONES;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.core.content.FileProvider;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MemeAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private final ArrayList<Meme> versiones;
    private final String authorities = "com.fato.victor.fatoapp.fileprovider";
    MediaPlayer mp;


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

//            holder.name = convertView.findViewById(R.id.textViewName);
//            holder.number = convertView.findViewById(R.id.textViewNumber);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Meme version = getItem(position);
        holder.button.setText(version.getName());
        final View finalConvertView = convertView;
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(finalConvertView.getContext(), version.getAudio());
                mp.start();
            }
        });
        final View finalConvertView1 = convertView;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        holder.button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                String downloadPath = Environment.getExternalStoragePublicDirectory(DIRECTORY_RINGTONES).getAbsolutePath() + "/";

                try{
                    InputStream in = finalConvertView1.getResources().openRawResource(version.getAudio());
                    File audio = new File(downloadPath + version.name);
                    FileUtils.copyInputStreamToFile(in, audio);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return false;
            }
        });
        return convertView;
    }

    class ViewHolder {
        Button button;

    }

    }

