package com.fato.victor.fatoapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.media.MediaPlayer;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView= findViewById(R.id.listView);

        final ArrayList<Meme> memes= new ArrayList<>();
        memes.add(new Meme(R.raw.drogats, false, "image", "Drogats"));
        memes.add(new Meme(R.raw.perfecte, false, "image", "Perfecte"));
        memes.add(new Meme(R.raw.divendres, false, "image", "Divendres"));
        memes.add(new Meme(R.raw.escoltam, false, "image", "Escoltam"));
        memes.add(new Meme(R.raw.coca, false, "image", "Coca"));
        memes.add(new Meme(R.raw.pokemon, false, "image", "Pokemon"));
        memes.add(new Meme(R.raw.casallapoalaes, false, "image", "Casalla a Poalaes"));
        memes.add(new Meme(R.raw.manosaire, false, "image", "Manos en el Aire"));
        memes.add(new Meme(R.raw.piscina, false, "image", "Inaguracion Piscina"));
        memes.add(new Meme(R.raw.subierumble, false, "image", "Subie Rumble"));
        memes.add(new Meme(R.raw.once, false, "image", "ONCE"));



        Collections.sort(memes);

        MemeAdapter adapter = new MemeAdapter(this, memes);
        gridView.setAdapter(adapter);
        gridView.setActivated(true);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
//                mp.setAudioSessionId(memes.get(i).audio);
//                mp.start();
//            }
//        });
    }


}
