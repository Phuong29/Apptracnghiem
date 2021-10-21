package vn.nh.mrlongg.tracnghiemkhachquan.slide;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.nh.mrlongg.tracnghiemkhachquan.R;
import vn.nh.mrlongg.tracnghiemkhachquan.question.Question;

import static vn.nh.mrlongg.tracnghiemkhachquan.slide.ScreenSlideActivity.timer;

/**
 * Nguyễn Hoàng Long.
 * Facebook: facebook.com/87269
 * MrLongg
 * 15/07/2017
 */
public class ScreenSlidePageFragment extends Fragment {

    ArrayList<Question> arr_Ques;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    public int mPageNumber; // vị trí trang hiện tại
    public int checkAns;   // biến kiểm tra ...

    TextView tvNum, tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;
    ImageView imgIcon;
    Button btnTiep, btnLui;


    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        tvNum = (TextView) rootView.findViewById(R.id.tvNum);
        tvQuestion = (TextView) rootView.findViewById(R.id.tvQuestion);
        radA = (RadioButton) rootView.findViewById(R.id.radA);
        radB = (RadioButton) rootView.findViewById(R.id.radB);
        radC = (RadioButton) rootView.findViewById(R.id.radC);
        radD = (RadioButton) rootView.findViewById(R.id.radD);
        imgIcon=(ImageView) rootView.findViewById(R.id.ivIcon) ;
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radGroup);
        btnTiep = (Button) rootView.findViewById(R.id.tiep);
        btnLui = (Button) rootView.findViewById(R.id.lui);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arr_Ques = new ArrayList<Question>();
        vn.nh.mrlongg.tracnghiemkhachquan.slide.ScreenSlideActivity slideActivity = (vn.nh.mrlongg.tracnghiemkhachquan.slide.ScreenSlideActivity) getActivity();
        arr_Ques = slideActivity.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE);
        checkAns= getArguments().getInt(ARG_CHECKANSWER);

    }

    public static ScreenSlidePageFragment create(int pageNumber, int checkAnswer) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putInt(ARG_CHECKANSWER,checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNum.setText("Câu " + (mPageNumber + 1));
        tvQuestion.setText(arr_Ques.get(mPageNumber).getQuestion());

        radA.setText("A. " + getItem(mPageNumber).getAns_a());
        radB.setText("B. " + getItem(mPageNumber).getAns_b());
        radC.setText("C." + getItem(mPageNumber).getAns_c());
        radD.setText("D." + getItem(mPageNumber).getAns_d());

        imgIcon.setImageResource(getResources().getIdentifier(getItem(mPageNumber).getImage()+"","drawable","vn.nh.mrlongg.tracnghiemkhachquan"));



        if(checkAns!=0){
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getCheckAns(getItem(mPageNumber).getResult().toString());
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getItem(mPageNumber).choiceID = checkedId;
                getItem(mPageNumber).setTraloi(getChoiceFromID(checkedId));

//                Toast.makeText(getActivity(),"Đây là đáp án "+ checkedId, Toast.LENGTH_SHORT).show();
            }
        });
        btnTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ScreenSlideActivity.mPager.getCurrentItem() == 19){
                    Toast.makeText(getActivity(), "Đã hết câu hỏi ở bộ đề này !!!", Toast.LENGTH_SHORT).show();
                }else {
                    ScreenSlideActivity.mPager.setCurrentItem(ScreenSlideActivity.mPager.getCurrentItem() + 1);
                }
            }
        });
        btnLui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ScreenSlideActivity.mPager.getCurrentItem() == 0){
                    final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setIcon(R.drawable.exit);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn thoát phần kiểm tra này hay không?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            timer.cancel();
                            getActivity().finish();
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    builder.show();


                }else {
                    ScreenSlideActivity.mPager.setCurrentItem(ScreenSlideActivity.mPager.getCurrentItem() - 1);
                }
            }
        });

    }

    public Question getItem(int posotion){
        return arr_Ques.get(posotion);
    }

    //Lấy giá trị (vị trí) radiogroup chuyển thành đáp án A/B/C/D
    private String getChoiceFromID(int ID) {
        if (ID == R.id.radA) {
            return "A";
        } else if (ID == R.id.radB) {
            return "B";
        } else if (ID == R.id.radC) {
            return "C";
        } else if (ID == R.id.radD) {
            return "D";
        } else return "";
    }

    //Hàm kiểm tra câu đúng, nếu câu đúng thì đổi màu background radiobutton tương ứng
    private void getCheckAns(String ans){
        if(ans.equals("A")==true){
            radA.setBackgroundColor(Color.RED);
        }
        else if(ans.equals("B")==true){
            radB.setBackgroundColor(Color.RED);
        }else if(ans.equals("C")==true){
            radC.setBackgroundColor(Color.RED);
        }else if(ans.equals("D")==true){
            radD.setBackgroundColor(Color.RED);
        }else ;
    }


}
