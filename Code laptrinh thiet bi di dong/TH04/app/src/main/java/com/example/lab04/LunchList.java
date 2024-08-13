package com.example.lab04;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LunchList extends AppCompatActivity {
    private List<Restaurant> restaurantList = new ArrayList<Restaurant>();
    private ArrayAdapter<Restaurant> adapter = null;
    //bien thanh vien
    private  Restaurant r = new Restaurant();
    public  void onCreate(Bundle savedlnstanceState){
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.activity_main);

    }

}
