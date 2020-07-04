package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    View bottom_sheet;
    TextView main_content, pages_left, page_no, back_to_page;
    ImageButton align_left, align_right, align_center, align_justify;
    ImageButton spacing1, spacing2, spacing3;
    ImageButton margin1, margin2, margin3;
    Button mode_dark, mode_light, mode_warm;

    String[] font = { "Sans-Serif", "Arial", "Agency FB", "Bondoi MT"};
    String typo = font[0];
    BottomSheetBehavior behavior;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }


        main_content = findViewById(R.id.textView_Main);
        pages_left = findViewById(R.id.pages_left);
        page_no = findViewById(R.id.page_no);
        back_to_page = findViewById(R.id.back_to_page);

        mainContent(main_content);

        align_left = findViewById(R.id.align_left);
        align_right = findViewById(R.id.align_right);
        align_center = findViewById(R.id.align_center);
        align_justify = findViewById(R.id.align_justify);

        align_left.setSelected(true);

        spacing1 = findViewById(R.id.spacing_1);
        spacing2 = findViewById(R.id.spacing_2);
        spacing3 = findViewById(R.id.spacing_3);

        spacing1.setSelected(true);

        margin1 = findViewById(R.id.margin_1);
        margin2 = findViewById(R.id.margin_2);
        margin3 = findViewById(R.id.margin_3);

        margin2.setSelected(true);

        mode_dark = findViewById(R.id.mode_dark);
        mode_light = findViewById(R.id.mode_light);
        mode_warm = findViewById(R.id.mode_warm);

        mode_dark.setSelected(true);


        pages_left.setText("Back to page 3");
        page_no.setText("15 of 734");
        back_to_page.setText("29 pages left");


        bottom_sheet = findViewById(R.id.design_bottom_sheet);
        behavior = BottomSheetBehavior.from(bottom_sheet);
        behavior.setHideable(true);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("BottomSheetCallback","BottomSheetBehavior.STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:

                        Log.i("BottomSheetCallback","BottomSheetBehavior.STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i("BottomSheetCallback","BottomSheetBehavior.STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        main_content.setVisibility(View.INVISIBLE);
                        Log.i("BottomSheetCallback","BottomSheetBehavior.STATE_HALF_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:

                        main_content.setVisibility(View.VISIBLE);
                        Log.i("BottomSheetCallback","BottomSheetBehavior.STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        main_content.setVisibility(View.VISIBLE);
                        Log.i("BottomSheetCallback","BottomSheetBehavior.STATE_HIDDEN");
                        break;

                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("BottomSheetCallback","slideOffset: " + slideOffset);

            }
        });



        Spinner spin = findViewById(R.id.font_spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //todo change font
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //todo when nothing is selected
            }
        });
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, font);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin.setAdapter(aa);

    }

    private void mainContent(TextView main_content) {
        String content = "Mobile app development is the act or process by which a mobile app is developed for mobile devices, such as personal digital assistants, enterprise digital assistants or mobile phones.\nThese applications can be pre-installed on phones during manufacturing platforms, or delivered as web applications using server-side or client-side processing (e.g., JavaScript) to provide an \"application-like\" experience within a Web browser.\n Application software developers also must consider a long array of screen sizes, hardware specifications, and configurations because of intense competition in mobile software and changes within each of the platforms. Mobile app development has been steadily growing, in revenues and jobs created. A 2013 analyst report estimates there are 529,000 direct app economy jobs within the EU then 28 members (including the UK), 60 percent of which are mobile app developers.";
        main_content.setTextSize(1,20);
        main_content.setText(content);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.main_settings:
                {
                    main_content.setVisibility(View.INVISIBLE);

                    if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                    else if(behavior.getState() == BottomSheetBehavior.STATE_EXPANDED){

                        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    }
                    else if(behavior.getState() == BottomSheetBehavior.STATE_HIDDEN){
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                    else{
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
            break;
            }


            case R.id.main_hamburger:
            {
                Intent i = new Intent(MainActivity.this,three_tabbed.class);
                startActivity(i);
            }
            default:

                return  super.onOptionsItemSelected(item);


        }
        return  true;
    }
}
