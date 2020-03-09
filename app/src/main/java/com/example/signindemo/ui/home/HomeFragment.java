package com.example.signindemo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.signindemo.R;
import com.example.signindemo.Signin;
import com.google.firebase.database.DatabaseReference;

public class HomeFragment extends Fragment {

    View view;
    EditText et_name,et_age;
    Button bt_submit;
    DatabaseReference databaseReference;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        et_name=view.findViewById(R.id.name);
        et_age=view.findViewById(R.id.age);
        bt_submit=view.findViewById(R.id.btn_submit);



        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             savedata();
            }
        });

        return view;
    }

    private void savedata() {

        String name=et_name.toString().trim();
        String age=et_age.toString().trim();




    }
}