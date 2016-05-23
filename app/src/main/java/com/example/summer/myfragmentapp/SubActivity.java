package com.example.summer.myfragmentapp;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        if (getResources().getConfiguration().orientation==
                Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        int position = getIntent().getIntExtra(TitlesFragment.EXTRA_POSITION,0);
        DetailFragment detailFragment = DetailFragment.newInstance(position);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.detailFrame,detailFragment)
                .commit();

    }

}
