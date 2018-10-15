package com.fato.victor.fatoapp;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MemeAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private final ArrayList<Meme> versiones;
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
        return convertView;
    }

    class ViewHolder {
        Button button;



    }

    }

