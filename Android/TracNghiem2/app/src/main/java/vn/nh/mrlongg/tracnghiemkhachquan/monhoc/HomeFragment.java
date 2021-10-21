package vn.nh.mrlongg.tracnghiemkhachquan.monhoc;
/**
 * Nguyễn Hoàng Long.
 * Facebook: facebook.com/87269
 * MrLongg
 * 15/07/2017
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.nh.mrlongg.tracnghiemkhachquan.MainActivity;
import vn.nh.mrlongg.tracnghiemkhachquan.R;

public class HomeFragment extends Fragment {
    Button btnStart;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("APP Trắc nghiệm Khách quan");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        btnStart = (Button) getActivity().findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartFragment homeFragment = new StartFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();



                fragmentTransaction.replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();

//                fragmentTransaction.commit();

            }
        });




    }

}
