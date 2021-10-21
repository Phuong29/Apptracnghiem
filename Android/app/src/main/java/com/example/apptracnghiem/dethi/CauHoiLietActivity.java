package com.example.apptracnghiem.dethi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apptracnghiem.MainActivity;
import com.example.apptracnghiem.R;
import com.example.apptracnghiem.RecyclerViewApdapter;
import com.example.apptracnghiem.database.CauHoi;
import com.example.apptracnghiem.database.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class CauHoiLietActivity extends AppCompatActivity {

    private static final String TABLE = "dethi";
    MyDatabase myDatabase = null;
    List<CauHoi> listCH;
    RecyclerView recyclerView;
    ImageView imgBackMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi_liet);

        mapping();

        myDatabase = new MyDatabase(CauHoiLietActivity.this, "csdl_dethi.sqlite", null, 1);

        initView();

        imgBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CauHoiLietActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void mapping() {
        recyclerView = findViewById(R.id.recycler_view);
        imgBackMenu = findViewById(R.id.imgBackMenu);
    }


    public void initView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        listCH = new ArrayList<CauHoi>();
        listCH = getAll();
        RecyclerViewApdapter apdapter = new RecyclerViewApdapter(listCH, getApplicationContext());
        recyclerView.setAdapter(apdapter);
    }

    private List<CauHoi> getAll() {
        List<CauHoi> list = new ArrayList<>();
        String sql_select = "SELECT * FROM "+TABLE+" where cauliet = 1";
        Cursor c = myDatabase.SelectData(sql_select);
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String cauhoi = c.getString(1);
            String dapan_a = c.getString(2);
            String dapan_b = c.getString(3);
            String dapan_c = c.getString(4);
            String dapan_d = c.getString(5);
            String ketqua = c.getString(6);
            int madethi = c.getInt(7);
            int cauliet = c.getInt(8);
            String cautraloi = c.getString(9);
            CauHoi cauHoi = new CauHoi(id, cauhoi, dapan_a, dapan_b, dapan_c, dapan_d, ketqua, madethi, cauliet, cautraloi);
            list.add(cauHoi);
        }
        return list;
    }

}