package com.example.multipleactivityformexample;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView textViewDisplay;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewDisplay = findViewById(R.id.textViewDisplay);
        returnButton = findViewById(R.id.button_return);

        // Get data from intent
        String name = getIntent().getStringExtra("NAME");
        String email = getIntent().getStringExtra("EMAIL");
        String address = getIntent().getStringExtra("ADDRESS");

        // Display data
        textViewDisplay.setText("Name: " + name + "\nEmail: " + email + "\nAddress: " + address);

        // Set return button action
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
