package com.example.thuchanh2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class Option_Activity extends MainActivity {
    private int index1 = 0, index2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index1 = arg2; // lấy index user chọn
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index2 = arg2; // lấy index user chọn
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
    public void onOK(View view) {
        // gởi dữ liệu về activity trước
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("ForeColor", index1); // lấy giá trị màu text
        bundle.putInt("BackColor", index2); // lấy giá trị màu nền
        intent.putExtras(bundle); // gởi kèm dữ liệu
        setResult(RESULT_OK, intent); // gởi kết quả về
        finish(); // đóng activity
    }
}