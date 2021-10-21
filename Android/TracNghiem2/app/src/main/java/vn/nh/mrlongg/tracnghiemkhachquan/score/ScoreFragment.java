package vn.nh.mrlongg.tracnghiemkhachquan.score;

/**
 * Nguyễn Hoàng Long.
 * Facebook: facebook.com/87269
 * MrLongg
 * 15/07/2017
 */
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.nh.mrlongg.tracnghiemkhachquan.MainActivity;
import vn.nh.mrlongg.tracnghiemkhachquan.R;

public class ScoreFragment extends Fragment {

    ListView lvScore;
    vn.nh.mrlongg.tracnghiemkhachquan.score.ScoreController scoreController;
    vn.nh.mrlongg.tracnghiemkhachquan.score.ScoreAdapter scoreAdapter;

    public ScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Danh sách điểm");
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        scoreController=new vn.nh.mrlongg.tracnghiemkhachquan.score.ScoreController(getActivity());
        lvScore=(ListView) getActivity().findViewById(R.id.lvScore);
        Cursor cursor=scoreController.getScore();
        scoreAdapter=new vn.nh.mrlongg.tracnghiemkhachquan.score.ScoreAdapter(getActivity(),cursor,true);
        lvScore.setAdapter(scoreAdapter);
    }
}
