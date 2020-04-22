package com.example.locate;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.locate.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Test extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }



    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(Test.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(Test.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) {

//                        Intent intent = new Intent(EasyQuran.this, Home.class);
//                        // intent.putExtra("mode","single");
//                        // intent.putExtra("selected_card",finalI);
//                        startActivity(intent);
                    }
                    else if(finalI==1){
//                        Intent intent = new Intent(EasyQuran.this, Home.class);
//                        intent.putExtra("mode",1);
//                        // intent.putExtra("selected_card",finalI);
//                        startActivity(intent);

                    }
                    else if (finalI == 5) {


                        //call_mushaf();

                        // test();
                    }
                    else if(finalI==3){

                    }
                    else {
//                        Intent intent = new Intent(EasyQuran.this, MainActivity.class);
//                        intent.putExtra("card", finalI);
//                        intent.putExtra("mode", "single");
//
//                        startActivity(intent);
                    }

                }
            });
        }
    }


}