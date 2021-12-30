package com.fato.victor.fatoapp;

import static android.os.Environment.DIRECTORY_RINGTONES;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String downloadPath = Environment.getExternalStoragePublicDirectory(DIRECTORY_RINGTONES).getAbsolutePath() + "/";

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        GridView gridView= findViewById(R.id.listView);

        final ArrayList<Meme> memes= new ArrayList<>();
        memes.add(new Meme(R.raw.albertoover9000, false, "image", "Alberto9000"));
        memes.add(new Meme(R.raw.animomarika, false, "image", "Animo Marika"));
        memes.add(new Meme(R.raw.bontorreblanqui, false, "image", "Bon Torreblanqui"));
        memes.add(new Meme(R.raw.buitre, false, "image", "Buitre"));
        memes.add(new Meme(R.raw.caretadefato, false, "image", "Cara de Fato"));
        memes.add(new Meme(R.raw.chuuu, false, "image", "Chuu"));
        memes.add(new Meme(R.raw.coca, false, "image", "Coca"));
        memes.add(new Meme(R.raw.cuantomepagas, false, "image", "Cuanto me pagas"));
        memes.add(new Meme(R.raw.depredador, false, "image", "Depredador"));
        memes.add(new Meme(R.raw.depredador9000, false, "image", "Depredador9000"));
        memes.add(new Meme(R.raw.divendres, false, "image", "Divendres"));
        memes.add(new Meme(R.raw.drogats, false, "image", "Drogats"));
        memes.add(new Meme(R.raw.esanoesfea, false, "image", "Esa NO es fea"));
        memes.add(new Meme(R.raw.escoltam, false, "image", "Escoltam"));
        memes.add(new Meme(R.raw.fetiche, false, "image", "Fetiche"));
        memes.add(new Meme(R.raw.fieshta, false, "image", "Fieshta"));
        memes.add(new Meme(R.raw.fillsdeputes, false, "image", "Fillsdeputes"));
        memes.add(new Meme(R.raw.gusanito, false, "image", "Gusanito"));
        memes.add(new Meme(R.raw.hueleavicio, false, "image", "Huele a vicio"));
        memes.add(new Meme(R.raw.joelnieve, false, "image", "Jhoel Nieve"));
        memes.add(new Meme(R.raw.jugos, false, "image", "Jugos"));
        memes.add(new Meme(R.raw.lospenesylosculos, false, "image", "Penes y Culos"));
        memes.add(new Meme(R.raw.mortdefam, false, "image", "Mort de Fam"));
        memes.add(new Meme(R.raw.noalasdrogas, false, "image", "No a las Drogas"));
        memes.add(new Meme(R.raw.once, false, "image", "ONCE"));
        memes.add(new Meme(R.raw.perfecte, false, "image", "Perfecte"));
        memes.add(new Meme(R.raw.perra, false, "image", "Perra"));
        memes.add(new Meme(R.raw.pokemon, false, "image", "Pokemon"));
        memes.add(new Meme(R.raw.siempretowing, false, "image", "Siempre Towing"));
        memes.add(new Meme(R.raw.siii, false, "image", "Sii!"));
        memes.add(new Meme(R.raw.sitieneeso, false, "image", "Si tiene eso"));
        memes.add(new Meme(R.raw.subierumble, false, "image", "Subie Rumble"));
        memes.add(new Meme(R.raw.suenaprrra, false, "image", "La cosa suena Prra"));
        memes.add(new Meme(R.raw.tesenota, false, "image", "Te Se Nota"));
        memes.add(new Meme(R.raw.vayamaricon, false, "image", "Vaya Maricon"));
        memes.add(new Meme(R.raw.vinebuscandocobre, false, "image", "Vine buscando cobre..."));
        memes.add(new Meme(R.raw.whip, false, "image", "Latigo"));

        Collections.sort(memes);

        MemeAdapter adapter = new MemeAdapter(this, memes);
        gridView.setAdapter(adapter);
        gridView.setActivated(true);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        // Should we show an explanation?
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
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
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, Latigo.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
