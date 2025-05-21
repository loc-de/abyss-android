package com.abyss.abyss.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abyss.abyss.R;
import com.abyss.abyss.data.model.User;
import com.abyss.abyss.data.network.ApiService;
import com.abyss.abyss.data.network.RetrofitClient;
import com.abyss.abyss.ui.login.LoginActivity;
import com.abyss.abyss.ui.register.RegisterActivity;
import com.abyss.abyss.utils.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private TextView usernameView, emailView, idView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameView = findViewById(R.id.usernameView);
        emailView = findViewById(R.id.roleView);
        idView = findViewById(R.id.idView);

        String token = TokenManager.getToken(this);
        if (token == null) {
            Toast.makeText(this, "No token found", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService api = RetrofitClient.getInstanceWithAuth(token).create(ApiService.class);
        api.getProfile().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    usernameView.setText(user.getUsername());
                    emailView.setText(user.getRole());
                    idView.setText("ID: " + user.getId());
                } else {
                    if (response.code() == 401 || response.code() == 403) {
                        TokenManager.clearToken(ProfileActivity.this);
                        Intent intent = new Intent(ProfileActivity.this, com.abyss.abyss.ui.login.LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ProfileActivity.this, "Failed to load profile", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
