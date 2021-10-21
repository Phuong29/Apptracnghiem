package com.example.apptracnghiem;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptracnghiem.database.CauHoi;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewApdapter extends RecyclerView.Adapter<RecyclerViewApdapter.ViewHolder>{

    List<CauHoi> list;
    Context context;

    public RecyclerViewApdapter(List<CauHoi> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.line_cauhoi,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int vitri = position + 1;
        holder.tvViTri.setText("Câu hỏi "+vitri+":");
        holder.tvCauHoi.setText(list.get(position).getCauHoi());
        holder.radio_a.setText(list.get(position).getDapan_a());
        holder.radio_b.setText(list.get(position).getDapan_b());
        holder.radio_c.setText(list.get(position).getDapan_c());
        holder.radio_d.setText(list.get(position).getDapan_d());

        //Tắt sự kiện click vào radio
        holder.radio_a.setClickable(false);
        holder.radio_b.setClickable(false);
        holder.radio_c.setClickable(false);
        holder.radio_d.setClickable(false);

        if(list.get(position).getKetqua().equalsIgnoreCase("a")) {
            holder.radio_a.setChecked(true);
        } else if(list.get(position).getKetqua().equalsIgnoreCase("b")) {
            holder.radio_b.setChecked(true);
        } else if(list.get(position).getKetqua().equalsIgnoreCase("c")) {
            holder.radio_c.setChecked(true);
        } else if(list.get(position).getKetqua().equalsIgnoreCase("d")) {
            holder.radio_d.setChecked(true);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCauHoi;
        TextView tvViTri;
        RadioButton radio_a, radio_b, radio_c, radio_d;
        RadioGroup radioGroup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCauHoi = itemView.findViewById(R.id.tvCauHoi);
            tvViTri = itemView.findViewById(R.id.tvViTri);
            radio_a = itemView.findViewById(R.id.radio_a);
            radio_b = itemView.findViewById(R.id.radio_b);
            radio_c = itemView.findViewById(R.id.radio_c);
            radio_d = itemView.findViewById(R.id.radio_d);
            radioGroup = itemView.findViewById(R.id.radioGroup);
        }
    }

    //Hàm chuyển id radio => đáp án A, B, C hoặc D
    private String getChoiceIdRadio(int id) {
        if (id == R.id.radio_a) {
            return "a";
        } else if (id == R.id.radio_b) {
            return "b";
        } else if (id == R.id.radio_c) {
            return "c";
        } else if (id == R.id.radio_d) {
            return "d";
        } else
            return "";
    }
}
