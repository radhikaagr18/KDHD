package com.parana.kdhd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parana.kdhd.databinding.ActivityAddRequestBinding;

public class AddRequest extends AppCompatActivity {

    private DatabaseReference mDatabase;
    ActivityAddRequestBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_request);


        binding.submitRequest.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){

                String username = binding.username.getText().toString();
                String age = binding.age.getText().toString();
                String city = binding.city.getText().toString();
                String phNo = binding.phNo.getText().toString();
                String details = binding.username.getText().toString();

                Request request =  new Request(username,age,city,phNo,details);

                FirebaseDatabase.getInstance().getReference().child("requests").push().setValue(request);

                Toast.makeText(getApplicationContext(), "request added", Toast.LENGTH_SHORT).show();

                startActivity(getIntent());

            }
        });


    }


}
