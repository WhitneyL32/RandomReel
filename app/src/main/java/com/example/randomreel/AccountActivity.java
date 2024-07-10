package com.example.randomreel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText emailInput;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        createAccountButton = findViewById(R.id.createAccountButton);

        // Set click listener for the create account button
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String email = emailInput.getText().toString();
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                String confirmPassword = confirmPasswordInput.getText().toString();

                if (password.equals(confirmPassword)) {
                    createAccount(name, email, username, password);
                } else {
                    Toast.makeText(AccountActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createAccount(String name, String email, String username, String password) {
        // Get a reference to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        // Save the user's details to the database
        String userId = usersRef.push().getKey(); // Generate a unique key for the new user
        if (userId != null) {
            User user = new User(name, email, username, password);
            usersRef.child(userId).setValue(user);
        }
    }

    public static class User {
        public String name;
        public String email;
        public String username;
        public String password;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String name, String email, String username, String password) {
            this.name = name;
            this.email = email;
            this.username = username;
            this.password = password;
        }
    }
}
