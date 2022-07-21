package com.example.petshop.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.petshop.R;
import com.example.petshop.adapter.Adapter;
import com.example.petshop.model.Cycle;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final ArrayList<Cycle> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter adapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // drawer menu settings
        drawerLayout = findViewById(R.id.dl);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.rec);
        adapter = new Adapter(list, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        list.add(new Cycle(1, "XEDUA", R.drawable.xedua));
        list.add(new Cycle(2, "Mini", R.drawable.xedapmini));
        list.add(new Cycle(3, "BanhTo", R.drawable.xebanhto));
        list.add(new Cycle(4, "BanhTo", R.drawable.xebanhto));
        list.add(new Cycle(3, "BanhTo", R.drawable.xebanhto));

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    public void play() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.starboy);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        mediaPlayer.start();
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void stop(View v) {
        stopPlayer();
    }

    private void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(this, "Media Released ", Toast.LENGTH_LONG).show();
        }
    }

    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.bluetooth:
                Toast.makeText(this, "Bluetooth", Toast.LENGTH_LONG).show();
                return true;

            case R.id.add:
                Toast.makeText(this, "add", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                startActivity(intent);
                return true;
            case R.id.action_log_out:
                intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Log out", Toast.LENGTH_LONG).show();
                return true;

            default:
                return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent = new Intent();
        switch (menuItem.getItemId()) {
            case R.id.phone:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;
            case R.id.message:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"));
                intent.putExtra("sms_body", "hello fen");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;
            case R.id.browser:
                Uri web = Uri.parse("https://google.com");
                intent = new Intent(Intent.ACTION_VIEW, web);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;
            case R.id.capture:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;
            case R.id.play:
                play();
                return true;
            case R.id.pause:
                pause();
                return true;
            case R.id.stop:
                stopPlayer();
                return true;
            default:
                return actionBarDrawerToggle.onOptionsItemSelected(menuItem);
        }


    }




}