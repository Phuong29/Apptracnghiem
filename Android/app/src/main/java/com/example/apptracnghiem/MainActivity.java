package com.example.apptracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptracnghiem.database.CauHoi;
import com.example.apptracnghiem.database.MyDatabase;
import com.example.apptracnghiem.dethi.CauHoiLietActivity;
import com.example.apptracnghiem.dethi.DeThi1Activity;
import com.example.apptracnghiem.dethi.DeThi2Activity;
import com.example.apptracnghiem.dethi.DeThiActivity;
import com.example.apptracnghiem.dethi.MeoThiActivity;
import com.example.apptracnghiem.dethi.OnTapCauHoiActivity;
import com.example.apptracnghiem.gridviewcustom.GridviewMenu;
import com.example.apptracnghiem.gridviewcustom.GridviewMenuAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView lvNavi;
    GridView gvMenu;
    ArrayList<ItemMenu> listItemMenu;
    ArrayList<GridviewMenu> listGridviewMenu;
    MenuAdapter adapter;
    GridviewMenuAdapter gvAdapter;

    private static final String TABLE = "dethi";
    MyDatabase myDatabase = null;
    private static final String KEY_CHECK_INSTALL = "KEY_FIRST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MySharedPreferences mySharedPreferences = new MySharedPreferences(this);
        if (mySharedPreferences.getBooleanValue(KEY_CHECK_INSTALL)) {

        } else {
            myDatabase = new MyDatabase(MainActivity.this, "csdl_dethi.sqlite", null, 1);
            String sql_create_table = "CREATE TABLE IF NOT EXISTS "+TABLE+" (id integer primary key AUTOINCREMENT, cauhoi text, dapan_a text, dapan_b text, danan_c text, dapan_d text, ketqua text, madethi integer, cauliet integer, cautraloi text)";
            myDatabase.excuteSQL(sql_create_table);

            CauHoi cauHoi = new CauHoi(0, "Người điều khiển phương tiện giao thông đường bộ mà trong cơ thể có chất ma túy có bị nghiêm cấm hay không?", "Bị nghiêm cấm", "Không bị nghiêm cấm", "Không bị nghiêm cấm, nếu có chất ma túy ở mức nhẹ, có thể điều khiển phương tiện tham gia giao thông.", "", "a", 1, 1, "");
            CauHoi cauHoi2 = new CauHoi(0, "Hành vi điều khiển xe cơ giới chạy quá tốc độ quy định, giành đường, vượt ẩu có bị nghiêm cấm hay không?", "Bị nghiêm cấm tùy từng trường hợp", "Không bị nghiêm cấm", "Bị nghiêm cấm.", "", "c", 1,0, "");
            CauHoi cauHoi3 = new CauHoi(0, "Bạn đang lái xe phía trước có một xe cứu thương đang phát tín hiệu ưu tiên bạn có được phép vượt hay không?", "Không được vượt", "Được vượt khi đang đi trên cầu", "Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông", "Được vượt khi đảm bảo an toàn.", "a", 1,0, "");
            CauHoi cauHoi4 = new CauHoi(0, "Ở phần đường dành cho người đi bộ qua đường, trên cầu, đầu cầu, đường cao tốc, đường hẹp, đường dốc, tại nơi đường bộ giao nhau cùng mức với đường sắt có được quay đầu xe hay không?", "Được phép", "Không được phép", "Tùy từng trường hợp.", "", "b", 1,0, "");
            CauHoi cauHoi5 = new CauHoi(0, "Người điều khiển xe mô tô hai bánh, ba bánh, xe gắn máy có được phép sử dụng xe để kéo hoặc đẩy các phương tiện khác khi tham gia giao thông không?", "Được phép", "Nếu phương tiện được kéo, đẩy có khối lượng nhỏ hơn phương tiện của mình", "Tùy trường hợp", "Không được phép.", "d", 1,0, "");
            CauHoi cauHoi6 = new CauHoi(0, "Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy có được phép không?", "Được phép", "Tùy trường hợp", "Không được phép.", "", "c", 1,0, "");
            CauHoi cauHoi7 = new CauHoi(0, "Người ngồi trên xe mô tô hai bánh, ba bánh, xe gắn máy khi tham gia giao thông có được mang, vác vật cồng kềnh hay không?", "Được mang, vác tùy trường hợp cụ thể", "Không được mang, vác", "Được mang, vác nhưng phải đảm bảo an toàn", "Được mang, vác tùy theo sức khỏe của bản thân.", "b", 1,1, "");
            CauHoi cauHoi8 = new CauHoi(0, "Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được bám, kéo hoặc đẩy các phương thiện khác không?", "Được phép", "Được bám trong trường hợp phương tiện của mình bị hỏng", "Được kéo, đẩy trong trường hợp phương tiện khác bị hỏng", "Không được phép.", "d", 1,1, "");
            CauHoi cauHoi9 = new CauHoi(0, "Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được sử dụng ô khi trười mưa hay không?", "Được sử dụng", "Chỉ người ngồi sau được sử dụng", "Không được sử dụng", "Không được sử dụng nếu không có áo mưa", "d", 1,0, "");
            CauHoi cauHoi10 = new CauHoi(0, " Người điều khiển xe mô tô hai bánh, xe gắn máy có được đi xe dàn hàng ngang; đi xe vào phần đường dành cho người đi bộ và phương tiện khác; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính) hay không?", "Được phép nhưng phảo đảm bảo an toàn", "Không được phép", "Được phép tùy từng hoàn cảnh, điều kiện cụ thể.", "", "2", 1,0, "");
//            CauHoi cauHoi11 = new CauHoi(0, "", "", "", "", "", "", 1,0, "");
            insertData(cauHoi);
            insertData(cauHoi2);
            insertData(cauHoi3);
            insertData(cauHoi4);
            insertData(cauHoi5);
            insertData(cauHoi6);
            insertData(cauHoi7);
            insertData(cauHoi8);
            insertData(cauHoi9);
            insertData(cauHoi10);
            Toast.makeText(this, "TẢI DỮ LIỆU KIỂM TRA THÀNH CÔNG!", Toast.LENGTH_SHORT).show();
            mySharedPreferences.putBooleanValue(KEY_CHECK_INSTALL, true);
        }

        //Gọi hàm Ánh xạ
        mapping();
        //Gọi hàm Tạo sự kiện cho ToolBar
        acctionToolBar();
        //Gọi hàm tạo sự kiện cho Menu
        actionItemMenu();
        //Gọi hàm tạo sự kiện cho MenuGridView
        actionGridviewMenu();


    }

    //Hàm tạo menu và sự kiện cho Menu
    private void actionItemMenu() {
        //Thêm tên và icon các dòng menu
        listItemMenu = new ArrayList<>();
        listItemMenu.add(new ItemMenu("Đề Thi", R.drawable.ic_action_question));
        listItemMenu.add(new ItemMenu("Ôn tập câu hỏi", R.drawable.ic_action_question));
        listItemMenu.add(new ItemMenu("Câu hỏi liệt", R.drawable.ic_action_question));
        listItemMenu.add(new ItemMenu("Mẹo thi", R.drawable.ic_action_tips));

        //Tạo đối tượng MenuAdapter
        adapter = new MenuAdapter(this, R.layout.line_item_menu, listItemMenu);

        //Truyền adapter vào listview
        lvNavi.setAdapter(adapter);

        //Sự kiện chọn menu trên listview
        lvNavi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        //Chuyển màn hình
                        intent = new Intent(MainActivity.this, DeThiActivity.class);
                        startActivity(intent);

                        //Ẩn menu vào trong khi chuyển màn hình mới
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        //Chuyển màn hình
                        intent = new Intent(MainActivity.this, OnTapCauHoiActivity.class);
                        startActivity(intent);

                        //Ẩn menu vào trong khi chuyển màn hình mới
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        //Chuyển màn hình
                        intent = new Intent(MainActivity.this, CauHoiLietActivity.class);
                        startActivity(intent);

                        //Ẩn menu vào trong khi chuyển màn hình mới
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        //Chuyển màn hình
                        intent = new Intent(MainActivity.this, MeoThiActivity.class);
                        startActivity(intent);

                        //Ẩn menu vào trong khi chuyển màn hình mới
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    //Hàm tạo menu và sự kiện cho MenuGridView
    private void actionGridviewMenu() {
        //Thêm tên và icon các dòng menu
        listGridviewMenu = new ArrayList<>();
        listGridviewMenu.add(new GridviewMenu(R.drawable.dethi, "Đề thi"));
        listGridviewMenu.add(new GridviewMenu(R.drawable.listcauhoi, "Ôn tập câu hỏi"));
        listGridviewMenu.add(new GridviewMenu(R.drawable.list20cauliet, "Câu hỏi liệt"));
        listGridviewMenu.add(new GridviewMenu(R.drawable.meo, "Mẹo làm bài"));

        //Tạo đối tượng GridviewMenuAdapter
        gvAdapter = new GridviewMenuAdapter(this, R.layout.layout_gridview, listGridviewMenu);

        //Truyền adapter vào gridview
        gvMenu.setAdapter(gvAdapter);

        //Sự kiện chọn menu trên gridview
        gvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        //Chuyển màn hình
                        intent = new Intent(MainActivity.this, DeThiActivity.class);
                        startActivity(intent);

                        //Ẩn menu vào trong khi chuyển màn hình mới
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        //Chuyển màn hình
                        intent = new Intent(MainActivity.this, OnTapCauHoiActivity.class);
                        startActivity(intent);

                        //Ẩn menu vào trong khi chuyển màn hình mới
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        //Chuyển màn hình
                        intent = new Intent(MainActivity.this, CauHoiLietActivity.class);
                        startActivity(intent);

                        //Ẩn menu vào trong khi chuyển màn hình mới
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        //Chuyển màn hình
                        intent = new Intent(MainActivity.this, MeoThiActivity.class);
                        startActivity(intent);

                        //Ẩn menu vào trong khi chuyển màn hình mới
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }


    //Hàm tạo sự kiện cho ToolBar
    private void acctionToolBar() {
        //Chỉ định Toolbar được bắt sự kiện
        setSupportActionBar(toolbar);

        //Ẩn nội dung của toolbar khi màn hình được bật lên
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Gán icon cho Navigation
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);

        //Sự kiện khi click chuột vào icon của toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //Hàm ánh xạ
    private void mapping() {
        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        lvNavi = findViewById(R.id.lvNavi);
        gvMenu = findViewById(R.id.gvMenu);
    }

    private void insertData(CauHoi cauHoi) {
        String sql_insert_data = "insert into "+TABLE+" values(null, '"+cauHoi.getCauHoi()+"', '"+cauHoi.getDapan_a()+"', '"+cauHoi.getDapan_b()+"', '"+cauHoi.getDapan_c()+"', '"+cauHoi.getDapan_d()+"', '"+cauHoi.getKetqua()+"', "+cauHoi.getMadethi()+", "+cauHoi.getCauLiet()+",'"+cauHoi.getCauTraLoi()+"')";
        myDatabase.excuteSQL(sql_insert_data);
    }

    private List<CauHoi> getAll() {
        List<CauHoi> list = new ArrayList<>();
        String sql_select = "SELECT * FROM "+TABLE;
        Cursor c = myDatabase.SelectData(sql_select);
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String cauhoi = c.getString(1);
            String dapan_a = c.getString(2);
            String dapan_b = c.getString(3);
            String dapan_c = c.getString(4);
            String dapan_d = c.getString(5);
            String ketqua = c.getString(6);
            int madethi = c.getInt(7);
            int cauliet = c.getInt(8);
            String cautraloi = c.getString(9);
            CauHoi cauHoi = new CauHoi(id, cauhoi, dapan_a, dapan_b, dapan_c, dapan_d, ketqua, madethi, cauliet, cautraloi);
            list.add(cauHoi);
        }
        return list;
    }
}