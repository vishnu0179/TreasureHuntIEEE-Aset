package com.example.vishnu.treasurehunt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.squareup.picasso.Picasso;

public class Game extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button scanQRbutton;
    private static final int REQUEST_CODE_QR_SCAN = 101;


    String result;
    TextView validate;
    int Level=0, flag=0, path;
    ImageView ques;

    TextView level;
    String[] value1 = new String[]{"1000","2000","7000","16000"};
    String[] value2 = new String[]{"4000","5000","7000","16000"};
    String[] value3 = new String[]{"9000","10000","15000","16000"};
    String[] value4 = new String[]{"13000","14000","15000","16000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        level = (TextView) findViewById(R.id.level);
        validate = (TextView) findViewById(R.id.validate);
        ques = (ImageView) findViewById(R.id.ques);
        setSupportActionBar(toolbar);

        scanQRbutton = (Button) findViewById(R.id.qrButton);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        scanQRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Game.this, QrCodeActivity.class);
                startActivityForResult(i, REQUEST_CODE_QR_SCAN);

                //validate.setText(result);
            }
        });


    }



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


            final AlertDialog alertDialog = new AlertDialog.Builder(Game.this).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            alertDialog.show();

            if(flag == 0){
                path = checkPath(result);
                flag = 1;
            }

            //gameplay(result,Level,path);

            //level.setText(String.valueOf(Level));

        //    level.setText(String.valueOf(gameplay(result,Level,checkPath(result))));

         if(gameplay(result,path)==1){
             validate.setText("Valid");
             ++Level;

             if(result.equals("1000"))
                 Picasso.with(getApplicationContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/temp.png").into(ques);
                 //ques.setImageResource(R.drawable.temp);

             else if(result.equals("2000"))
                 Picasso.with(getBaseContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/1000-min.jpeg").fit().into(ques);

             else if(result.equals("4000"))
                 Picasso.with(getBaseContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/4000.jpeg").into(ques);

             else if(result.equals("5000"))
                 Picasso.with(getBaseContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/5000.jpeg").into(ques);

             else if(result.equals("7000"))
                 Picasso.with(getBaseContext()).load("https://support.appsflyer.com/hc/article_attachments/115011109089/android.png").into(ques);

             else if(result.equals("9000"))
                 Picasso.with(getBaseContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/9000.jpeg").into(ques);

             else if(result.equals("10000"))
                 Picasso.with(getBaseContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/10000.jpeg").into(ques);

             else if(result.equals("13000"))
                 Picasso.with(getBaseContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/13000.jpeg").into(ques);

             else if(result.equals("14000"))
                 Picasso.with(getBaseContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/14000.jpeg").into(ques);

             else if(result.equals("15000"))
                 Picasso.with(getBaseContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/15000.jpeg").into(ques);

             else if(result.equals("16000"))
                 Picasso.with(getBaseContext()).load("https://github.com/vishnu0179/treaseurehuntimages/blob/master/16000.jpeg").into(ques);

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

        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.rules) {
            // Handle the camera action
            fragmentManager.beginTransaction()
                    .replace(R.id.rules_fragment, new RulesFragment())
                    .commit();
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
