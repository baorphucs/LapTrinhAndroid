package com.example.lab7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtMa, txtTen;
    Button btnNhap;
    RadioGroup rdgGioiTinh;
    ImageButton btnXoa;
    ListView lvDanhSachNV;
    ArrayList<Employee> myArrayEmployee;
    MyArrayAdapter myArrayAdapter;

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
        AnhXaCacControl();

        myArrayEmployee = new ArrayList<>();
        myArrayAdapter = new MyArrayAdapter(this, R.layout.my_item_layout, myArrayEmployee);
        lvDanhSachNV.setAdapter(myArrayAdapter); //Kết nối Listview vào ArrayAdapter
        DangKyVaXuLyCacSuKien();
    }

    private void AnhXaCacControl() {
        txtMa = findViewById(R.id.txtMaNV);
        txtTen = findViewById(R.id.txtTenNV);
        btnNhap = findViewById(R.id.btnNhap);
        rdgGioiTinh = findViewById(R.id.rdgGioiTinh);
        btnXoa = findViewById(R.id.imgDelete);
        lvDanhSachNV = findViewById(R.id.lvDanhSachNhanVien);
    }

    private void DangKyVaXuLyCacSuKien() {
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyNhap();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyXoa();
            }
        });
    }

    private void XuLyNhap() {
        String ma = txtMa.getText().toString();
        String ten = txtTen.getText().toString();
        boolean gioiTinh = false;
        if (rdgGioiTinh.getCheckedRadioButtonId() == R.id.chkGioiTinhNam) {
            gioiTinh = true;
        }
        Employee emp = new Employee();
        emp.setMaNV(ma);
        emp.setTenNV(ten);
        emp.setGioiTinh(gioiTinh);
        myArrayAdapter.add(emp);
        myArrayAdapter.notifyDataSetChanged();

        txtMa.setText("");
        txtTen.setText("");
        rdgGioiTinh.clearCheck();
        txtMa.requestFocus();
    }

    private void XuLyXoa() {
         for(int i=myArrayEmployee.size()-1; i>=0; i--){

                View v = lvDanhSachNV.getChildAt(i);

                CheckBox chk = (CheckBox)v.findViewById(R.id.chkItemDelete);

                if (chk.isChecked()) {
                    myArrayEmployee.remove(i);
                }
            myArrayAdapter.notifyDataSetChanged();
            }
        }
}
