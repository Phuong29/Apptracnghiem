package vn.nh.mrlongg.tracnghiemkhachquan.info;

/**
 * Nguyễn Hoàng Long.
 * Facebook: facebook.com/87269
 * MrLongg
 * 15/07/2017
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.nh.mrlongg.tracnghiemkhachquan.MainActivity;
import vn.nh.mrlongg.tracnghiemkhachquan.R;

public class LienheFragment extends Fragment {


    public LienheFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Thông tin Tác giả");
        return inflater.inflate(R.layout.fragment_lienhe, container, false);


    }
}
