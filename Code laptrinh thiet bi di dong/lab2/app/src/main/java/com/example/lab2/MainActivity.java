package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvNhap, ketqua;
    Button btnDelete, btn7, btn8, btn9, btnChia, btn4, btn5, btn6, btnNhan,
            btn1, btn2, btn3, btnTru, btnCham, btn0, btnBang, btnCong;

    // Biến lưu trữ giá trị và phép tính
    String input = "";
    double number1 = 0, number2 = 0;
    char operator = ' ';
    boolean isNewCalculation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view từ XML
        tvNhap = findViewById(R.id.tvNhap);
        ketqua = findViewById(R.id.ketqua);
        btnDelete = findViewById(R.id.btnDelete);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnChia = findViewById(R.id.btnChia);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btnNhan = findViewById(R.id.btnNhan);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btnTru = findViewById(R.id.btnTru);
        btnCham = findViewById(R.id.btnCham);
        btn0 = findViewById(R.id.btn0);
        btnBang = findViewById(R.id.btnBang);
        btnCong = findViewById(R.id.btnCong);

        // Thiết lập sự kiện click cho các nút số và toán tử
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        // Thiết lập sự kiện click cho các nút số
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = ((Button) v).getText().toString();
                // Nếu là lần tính toán mới và số nhập vào là số đầu tiên
                if (isNewCalculation) {
                    input = buttonText;
                    isNewCalculation = false;
                } else {
                    input += buttonText;
                }
                tvNhap.setText(input);
            }
        };

        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);

        // Thiết lập sự kiện click cho các nút toán tử
        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lưu số thứ nhất nếu đã có số và toán tử được chọn
                if (!isNewCalculation) {
                    if (!input.isEmpty()) {
                        number1 = Double.parseDouble(input); // Lưu số thứ nhất
                    }
                    isNewCalculation = true;  // Đánh dấu là tính toán mới bắt đầu
                }

                operator = ((Button) v).getText().toString().charAt(0);
                input = "";  // Reset input để nhập số tiếp theo
            }
        };

        btnCong.setOnClickListener(operatorClickListener);
        btnTru.setOnClickListener(operatorClickListener);
        btnNhan.setOnClickListener(operatorClickListener);
        btnChia.setOnClickListener(operatorClickListener);

        // Thiết lập sự kiện click cho nút xóa
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = "";
                number1 = 0;
                number2 = 0;
                operator = ' ';
                isNewCalculation = true;
                tvNhap.setText("0");
                ketqua.setText("");
            }
        });

        // Thiết lập sự kiện click cho nút bằng
        btnBang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu input không rỗng, thực hiện tính toán
                if (!input.isEmpty()) {
                    number2 = Double.parseDouble(input);
                    calculate();
                    isNewCalculation = true;
                }
            }
        });

        // Thiết lập sự kiện click cho nút dấu chấm
        btnCham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.contains(".")) {
                    input += ".";
                    tvNhap.setText(input);
                }
            }
        });
    }

    private void calculate() {
        switch (operator) {
            case '+':
                number1 += number2;
                break;
            case '-':
                number1 -= number2;
                break;
            case 'x':
                number1 *= number2;
                break;
            case '÷':
                if (number2 != 0) {
                    number1 /= number2;
                } else {
                    ketqua.setText("Error");
                }
                break;
        }

        ketqua.setText(String.valueOf(number1));
        tvNhap.setText("");
        input = "";
    }
}
