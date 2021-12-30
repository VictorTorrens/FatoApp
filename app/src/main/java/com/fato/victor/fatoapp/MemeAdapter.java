package com.fato.victor.fatoapp;

import static android.os.Environment.DIRECTORY_RINGTONES;

import static androidx.core.content.FileProvider.getUriForFile;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.provider.DocumentsContract;
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
import java.io.OutputStream;
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

                File sound;
                try {
                    InputStream inputStream = finalConvertView1.getResources().openRawResource(version.getAudio()); // equivalent to R.raw.yoursound
                    sound = File.createTempFile("sound", ".mp3");
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
//                InputStream inputStream = finalConvertView1.getResources().openRawResource(version.getAudio());
//                try {
//                    FileUtils.copyInputStreamToFile(inputStream, version.getFile());
//                } catch (IOException e) {/data/data/com.fato.victor.fatoapp/cache/sound6868849873939089799.mp3
//                    e.printStackTrace();
//                }
//
//                Uri path=FileProvider.getUriForFile(finalConvertView1.getContext(), authorities, version.getFile());
//
//                Intent intent = new  Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_TITLE, path);
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                intent.setType("audio/mp3");
//                finalConvertView1.getContext().startActivity(Intent.createChooser(intent, "Share..."));

   //             String downloadPath = FileProvider.getUriForFile(this, authorities, version.get);

//                try{
//                    ParcelFileDescriptor pfd = finalConvertView1.getContext().getContentResolver(),finalConvertView1.getContext().op
//                    InputStream in = finalConvertView1.getResources().openRawResource(version.getAudio());
//                    File audio = new File(downloadPath + version.name);
//                    FileUtils.copyInputStreamToFile(in, audio);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                return false;
            }
        });
        return convertView;
    }

    class ViewHolder {
        Button button;

    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1)
            out.write(buffer, 0, read);
    }
    }

