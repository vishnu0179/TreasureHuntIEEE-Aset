package com.example.vishnu.treasurehunt;


import android.app.Activity;
import android.app.AlertDialog;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.example.vishnu.treasurehunt.Common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Game extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button scanQRbutton;
    private static final int REQUEST_CODE_QR_SCAN = 101;


    String result;
    TextView validate;
    int Level=0, flag=0, path;
    ImageView ques;
    TextView teamId;

    TextView level;
    String[] value1 = new String[]{"1000","2000","7000","16000","17000"};
    String[] value2 = new String[]{"4000","5000","7000","16000","17000"};
    String[] value3 = new String[]{"9000","10000","15000","16000","17000"};
    String[] value4 = new String[]{"13000","14000","15000","16000","17000"};

    //private SharedPreferences mPreferences;
    //private String sharedPrefFile= "com.example.vishnu.treasurehunt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        level = (TextView) findViewById(R.id.level);
        teamId = (TextView) findViewById(R.id.teamId);
        validate = (TextView) findViewById(R.id.validate);
        ques = (ImageView) findViewById(R.id.ques);
        setSupportActionBar(toolbar);

       // teamId.setText("Team ID #"+ Common.currentUser.toString());

        scanQRbutton = (Button) findViewById(R.id.qrButton);




        //mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       /* if(savedInstanceState != null){
            Level = savedInstanceState.getInt("lvl");
            path = savedInstanceState.getInt("pth");
            flag = savedInstanceState.getInt("flg");
        }*/

        scanQRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Game.this, QrCodeActivity.class);
                startActivityForResult(i, REQUEST_CODE_QR_SCAN);

                //validate.setText(result);
            }
        });


    }
/*
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor prefernceEditor = mPreferences.edit();
        prefernceEditor.putInt("lvl",Level);
        prefernceEditor.putInt("pth",path);
        prefernceEditor.putInt("flg",flag);
        prefernceEditor.apply();
    }
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_OK){
            if(data==null)
                return;

            result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");

            if(result!=null){

                final AlertDialog alertDialog = new AlertDialog.Builder(Game.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("Qr code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;
        }

        if(requestCode== REQUEST_CODE_QR_SCAN){
            if(data==null)
                return;



            result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");


//            final AlertDialog alertDialog = new AlertDialog.Builder(Game.this).create();
//            alertDialog.setTitle("Scan result");
//            alertDialog.setMessage(result);
//            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                        }
//                    });
//            alertDialog.show();
//


            if(flag == 0){
                path = checkPath(result);
                flag = 1;
            }

            //gameplay(result,Level,path);

            //level.setText(String.valueOf(Level));

        //    level.setText(String.valueOf(gameplay(result,Level,checkPath(result))));

         if(gameplay(result,path)==1){
             validate.setText("Valid");
             validate.setTextColor(getColor(R.color.colorAccent));
             ++Level;

             if(result.equals("1000"))
                 //Picasso.with(getBaseContext()).load(R.drawable.ic_a1000).fit().into(ques);
                 ques.setImageResource(R.drawable.ic_1000_min);

             else if(result.equals("2000"))
                 //Picasso.with(getBaseContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/1000-min.jpeg").fit().into(ques);
                 ques.setImageResource(R.drawable.ic_2000);

             else if(result.equals("4000"))
                 //Picasso.with(getBaseContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/4000.jpeg").into(ques);
                 ques.setImageResource(R.drawable.ic_4000);

             else if(result.equals("5000"))
                 ques.setImageResource(R.drawable.ic_5000);

             else if(result.equals("7000"))
                 ques.setImageResource(R.drawable.ic_7000);

             else if(result.equals("9000"))
                 ques.setImageResource(R.drawable.ic_9000);

             else if(result.equals("10000"))
                 ques.setImageResource(R.drawable.ic_10000);

             else if(result.equals("13000"))
                 ques.setImageResource(R.drawable.ic_13000);

             else if(result.equals("14000"))
                 ques.setImageResource(R.drawable.ic_14000);

             else if(result.equals("15000"))
                 ques.setImageResource(R.drawable.ic_15000);

             else if(result.equals("16000"))
                 ques.setImageResource(R.drawable.ic_a16000);

             else if(result.equals("17000"))
                 Picasso.with(getBaseContext()).load("http://pngimage.net/wp-content/uploads/2018/06/you-win-png-5-300x200.png").into(ques);

             level.setText("Status: Level "+ String.valueOf(Level));

         }else {
             validate.setText("Ïnvalid");
             validate.setTextColor(getColor(R.color.colorInvalid));
         }

        }

//        validate.setText(String.valueOf(path));


        return;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.rules) {
            // Handle the camera action
        } else if (id == R.id.home) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public int checkPath(String ans){

        if(ans.equals("1000"))
            return 1;
        else if(ans.equals("4000"))
            return 2;
        else if(ans.equals("9000"))
            return 3;
        else if(ans.equals("13000"))
            return 4;

        return 0;
    }

    public int gameplay(String ans, int path){

        if(path==1){
            if(ans.equals(value1[Level])){

                return 1;
            }else {
                return 0;
            }
        }else if(path == 2){
            if(ans.equals(String.valueOf(value2[Level]))){

                return 1;
            }else {
                return 0;
            }
        }else if(path == 3){
            if(ans.equals(value3[Level])){

                return 1;
            }else {
                return 0;
            }
        }else if(path == 4){
            if(ans.equals(value4[Level])){

                return 1;
            }else {
                return 0;
            }
        }
        return 0;
    }
}
