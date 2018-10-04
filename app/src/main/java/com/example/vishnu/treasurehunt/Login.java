package com.example.vishnu.treasurehunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vishnu.treasurehunt.Common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button LoginButton;
    EditText TeamId;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button)findViewById(R.id.btnLogin);
        TeamId = (EditText) findViewById(R.id.teamId);
        Password = (EditText) findViewById(R.id.teamPwd);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableUser = database.getReference("users");

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tableUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(TeamId.getText().toString()).exists()) {

                            User user = dataSnapshot.child(TeamId.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(Password.getText().toString())) {

                                Toast.makeText(Login.this, "Log In Successfull", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Login.this,Game.class);
                                Common.currentUser = user;
                                startActivity(i);
                                finish();

                            } else {
                                Toast.makeText(Login.this, "Log In Failed", Toast.LENGTH_SHORT).show();

                            }
                        }else {
                            Toast.makeText(Login.this, "Not Registered",Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
