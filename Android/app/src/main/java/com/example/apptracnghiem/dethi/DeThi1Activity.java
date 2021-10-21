package com.example.apptracnghiem.dethi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.apptracnghiem.R;
import com.example.apptracnghiem.database.CauHoi;
import com.example.apptracnghiem.database.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class DeThi1Activity extends AppCompatActivity {

    private static final String TABLE = "dethi";
    MyDatabase myDatabase = null;

    Button btnThi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_thi1);
        myDatabase = new MyDatabase(DeThi1Activity.this, "csdl_dethi.sqlite", null, 1);

//        String sql_create_table = "CREATE TABLE IF NOT EXISTS "+TABLE+" (id integer primary key AUTOINCREMENT, cauhoi text, dapan_a text, dapan_b text, danan_c text, dapan_d text, ketqua text, madethi integer, cauliet integer, cautraloi text)";
//        myDatabase.excuteSQL(sql_create_table);
//        CauHoi cauHoi = new CauHoi(0, "Việt Nam ở đâu?", "Châu Âu", "Châu Mỹ", "Châu Á", "Châu Đại Dương", "c", 1, 0, "");
//        CauHoi cauHoi2 = new CauHoi(0, "1+1 = ?", "2", "3", "4", "5", "a", 1,0, "");
//        CauHoi cauHoi3 = new CauHoi(0, "100 / 2 = ?", "40", "50", "60", "70", "b", 1,0, "");
////        insertData(cauHoi);
////        insertData(cauHoi2);
////        insertData(cauHoi3);
////        printData(TABLE);

        btnThi = findViewById(R.id.btnThi);
        btnThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeThi1Activity.this, LamBaiThiActivity.class);
                startActivity(intent);
            }
        });

    }

    private void insertData(CauHoi cauHoi) {
        String sql_insert_data = "insert into "+TABLE+" values(null, '"+cauHoi.getCauHoi()+"', '"+cauHoi.getDapan_a()+"', '"+cauHoi.getDapan_b()+"', '"+cauHoi.getDapan_c()+"', '"+cauHoi.getDapan_d()+"', '"+cauHoi.getKetqua()+"', "+cauHoi.getMadethi()+", "+cauHoi.getCauLiet()+",'"+cauHoi.getCauTraLoi()+"')";
        myDatabase.excuteSQL(sql_insert_data);
        Toast.makeText(this, "TẢI DỮ LIỆU KIỂM TRA THÀNH CÔNG!f", Toast.LENGTH_SHORT).show();
    }

    private void printData(String table) {
        String sql_select = "SELECT * FROM "+table;
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
            CauHoi cauHoi = new CauHoi(id, cauhoi, dapan_a, dapan_b, dapan_c, dapan_d, ketqua, madethi, cauliet,cautraloi);
            Toast.makeText(this, cauHoi.toString() , Toast.LENGTH_LONG).show();
        }
    }

    private List<CauHoi> getAll() {
        List<CauHoi> list = new ArrayList<>();
        String sql_select = "SELECT * FROM "+TABLE;
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