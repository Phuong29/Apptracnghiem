package com.example.apptracnghiem.database;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptracnghiem.ItemMenu;
import com.example.apptracnghiem.MenuAdapter;
import com.example.apptracnghiem.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CauHoiAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CauHoi> list;


    private class ViewHolder {
        TextView tvCauHoi;
        TextView tvViTri;
        RadioButton radio_a, radio_b, radio_c, radio_d;
        RadioGroup radioGroup;
    }

    public CauHoiAdapter(Context context, int layout, List<CauHoi> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.tvCauHoi = convertView.findViewById(R.id.tvCauHoi);
            viewHolder.tvViTri = convertView.findViewById(R.id.tvViTri);
            viewHolder.radio_a = convertView.findViewById(R.id.radio_a);
            viewHolder.radio_b = convertView.findViewById(R.id.radio_b);
            viewHolder.radio_c = convertView.findViewById(R.id.radio_c);
            viewHolder.radio_d = convertView.findViewById(R.id.radio_d);
            viewHolder.radioGroup = convertView.findViewById(R.id.radioGroup);
            viewHolder.radioGroup.clearCheck();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int vitri = position + 1;
        viewHolder.tvViTri.setText("C??u h???i "+vitri+":");
        viewHolder.tvCauHoi.setText(list.get(position).getCauHoi());
        viewHolder.radio_a.setText(list.get(position).getDapan_a());
        viewHolder.radio_b.setText(list.get(position).getDapan_b());
        viewHolder.radio_c.setText(list.get(position).getDapan_c());
        viewHolder.radio_d.setText(list.get(position).getDapan_d());

        viewHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //L???y gi?? tr??? id c???a radio trong radio group
                list.get(position).choiceIdRadio = checkedId;
                //G??n c??u tr??? l???i v??o trong ?????i t?????ng
                list.get(position).setCauTraLoi(getChoiceIdRadio(checkedId));
//                Toast.makeText(context,"b???n ???? ch???n ????p ??n: " + checkedId, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    //H??m chuy???n id radio => ????p ??n A, B, C ho???c D
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


