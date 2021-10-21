package com.example.apptracnghiem.dethi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptracnghiem.ItemMenu;
import com.example.apptracnghiem.MenuAdapter;
import com.example.apptracnghiem.R;
import com.example.apptracnghiem.database.CauHoi;
import com.example.apptracnghiem.database.CauHoiAdapter;
import com.example.apptracnghiem.database.MyDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LamBaiThiActivity extends AppCompatActivity {
    private static final String TABLE = "dethi";
    MyDatabase myDatabase = null;

    ListView listView;
    List<CauHoi> listCH;
    CauHoiAdapter adapter;
    TextView tvTimer;
    CounterClass timer;
    int thoiGianLamBai = 60; //Phút
    Button btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lam_bai_thi);
        mapping();
        timer = new CounterClass(thoiGianLamBai * 60 * 1000, 1000);
        timer.start();

        myDatabase = new MyDatabase(LamBaiThiActivity.this, "csdl_dethi.sqlite", null, 1);

        listCH = new ArrayList<CauHoi>();
        listCH = getAll();
        adapter = new CauHoiAdapter(this, R.layout.line_cauhoi, listCH);
        listView.setAdapter(adapter);

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LamBaiThiActivity.this);
                alertDialog.setTitle("Thông báo!");
                alertDialog.setMessage("Bạn có muốn nộp bài thi!");
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Tính tổng số câu đúng và gửi sang màn hình kết quả
                        int tongSoCauDung = 0;
                        int truot = 0;
                        String ketQua, cauTraLoi;
                        for (CauHoi lch : listCH) {
                            ketQua = lch.getKetqua();
                            cauTraLoi = lch.getCauTraLoi();
                            if (ketQua.equalsIgnoreCase(cauTraLoi)) {
                                tongSoCauDung = tongSoCauDung + 1;
                            }
                            if (!ketQua.equalsIgnoreCase(cauTraLoi) && lch.getCauLiet() == 1) {
                                truot = 1;
                            }
//                    Toast.makeText(LamBaiThiActivity.this, "cau dung: "+tongSoCauDung+ "  ketQua: " + ketQua +"\n" +
//                            "cau tra loi: " +cauTraLoi, Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(LamBaiThiActivity.this, KetQuaActivity.class);
                        intent.putExtra("socaudung", tongSoCauDung);
                        intent.putExtra("truot", truot);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

    }

    private void mapping() {
        listView = findViewById(R.id.listView);
        tvTimer = findViewById(R.id.tvTimer);
        btnEnd = findViewById(R.id.btnEnd);
    }

    private List<CauHoi> getAll() {
        List<CauHoi> list = new ArrayList<>();
        String sql_select = "SELECT * FROM " + TABLE + " WHERE madethi = 1";
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


    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTimer.setText(countTime); //SetText cho textview hiện thị thời gian.
        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");  //SetText cho textview hiện thị thời gian.
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LamBaiThiActivity.this);
        alertDialog.setTitle("Thông báo!");
        alertDialog.setMessage("Bạn có muốn thoát!");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(LamBaiThiActivity.this, DeThiActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
}


