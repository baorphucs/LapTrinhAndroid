package com.example.bai1;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private EditText editA,editB;
    private TextView ketqua;
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
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        ketqua = findViewById(R.id.ketqua);
    }
    private double getNumberFromEditText(EditText editText) {
        String text = editText.getText().toString();
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public void tong(View view) {
        double a = getNumberFromEditText(editA);
        double b = getNumberFromEditText(editB);
        double result = a + b;
        ketqua.setText(String.valueOf(result));
    }
    public void hieu(View view) {
        double a = getNumberFromEditText(editA);
        double b = getNumberFromEditText(editB);
        double result = a - b;
        ketqua.setText(String.valueOf(result));
    }

    public void tich(View view) {
        double a = getNumberFromEditText(editA);
        double b = getNumberFromEditText(editB);
        double result = a * b;
        ketqua.setText(String.valueOf(result));
    }

    public void thuong(View view) {
        double a = getNumberFromEditText(editA);
        double b = getNumberFromEditText(editB);
        if (b != 0) {
            double result = a / b;
            ketqua.setText(String.valueOf(result));
        } else {
            ketqua.setText("Không chia được cho 0");
        }
    }
}