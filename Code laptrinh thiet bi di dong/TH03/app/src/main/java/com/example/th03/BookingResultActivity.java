package com.example.th03;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookingResultActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvPhone, tvRoomType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_result);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvRoomType = findViewById(R.id.tvRoomType);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            String email = extras.getString("email");
            String phone = extras.getString("phone");
            String roomType = extras.getString("roomType");

            tvName.setText("Name: " + name);
            tvEmail.setText("Email: " + email);
            tvPhone.setText("Phone: " + phone);
            tvRoomType.setText("Room Type: " + roomType);
        }
    }
}
