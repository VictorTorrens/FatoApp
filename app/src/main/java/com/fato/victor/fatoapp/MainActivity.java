package com.fato.victor.fatoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.media.MediaPlayer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

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

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
//                mp.setAudioSessionId(memes.get(i).audio);
//                mp.start();
//            }
//        });
    }


}
