package com.example.lab04;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Biến thành viên
    private Restaurant r = new Restaurant();
    private List<Restaurant> restaurantList = new ArrayList<>();
    private ArrayAdapter<Restaurant> adapter;

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

        // Tham chiếu đến Button và khai báo listener
        Button save = findViewById(R.id.save);
        save.setOnClickListener(onSave);

        // Tham chiếu đến ListView và thiết lập adapter
        ListView list = findViewById(R.id.restaurants);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, restaurantList);
        list.setAdapter(adapter);
    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Restaurant r = new Restaurant();
            EditText name = findViewById(R.id.name);
            EditText address = findViewById(R.id.addr);

            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());
            RadioGroup type = findViewById(R.id.type);
            int checkedId = type.getCheckedRadioButtonId();
            if (checkedId == R.id.take_out) {
                r.setType("Take out");
            } else if (checkedId == R.id.sit_down) {
                r.setType("Sit down");
            } else if (checkedId == R.id.delivery) {
                r.setType("Delivery");
            }

            // Thêm nhà hàng vào danh sách và cập nhật adapter
            restaurantList.add(r);
            adapter.notifyDataSetChanged();

            // Hiển thị thông tin nhà hàng sau khi lưu
            showRestaurantInfoDialog(r);
        }
    };

    // Phương thức để hiển thị AlertDialog với thông tin nhà hàng
    private void showRestaurantInfoDialog(Restaurant restaurant) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Restaurant Information");
        builder.setMessage(restaurant.toString());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Đóng AlertDialog khi người dùng click OK
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
