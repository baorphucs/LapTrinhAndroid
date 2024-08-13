package com.example.lab04;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LunchList extends AppCompatActivity {
    //bien thanh vien
    private  Restaurant r = new Restaurant();
    public  void onCreate(Bundle savedlnstanceState){
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.activity_main);
    }
}
