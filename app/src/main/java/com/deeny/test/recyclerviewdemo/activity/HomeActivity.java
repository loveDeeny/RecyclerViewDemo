package com.deeny.test.recyclerviewdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.deeny.test.recyclerviewdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.bt_toTwinkling)
    public void twinklingClick(View view){
        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_addOrRemove)
    public void addOrRemoveClick(View view){
        Intent intent = new Intent(HomeActivity.this,AddOrRemoveActivity.class);
        startActivity(intent);
    }
}
