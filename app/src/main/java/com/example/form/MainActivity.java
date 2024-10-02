package com.example.form;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText, emailEditText, passwordEditText , idnumberEditText, departmentEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.et_name);
        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);
        departmentEditText = findViewById(R.id.et_dept);
        idnumberEditText = findViewById(R.id.edit_text_id);
        submitButton = findViewById(R.id.btn_submit);

        submitButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String id = idnumberEditText.getText().toString();
            String dept = departmentEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (validateForm(name, email, password, id, dept)) {
                Toast.makeText(MainActivity.this, "Form submitted successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateForm(String name, String email, String password, String id, String dept) {
        // Validate name (at least 3 characters and only letters)
        String nameRegex = "^[a-zA-Z\\s]{3,}$";
        if (!name.matches(nameRegex)) {
            showToast("Name must contain only letters and be at least 3 characters long");
            return false;
        }

        // Validate email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Invalid email format");
            return false;
        }


        // Validate Id (min 15 digits)
        String idRegex = "018.[0-9]*.{12,}";
        if (!id.matches(idRegex)) {
            showToast("ID must contain at least 15 digits including first 018");
            return false;
        }


        // Validate department (at least 3 characters and only letters)
        String deptRegex = "^[a-zA-Z\\s]{3,}$";
        if (!dept.matches(deptRegex)) {
            showToast("department must contain only letters and be at least 3 characters long");
            return false;
        }


        
        // Validate password (min 6 chars, at least one digit, one lowercase, one uppercase)
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        if (!password.matches(passwordRegex)) {
            showToast("Password must contain at least 6 characters, one digit, one lowercase, and one uppercase letter");
            return false;
        }

        return true;
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
