package com.abyss.abyss.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abyss.abyss.data.repository.AuthRepository;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> tokenLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    private final AuthRepository repository = new AuthRepository();

    public void login(String username, String password) {
        repository.login(username, password, tokenLiveData, errorLiveData);
    }
}
