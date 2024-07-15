package com.example.javais_beautiful;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFirstNum, editTextSecondNum;
    private Button buttonAdd, buttonSubtract, buttonMultiply, buttonDivide;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextFirstNum = findViewById(R.id.editTextFirstNum);
        editTextSecondNum = findViewById(R.id.editTextSecondNum);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        textViewResult = findViewById(R.id.textViewResult);

        // Set click listeners for each operation button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('+');
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('-');
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('*');
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('/');
            }
        });
    }

    private void performOperation(char operation) {
        // Read input values
        String firstNumStr = editTextFirstNum.getText().toString();
        String secondNumStr = editTextSecondNum.getText().toString();

        if (!firstNumStr.isEmpty() && !secondNumStr.isEmpty()) {
            try {
                // Convert input to double (for division operation)
                double firstNum = Double.parseDouble(firstNumStr);
                double secondNum = Double.parseDouble(secondNumStr);

                double result = 0;

                // Perform the selected operation
                switch (operation) {
                    case '+':
                        result = firstNum + secondNum;
                        break;
                    case '-':
                        result = firstNum - secondNum;
                        break;
                    case '*':
                        result = firstNum * secondNum;
                        break;
                    case '/':
                        if (secondNum != 0) {
                            result = firstNum / secondNum;
                        } else {
                            textViewResult.setText("Cannot divide by zero");
                            return;
                        }
                        break;
                    default:
                        break;
                }

                // Display the result
                textViewResult.setText(String.format("Result: %.2f", result));

            } catch (NumberFormatException e) {
                textViewResult.setText("Invalid input. Please enter valid numbers.");
            }

        } else {
            textViewResult.setText("Please enter both numbers.");
        }
    }
}
