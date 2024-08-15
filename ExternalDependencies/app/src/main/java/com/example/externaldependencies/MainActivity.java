package com.example.externaldependencies;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

// API Service Interface
interface ApiService {
    @GET("users/4")
    Call<User> getUser();
}

// User Model Class
class User {
    private String name;
    private Address address;

    public String getName() { return name; }
    public Address getAddress() { return address; }

    public static class Address {
        private String city;

        public String getCity() { return city; }
    }
}

public class MainActivity extends AppCompatActivity {

    private TextView textViewName, textViewAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewName = findViewById(R.id.textViewName);
        textViewAddress = findViewById(R.id.textViewAddress);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    textViewName.setText(user.getName());
                    textViewAddress.setText(user.getAddress().getCity());
                } else {
                    textViewName.setText("No data available");
                    textViewAddress.setText("");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("MainActivity", "Error fetching data", t);
                textViewName.setText("Error fetching data");
                textViewAddress.setText("");
            }
        });
    }
}
