package com.example.thuchanh2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_OPTION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Tạo menu tùy chọn trong Activity
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        // Nạp các mục menu từ tập tin menu/menu.xml
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clear) {
            // Hiển thị một AlertDialog với tiêu đề và nội dung từ tài nguyên chuỗi
            AlertDialog.Builder message = new AlertDialog.Builder(this);
            message.setTitle(R.string.message_caption);
            message.setMessage(R.string.message_content);
            message.setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Khi nhấn nút đóng, nội dung trong EditText (có id là noidung) sẽ bị xóa
                    EditText et = findViewById(R.id.noidung);
                    et.setText(" ");
                }
            }).show();
        }else if (id == R.id.setting) {
            // Khởi chạy OptionActivity thông qua một Intent và yêu cầu kết quả trả về
            Intent intent = new Intent(this, Option_Activity.class);
            startActivityForResult(intent, REQUEST_CODE_OPTION);
        } else if (id == R.id.exit) {
            // Hiển thị một AlertDialog để xác nhận thoát khỏi ứng dụng
            new AlertDialog.Builder(this)
                    .setTitle(R.string.exit_caption)
                    .setMessage(R.string.exit_content)
                    .setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Nếu người dùng nhấn "Yes", ứng dụng sẽ kết thúc
                            finish();
                        }
                    })
                    .setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Không làm gì cả, chỉ đóng hộp thoại
                        }
                    })
                    .show();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OPTION && resultCode == RESULT_OK) {
            // Nhận dữ liệu từ Intent và lấy các giá trị màu sắc từ tài nguyên string array
            Bundle bundle = data.getExtras();
            int index1 = bundle.getInt("ForeColor");
            int index2 = bundle.getInt("BackColor");
            String []colorArray = getResources().getStringArray(R.array.color_array);
            // Thay đổi màu chữ (TextColor) và màu nền (BackgroundColor) của EditText dựa trên các màu được chọn
            EditText et = findViewById(R.id.noidung);
            et.setTextColor(Color.parseColor(colorArray[index1]));
            et.setBackgroundColor(Color.parseColor(colorArray[index2]));
        }
    }
}
