package com.tanmaymadaan.swypr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.tanmaymadaan.swypr.R;
import com.tanmaymadaan.swypr.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding mBinding;
    private String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        //Fetch and display name
        String welcomeMsg = getString(R.string.welcome) + " Tanmay!";
        mBinding.welcomeTv.setText(welcomeMsg);

        //Fetch and display distance travelled
        String distTravelled = getString(R.string.dist_trav) + " 20KM";
        mBinding.distanceTv.setText(distTravelled);

        //Fetch and display swipe-in time
        String swipeInTime = getString(R.string.swipeIn) + " 10:27AM";
        mBinding.swipeTimeTv.setText(swipeInTime);

        //Fetch and display total time
        String totalTime = getString(R.string.totalTime) + " 3hr48min";
        mBinding.totalTimeTv.setText(totalTime);

        //Fetch and display current check-in
        String checkIn = getString(R.string.currCheckIn) + " CMC";
        mBinding.currCheckinTv.setText(checkIn);

    }
}