package com.example.vishnu.treasurehunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BtnLogin;
    Button BtnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnLogin = (Button) findViewById(R.id.btnLogin);
        BtnReg=(Button)findViewById(R.id.btnRegister);


        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent logIn = new Intent(MainActivity.this,Login.class);
                startActivity(logIn);

            }
        });

        BtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Register = new Intent(MainActivity.this, Register.class);
                startActivity(Register);
            }
        });



    }
}
