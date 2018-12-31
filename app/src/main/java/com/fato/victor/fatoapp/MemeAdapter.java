package com.fato.victor.fatoapp;

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        final View finalConvertView1 = convertView;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        holder.button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String pathSDCard = Environment.getExternalStorageDirectory() + "/Android/data/" + version.getName()+".mp3";
                try{
                    InputStream in = finalConvertView1.getResources().openRawResource(version.getAudio());
                    FileOutputStream out = null;
                    out = new FileOutputStream(pathSDCard);
                    byte[] buff = new byte[1024];
                    int read = 0;
                    try {
                        while ((read = in.read(buff)) > 0) {
                            out.write(buff, 0, read);
                        }
                    } finally {
                        in.close();
                        out.close();

                        Intent shareMedia = new Intent(Intent.ACTION_SEND);
                        //set WhatsApp application.
                        shareMedia.setType("audio/*");
                        //set path of media file in ExternalStorage.
                        shareMedia.putExtra(Intent.EXTRA_STREAM, Uri.parse(pathSDCard));
                        finalConvertView1.getContext().startActivity(Intent.createChooser(shareMedia, "Compartiendo archivo."));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


//                InputStream inputStream;
//                FileOutputStream fileOutputStream;
//                try {
//                    inputStream = finalConvertView1.getResources().openRawResource(version.getAudio());
//                    fileOutputStream = new FileOutputStream(
//                            new File(Environment.getExternalStorageDirectory(), version.getName()));
//
//                    byte[] buffer = new byte[1024];
//                    int length;
//                    while ((length = inputStream.read(buffer)) > 0) {
//                        fileOutputStream.write(buffer, 0, length);
//                    }
//
//                    inputStream.close();
//                    fileOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_STREAM,
//                        Uri.parse("file://" + Environment.getExternalStorageDirectory() + "/" + version.getName() + ".mp3" ));
//                intent.setType("audio/*");
//                finalConvertView1.getContext().startActivity(Intent.createChooser(intent, "Share sound"));



//                final Intent audIntent = new Intent(android.content.Intent.ACTION_SEND);
//                audIntent.setType("audio/mp3");
//                audIntent.putExtra(android.content.Intent.EXTRA_STREAM, version.getAudio());
//                finalConvertView1.getContext().startActivity(Intent.createChooser(audIntent, "Share Audio "));
                return false;
            }
        });
        return convertView;
    }

    class ViewHolder {
        Button button;



    }

    }

