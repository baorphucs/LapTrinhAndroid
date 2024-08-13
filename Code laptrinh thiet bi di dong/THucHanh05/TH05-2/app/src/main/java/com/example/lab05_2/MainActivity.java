package com.example.lab05_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView tvMsg;
    GridView gvmyPicture;

    //mảng lưu danh sách các id hình ảnh có sẵn
    Integer[]mThumbIds; //Data Source
    //Adapter cho GridView
    MyImageAdapter myadapter = null; //Adapter

    //Vì không tạo thêm 1 Activity nên lấy luôn 2 Id ở bên show_myimage_layout.xml
    TextView tvShowMsg;
    ImageView ivmyPicture;
    Button btnBack;

    //Lưu Bundle backup cho MainActivity => Do chúng ta không dùng Intent
    Bundle myBackupBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Lưu savedInstanceState trước vào myBackupBundle
        myBackupBundle = savedInstanceState;
        //chọn lay out để hiển thị
        setContentView(R.layout.activity_main);
        get_myWidget();

        //gán mảng các Id hình ảnh cho mThumbIds
        mThumbIds = new Integer[]{R.drawable.ip15_den,R.drawable.ip15_vang,
                R.drawable.ip15pm_xanh,R.drawable.reno8_den,
                R.drawable.s22ultra_den,R.drawable.s24plus_den,
                R.drawable.s24ultra_den,
        R.drawable.xiaomi14ultra_den};

        //gắn Datasource cho Adapter
        myadapter = new MyImageAdapter(this, mThumbIds);

        //gán Adapter vào Gridview
        gvmyPicture.setAdapter(myadapter);
        set_myEvent();
    }

    private void get_myWidget()
    {
        tvMsg = (TextView)findViewById(R.id.tvMsg);
        gvmyPicture=(GridView) findViewById(R.id.gvmyPicture);
    }

    private void get_myWidget_2()
    {
        //ta lấy các control trong layout mới này (show_myimage_layout.xml)
        tvShowMsg =(TextView) findViewById(R.id.tvShowMsg);
        ivmyPicture = (ImageView) findViewById(R.id.ivmyPicture);
    }

    private void set_myEvent()
    {
        gvmyPicture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long
                    id) {
                //gọi hàm xem hình ảnh chi tiết tại ví trí thứ arg2
                showdetail(position);
            }
        });
    }

    private void showdetail(int pos)
    {
        //Không mở Activity mới mà chỉ thiết lập lại Layout (không dùng Intent)
        setContentView(R.layout.show_myimage_layout);
        //Vừa gọi hàm trên thì màn hình sẽ thay đổi qua cái mới
        get_myWidget_2();

        //Hiển thị nội dung
        tvShowMsg.setText("Image at " + pos);

        //thiết lập hình ảnh đang chọn lên ImageView mới
        ivmyPicture.setImageResource(mThumbIds[pos]);

        btnBack=(Button) findViewById(R.id.btnBack);

        //Thiết lập sự kiện click Back để phục hồi lại MainActivity
        //bằng cách gọi lại onCreate(myBackupBundle);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                //Phục hồi Bundlde đã lưu trước đó
                onCreate(myBackupBundle);
            }
        });
    }
}