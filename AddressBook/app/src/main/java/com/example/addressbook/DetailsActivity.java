package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.addressbook.Fragment.DetailsFragment;

public class DetailsActivity extends AppCompatActivity {

    public DetailsActivity() {
        super(R.layout.activity_details);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true).add(R.id.fragment_container_view, DetailsFragment.class, null).commit();
        }
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            Intent i = new Intent(this, EmployeeActivity.class);
            startActivity(i);
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }
}