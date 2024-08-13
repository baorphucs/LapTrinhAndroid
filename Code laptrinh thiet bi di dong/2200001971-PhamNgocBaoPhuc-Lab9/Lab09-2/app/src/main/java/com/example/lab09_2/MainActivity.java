package com.example.lab09_2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SeekBar sbSound, sbBrightness;
    RadioButton rdEasy, rdMedium, rdHard;
    Button btnSave, btnRead;

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
        // Khởi tạo các thành phần giao diện
        sbSound = (SeekBar) findViewById(R.id.sbSound);
        sbBrightness = (SeekBar) findViewById(R.id.sbBrightness);
        rdEasy = (RadioButton) findViewById(R.id.rdEasy);
        rdMedium = (RadioButton) findViewById(R.id.rdMedium);
        rdHard = (RadioButton) findViewById(R.id.rdHard);
        btnSave = (Button) findViewById(R.id.btnSave);

        // Đọc dữ liệu từ SharedPreferences khi khởi động
        ReadDataFromSharedPreferences();

        // Đăng ký sự kiện cho nút "Save"
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {SaveDataToSharedPreferences();}
        });
    }

    private void ReadDataFromSharedPreferences() {
        SharedPreferences sp = getSharedPreferences("setting.xml", MODE_PRIVATE);

        // Lấy giá trị từ SharedPreferences
        int sound = sp.getInt("sound", 0);
        int brightness = sp.getInt("brightness", 10);
        String difficulty = sp.getString("difficulty", "Easy");

        // Thiết lập giá trị cho các thành phần giao diện
        sbSound.setProgress(sound);
        sbBrightness.setProgress(brightness);
        if (difficulty.equals("Easy")) {
            rdEasy.setChecked(true);
        } else if (difficulty.equals("Medium")) {
            rdMedium.setChecked(true);
        } else if (difficulty.equals("Hard")) {
            rdHard.setChecked(true);
        }

        Toast.makeText(this, "Read", Toast.LENGTH_SHORT).show();
    }

    private void SaveDataToSharedPreferences() {
        SharedPreferences sp = getSharedPreferences("setting.xml", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // Lấy giá trị từ các thành phần giao diện
        int sound = sbSound.getProgress();
        int brightness = sbBrightness.getProgress();
        String difficulty = "Easy";
        if (rdHard.isChecked()) {
            difficulty = "Hard";
        } else if (rdMedium.isChecked()) {
            difficulty = "Medium";
        }

        // Lưu giá trị vào SharedPreferences
        editor.putInt("sound", sound);
        editor.putInt("brightness", brightness);
        editor.putString("difficulty", difficulty);
        editor.commit();

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }
}
