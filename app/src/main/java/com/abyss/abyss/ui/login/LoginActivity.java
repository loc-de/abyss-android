package com.abyss.abyss.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.abyss.abyss.R;
import com.abyss.abyss.ui.register.RegisterActivity;
import com.abyss.abyss.utils.TokenManager;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();
            viewModel.login(username, password);
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        viewModel.tokenLiveData.observe(this, token -> {
            TokenManager.saveToken(this, token);
            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, com.abyss.abyss.ui.profile.ProfileActivity.class);
            startActivity(intent);
            finish();
        });

        viewModel.errorLiveData.observe(this, error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        );
    }
}