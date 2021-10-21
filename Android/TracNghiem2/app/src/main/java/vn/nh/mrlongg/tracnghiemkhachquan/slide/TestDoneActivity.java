package vn.nh.mrlongg.tracnghiemkhachquan.slide;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.nh.mrlongg.tracnghiemkhachquan.R;
import vn.nh.mrlongg.tracnghiemkhachquan.question.Question;
import vn.nh.mrlongg.tracnghiemkhachquan.score.ScoreController;
/**
 * Nguyễn Hoàng Long.
 * Facebook: facebook.com/87269
 * MrLongg
 * 15/07/2017
 */

public class TestDoneActivity extends AppCompatActivity {

    ArrayList<Question> arr_QuesBegin= new ArrayList<Question>();
    int numallAns = 20; // tổng câu hỏi
    int numNoAns=0; // số câu chưa trả lời
    int numTrue=0; // số câu trả lời đúng
    int numFalse=0; // số câu trả lời sai
    int totalScore=0; // số điểm
    ScoreController scoreController;
    TextView tvTrue, tvFalse, tvNotAns, tvAns, tvTotalScore;
    Button btnSaveScore, btnAgain, btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);
         scoreController=new ScoreController(TestDoneActivity.this);
        final Intent intent= getIntent();
        arr_QuesBegin= (ArrayList<Question>) intent.getExtras().getSerializable("arr_Ques");
        begin();
        checkResult();
        int numAns = numallAns - numNoAns;  // tính số câu đã trả lời
        totalScore= numTrue*5; // số điểm = số câu đúng * 10
        tvNotAns.setText(""+numNoAns); // settext câu chưa trả lời
        tvAns.setText(""+ numAns); // settext câu đã trả lời
        tvFalse.setText(""+numFalse); // settext câu trả lời sai
        tvTrue.setText(""+numTrue); // settext câu trả lời đúng
        tvTotalScore.setText(""+totalScore); // settex điểm
        // check "bạn có muốn thoát"
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(TestDoneActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát hay không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
        // dialog check lưu điểm
        btnSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(TestDoneActivity.this);
                LayoutInflater inflater=TestDoneActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.allert_dialog_save_score,null);
                builder.setView(view);
                final EditText edtName= (EditText) view.findViewById(R.id.edtName);
                TextView tvScore= (TextView) view.findViewById(R.id.tvScore);
                final int numTotal= numTrue*5;
                tvScore.setText(numTotal+" điểm");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edtName.getText().toString();
                        if (name.equals("")) {
                            Toast.makeText(TestDoneActivity.this, "Bạn chưa nhập Tên kìa!!!", Toast.LENGTH_SHORT).show();
                        } else if ((name.equals("") == false)){
                            scoreController.insertScore(name, numTotal);
                            Toast.makeText(TestDoneActivity.this, "Lưu điểm thành công!", Toast.LENGTH_LONG).show();
                            finish();
                            dialog.dismiss();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog b= builder.create();
                b.show();
            }
        });
        // check chơi lại
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
                finish();
                Intent intent2= new Intent(TestDoneActivity.this, ScreenSlideActivity.class);
                intent2.putExtra("arr_Ques",arr_QuesBegin);
                intent2.putExtra("test","no");
                startActivity(intent2);
            }
        });

    }
    public void refresh(){
        for(int i=0; i<arr_QuesBegin.size(); i++){
            arr_QuesBegin.get(i).setTraloi("");
        }
    }
    // ánh xạ các view
    public void begin(){
        tvFalse= (TextView)findViewById(R.id.tvFalse);
        tvTrue= (TextView)findViewById(R.id.tvTrue);
        tvNotAns= (TextView)findViewById(R.id.tvNotAns);
        tvAns= (TextView)findViewById(R.id.tvAns);
        tvTotalScore= (TextView)findViewById(R.id.tvTotalPoint);
        btnAgain=(Button)findViewById(R.id.btnAgain);
        btnSaveScore=(Button)findViewById(R.id.btnSaveScore);
        btnExit=(Button)findViewById(R.id.btnExit);
    }

    // Check kết quả đúng sai hoặc câu chưa trả lời => show ra bảng điểm
    public void checkResult(){
        for(int i=0; i< arr_QuesBegin.size(); i++){
            if(arr_QuesBegin.get(i).getTraloi().equals("")==true){
                numNoAns++;
            }else if(arr_QuesBegin.get(i).getResult().equals(arr_QuesBegin.get(i).getTraloi())==true){
                numTrue++;
            }else if(arr_QuesBegin.get(i).getResult().equals(arr_QuesBegin.get(i).getTraloi()) == false) {
                numFalse++;
            }
        }
    }

}
