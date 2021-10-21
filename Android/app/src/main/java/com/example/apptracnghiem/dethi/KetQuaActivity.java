package com.example.apptracnghiem.dethi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptracnghiem.MainActivity;
import com.example.apptracnghiem.R;

public class KetQuaActivity extends AppCompatActivity {

    TextView tvSoCauDung, tvDiem, tvThongBao;
    EditText edtName;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);

        mapping();

        //Nhận dữ liệu câu đúng từ màn hình làm bài thi và tính điểm
        Intent intent = getIntent();
        int soCauDung = intent.getIntExtra("socaudung", 0);
        int truot = intent.getIntExtra("truot", 0);
        int soDiem = soCauDung * 10;

        //Hiện thị ra màn hình
        if (truot == 1) {
            tvSoCauDung.setText("0");
            tvDiem.setText("0");
            tvThongBao.setVisibility(View.VISIBLE);
        } else {
            tvSoCauDung.setText(soCauDung + "");
            tvDiem.setText(soDiem + "");
        }

        //Gán sự kiện click cho nút thoát
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(KetQuaActivity.this, MainActivity.class);
                    startActivity(intent);
            }
        });
    }

    //Hàm ánh xạ
    private void mapping() {
        tvSoCauDung = findViewById(R.id.tvSoCauDung);
        tvDiem = findViewById(R.id.tvDiem);
        btnExit = findViewById(R.id.btnExit);
        tvThongBao = findViewById(R.id.tvThongBao);
    }
}