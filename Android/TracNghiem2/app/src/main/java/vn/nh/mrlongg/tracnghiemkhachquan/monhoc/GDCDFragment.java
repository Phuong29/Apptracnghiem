package vn.nh.mrlongg.tracnghiemkhachquan.monhoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import vn.nh.mrlongg.tracnghiemkhachquan.MainActivity;
import vn.nh.mrlongg.tracnghiemkhachquan.R;
import vn.nh.mrlongg.tracnghiemkhachquan.slide.ScreenSlideActivity;

/**
 * Nguyễn Hoàng Long.
 * Facebook: facebook.com/87269
 * MrLongg
 * 15/07/2017
 */
public class GDCDFragment extends Fragment {
    ExamAdapter examAdapter;
    GridView gvExam;
    ArrayList<vn.nh.mrlongg.tracnghiemkhachquan.monhoc.Exam> arr_exam= new ArrayList<vn.nh.mrlongg.tracnghiemkhachquan.monhoc.Exam>();
    public GDCDFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Bộ đề GDCD");
        return inflater.inflate(R.layout.fragment_toan_hoc, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gvExam=(GridView) getActivity().findViewById(R.id.gvExam);
        arr_exam.add(new vn.nh.mrlongg.tracnghiemkhachquan.monhoc.Exam("Đề số 1"));
        arr_exam.add(new vn.nh.mrlongg.tracnghiemkhachquan.monhoc.Exam("Đề số 2"));
        arr_exam.add(new vn.nh.mrlongg.tracnghiemkhachquan.monhoc.Exam("Đề số 3"));
        arr_exam.add(new vn.nh.mrlongg.tracnghiemkhachquan.monhoc.Exam("Đề số 4"));
        arr_exam.add(new vn.nh.mrlongg.tracnghiemkhachquan.monhoc.Exam("Đề số 5"));
        arr_exam.add(new vn.nh.mrlongg.tracnghiemkhachquan.monhoc.Exam("Đề số 6"));


        examAdapter=new ExamAdapter(getActivity(),arr_exam);
        gvExam.setAdapter(examAdapter);
        gvExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ScreenSlideActivity.class);
                intent.putExtra("num_exam", i + 1);
                intent.putExtra("subject", "GDCD");
                intent.putExtra("test", "yes");
                startActivity(intent);
            }
        });

    }
}
