package com.android.wisdom.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.wisdom.library.recy.RecycleyOneModelListActivity;
import com.android.wisdom.library.recy.RecycleyTwoModelListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_to_recyclerview_no_statue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, RecycleyOneModelListActivity.class));
            }
        });

        findViewById(R.id.bt_to_recyclerview_two_statue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, RecycleyTwoModelListActivity.class));
            }
        });
    }
}
