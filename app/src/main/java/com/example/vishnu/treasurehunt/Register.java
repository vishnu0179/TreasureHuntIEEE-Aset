package com.example.vishnu.treasurehunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText Passcode;
    EditText TeamName;
    EditText TeamID;
    EditText Password;
    Button Submit;
    Button Register;
    LinearLayout RegLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Passcode = (EditText) findViewById(R.id.passCode);
        Submit =(Button) findViewById(R.id.submitBtn);
        TeamName = (EditText) findViewById(R.id.teamName);
        TeamID = (EditText) findViewById(R.id.teamId);
        Password =(EditText) findViewById(R.id.password);
        Register = (Button) findViewById(R.id.btnRegister);
        RegLayout = (LinearLayout) findViewById(R.id.register);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Passcode.getText().toString().equals("0852")){
                    RegLayout.setVisibility(View.VISIBLE);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference tableUser= database.getReference("users");

                    tableUser.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(final DataSnapshot dataSnapshot) {

                            Register.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(dataSnapshot.child(TeamID.getText().toString()).exists()){
                                        Toast.makeText(Register.this,"User Already Exists",Toast.LENGTH_SHORT).show();

                                    }else {
                                        User user = new User(Password.getText().toString());
                                        tableUser.child(TeamID.getText().toString()).setValue(user);
                                        Toast.makeText(Register.this,"Registered Successfully",Toast.LENGTH_SHORT);

                                    }

                                }
                            });
                                                    }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                else {
                    Toast.makeText(Register.this,"Wrong PassCode",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
