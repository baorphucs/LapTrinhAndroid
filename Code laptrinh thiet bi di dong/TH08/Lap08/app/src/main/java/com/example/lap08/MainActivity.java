package com.example.lap08;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnThem, btnCapNhat, btnXoa, btnTimKiem;
    EditText edId, edMaSv, edHoVaTen;
    ListView lvDanhSachSinhVien;
    DataSqlite dataSqlite;
    ArrayList<String> arrayListSV;

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
        KhoiTaoDatabase();
        LoadData();

        lvDanhSachSinhVien.setOnItemClickListener((parent, view, position, id) -> {
            String sv = arrayListSV.get(position);
            String[] arr = sv.split(" - ");
            edId.setText(arr[0]);
            edMaSv.setText(arr[1]);
            edHoVaTen.setText(arr[2]);
        });

        btnThem.setOnClickListener(v -> {
            if (edMaSv.getText().toString().isEmpty() || edHoVaTen.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                String sqlThem = "INSERT INTO QLSV(maSV, tenSV) VALUES(?, ?)";
                dataSqlite.TruyVanKhongTraVe(sqlThem, new String[]{edMaSv.getText().toString(), edHoVaTen.getText().toString()});
                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                LoadData();
            }
        });

        btnXoa.setOnClickListener(v -> {
            String id = edId.getText().toString().trim();
            if (id.isEmpty()) {
                Toast.makeText(MainActivity.this, "Bạn chưa nhập mã sinh viên", Toast.LENGTH_SHORT).show();
            } else {
                String sqlXoa = "DELETE FROM QLSV WHERE id = ?";
                dataSqlite.TruyVanKhongTraVe(sqlXoa, new String[]{id});
                Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                LoadData();
            }
        });

        btnCapNhat.setOnClickListener(v -> {
            String id = edId.getText().toString().trim();
            String maSv = edMaSv.getText().toString().trim();
            String hoVaTen = edHoVaTen.getText().toString().trim();

            if (id.isEmpty() || maSv.isEmpty() || hoVaTen.isEmpty()) {
                Toast.makeText(MainActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                String sqlCapNhat = "UPDATE QLSV SET maSV = ?, tenSV = ? WHERE id = ?";
                dataSqlite.TruyVanKhongTraVe(sqlCapNhat, new String[]{maSv, hoVaTen, id});
                Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                LoadData();
            }
        });

        btnTimKiem.setOnClickListener(v -> {
            String id = edId.getText().toString().trim();
            if (id.isEmpty()) {
                Toast.makeText(MainActivity.this, "Bạn chưa nhập mã sinh viên", Toast.LENGTH_SHORT).show();
            } else {
                String sqlTimKiem = "SELECT * FROM QLSV WHERE id = ?";
                Cursor cursor = dataSqlite.TruyVanTraVe(sqlTimKiem, new String[]{id});
                if (cursor != null && cursor.moveToFirst()) {
                    edMaSv.setText(cursor.getString(1));
                    edHoVaTen.setText(cursor.getString(2));
                    Toast.makeText(MainActivity.this, "Tìm thấy sinh viên", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show();
                }
                if (cursor != null) {
                    cursor.close();
                }
            }
        });
    }

    private void AnhXaCacControl() {
        btnThem = findViewById(R.id.btnThem);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnXoa = findViewById(R.id.btnXoa);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        edId = findViewById(R.id.edID);
        edMaSv = findViewById(R.id.edMaSv);
        edHoVaTen = findViewById(R.id.edHoVaTen);
        lvDanhSachSinhVien = findViewById(R.id.lvDanhSachSinhVien);
        dataSqlite = new DataSqlite(this, "qlsv.sqlite", null, 1);
    }

    private void KhoiTaoDatabase() {
        dataSqlite.TruyVanKhongTraVe("CREATE TABLE IF NOT EXISTS QLSV (id INTEGER PRIMARY KEY AUTOINCREMENT, maSV TEXT, tenSV TEXT)", null);
    }

    private void LoadData() {
        arrayListSV = new ArrayList<>();
        Cursor cursor = dataSqlite.TruyVanTraVe("SELECT * FROM QLSV", null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String sv = cursor.getInt(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2);
                arrayListSV.add(sv);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayListSV);
            lvDanhSachSinhVien.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_LONG).show();
        }
        if (cursor != null) {
            cursor.close();
        }
    }
}
