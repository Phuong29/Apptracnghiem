package com.example.apptracnghiem.dethi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.apptracnghiem.MainActivity;
import com.example.apptracnghiem.R;

import java.util.ArrayList;

public class DeThiActivity extends AppCompatActivity {

    ImageView imgBackMenu;
    ListView lvDeThi;
    ArrayList<String> listLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_thi);

        mapping();

        acctionListview();

        imgBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeThiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void acctionListview() {
        listLv = new ArrayList<>();
        listLv.add("Đề thi số 1");
        listLv.add("Đề thi số 2");

        ArrayAdapter arrayAdapter = new ArrayAdapter(DeThiActivity.this, android.R.layout.simple_list_item_1, listLv);
        lvDeThi.setAdapter(arrayAdapter);

        lvDeThi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        //Chuyển màn hình
                        intent = new Intent(DeThiActivity.this, DeThi1Activity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //Chuyển màn hình
                        intent = new Intent(DeThiActivity.this, DeThi2Activity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void mapping() {
        imgBackMenu = findViewById(R.id.imgBackMenu);
        lvDeThi = findViewById(R.id.lvDeThi);
    }
}