package com.example.ktgk;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView tvText;
    ImageView mImage;
    Button btnClose;
    ListView lvDanhSach;

    String[] tenDanhSach = {
            "Đế Vương Kỷ",
            "Khai Quốc Công Tặc",
            "Truyện Cười Dân Gian",
            "Shin Kamen Rider Spirits"
    };

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

        tvText = (TextView) findViewById(R.id.tvText);
        mImage = (ImageView) findViewById(R.id.mImage);
        btnClose = (Button) findViewById(R.id.btnClose);
        lvDanhSach = (ListView) findViewById(R.id.lvDanhSach);

        lvDanhSach.setOnItemClickListener((parent, view, position, id) -> {
            LoadDataFromAssets(position);
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tenDanhSach);
        lvDanhSach.setAdapter(adapter);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void LoadDataFromAssets(int position) {
        String tenDS = tenDanhSach[position];
        String imageName = "", textFileName = "";

        switch (tenDS) {
            case "Đế Vương Kỷ":
                imageName = "de-vuong-ky.jpg";
                textFileName = "de-vuong-ky.txt";
                break;
            case "Khai Quốc Công Tặc":
                imageName = "khai-quoc-cong-tac.jpg";
                textFileName = "khai-quoc-cong-tac.txt";
                break;
            case "Truyện Cười Dân Gian":
                imageName = "truyen-cuoi-dan-gian.jpg";
                textFileName = "truyen-cuoi-dan-gian.txt";
                break;
            case "Shin Kamen Rider Spirits":
                imageName = "shin-kamen-rider-spirits.jpg";
                textFileName = "shin-kamen-rider-spirits.txt";
                break;
        }

        try {
            InputStream in = getAssets().open(textFileName);
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            in.close();
            String s = new String(buffer);
            tvText.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            InputStream inputStream = getAssets().open(imageName);
            Drawable d = Drawable.createFromStream(inputStream, null);
            mImage.setImageDrawable(d);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}