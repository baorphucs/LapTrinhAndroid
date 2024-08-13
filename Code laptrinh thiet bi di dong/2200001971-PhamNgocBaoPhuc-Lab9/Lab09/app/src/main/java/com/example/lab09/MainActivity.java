package com.example.lab09;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
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
        LoadDataFromAssets();
    }
    private void LoadDataFromAssets(){
        try {
            InputStream in = getAssets().open("TacGiaPushkin.txt");
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            in.close();
            String s = new String(buffer);
            tvText.setText(s);
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
        try {
            InputStream inputStream = getAssets().open("Pushkin.jpg");
            Drawable d = Drawable.createFromStream(inputStream,null);
            mImage.setImageDrawable(d);
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

}