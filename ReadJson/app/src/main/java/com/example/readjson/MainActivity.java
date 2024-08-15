package com.example.readjson;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Executors.newSingleThreadExecutor().execute(() -> {
            String jsonResponse = fetchJson("https://jsonplaceholder.typicode.com/todos/1");
            runOnUiThread(() -> updateTextView(jsonResponse));
        });
    }

    private String fetchJson(String urlString) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(urlString).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void updateTextView(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            String title = jsonObject.getString("title");
            boolean completed = jsonObject.getBoolean("completed");
            textView.setText("Title: " + title + "\nCompleted: " + completed);
        } catch (Exception e) {
            textView.setText("Failed to parse JSON");
        }
    }
}
