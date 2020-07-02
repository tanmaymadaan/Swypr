package com.tanmaymadaan.swypr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tanmaymadaan.swypr.R;
import com.tanmaymadaan.swypr.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;
    private String TAG = "MainActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mBinding.sendOtpBtn.setOnClickListener(this);
        setProgressBar(mBinding.progressBar);
        mAuth = FirebaseAuth.getInstance();
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressBar();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Authentication Success.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(user);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        hideProgressBar();
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mBinding.email.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mBinding.email.setError("Required.");
            valid = false;
        } else {
            mBinding.email.setError(null);
        }

        String password = mBinding.pass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mBinding.pass.setError("Required.");
            valid = false;
        } else {
            mBinding.pass.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        switch (i) {
            case R.id.sendOtpBtn:
                signIn(mBinding.email.getText().toString(), mBinding.pass.getText().toString());
        }
    }
}