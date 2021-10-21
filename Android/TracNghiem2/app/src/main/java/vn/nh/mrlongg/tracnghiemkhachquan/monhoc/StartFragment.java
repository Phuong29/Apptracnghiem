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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import vn.nh.mrlongg.tracnghiemkhachquan.MainActivity;
import vn.nh.mrlongg.tracnghiemkhachquan.R;



public class StartFragment extends Fragment {

    Button btnStar2;
    RadioGroup radioGroup;
    RadioButton radioTH, radioLS, radioGDCD;

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        radioGDCD = (RadioButton) getActivity().findViewById(R.id.radioGDCD);
        radioTH  = (RadioButton) getActivity().findViewById(R.id.radioTH);
        radioLS = (RadioButton) getActivity().findViewById(R.id.radioLS);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Chọn Bộ Môn");
        return inflater.inflate(R.layout.fragment_start, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        radioGroup = (RadioGroup) getActivity().findViewById(R.id.radiogroup);
        btnStar2 = (Button) getActivity().findViewById(R.id.btnStart2);
        btnStar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Vui lòng chọn Bộ đề!!!", Toast.LENGTH_SHORT).show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioLS:
                        btnStar2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                LichSuFragment homeFragment = new LichSuFragment();
                                fragmentTransaction.replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
                            }
                        });
                        break;
                    case R.id.radioTH:
                        btnStar2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                TinHocFragment homeFragment = new TinHocFragment();
                                fragmentTransaction.replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
                            }
                        });
                        break;
                    case R.id.radioGDCD:
                        btnStar2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                GDCDFragment homeFragment = new GDCDFragment();
                                fragmentTransaction.replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
                            }
                        });
                        break;
                }
            }
        });





        super.onActivityCreated(savedInstanceState);
    }

}
