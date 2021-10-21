package com.example.apptracnghiem.gridviewcustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptracnghiem.R;

import java.util.List;

public class GridviewMenuAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<GridviewMenu> list;

    private class ViewHolder {
        ImageView imgMenu;
        TextView tvMenu;
    }

    public GridviewMenuAdapter(Context context, int layout, List<GridviewMenu> list) {
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
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.imgMenu = convertView.findViewById(R.id.imgMenu);
            viewHolder.tvMenu  = convertView.findViewById(R.id.tvMenu);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GridviewMenu gridviewMenu = list.get(position);
        viewHolder.imgMenu.setImageResource(gridviewMenu.getImgMenu());
        viewHolder.tvMenu.setText(gridviewMenu.getNameMenu());

        return convertView;
    }
}
