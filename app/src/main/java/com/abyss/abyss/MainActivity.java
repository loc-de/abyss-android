package com.abyss.abyss;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.abyss.abyss.ui.login.LoginActivity;
import com.abyss.abyss.ui.profile.ProfileActivity;
import com.abyss.abyss.utils.TokenManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = TokenManager.getToken(this);

        if (token != null && !token.isEmpty()) {
            startActivity(new Intent(this, ProfileActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish();
    }
}
