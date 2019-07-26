package com.example.lenovo.any;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.lenovo.any.LoginActivity.FORUSERNAME;

public class Accountdetails extends AppCompatActivity {
    private static final String FORUSERNAME ="foraskername" ;
    public EditText et_name;
    public TextView name_txt;
    public Button save_btn;
    public Button edit_btn;
    private Button signOut;
    public String naam;

   // GoogleSignInClient mGoogleSignInClient;
    //public static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountdetails);
    et_name=findViewById(R.id.personname);
save_btn=findViewById(R.id.save_btn);
        edit_btn=findViewById(R.id.edit_btn);
        name_txt=findViewById(R.id.text_view_name);
        signOut = (Button) findViewById(R.id.sign_out);
        mAuth = FirebaseAuth.getInstance();
        signOut.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mAuth.signOut(); //get signed out
                signOut.setVisibility(View.GONE);
            }
        });

    }


   public void tomainactivity(View view){
update();
    Intent intent=new Intent(this,MainActivity.class);
    startActivity(intent);
    finish();

   }
   public void update(){

       SharedPreferences usrname = getSharedPreferences(FORUSERNAME, MODE_PRIVATE);
       SharedPreferences.Editor editorforname = usrname.edit();
       editorforname.putString("username",et_name.getText().toString());//acctname of person
       editorforname.apply();

   }
    public void loadnaam(){


        SharedPreferences usrname = getSharedPreferences(FORUSERNAME, MODE_PRIVATE);
        naam=usrname.getString("username","");//acct name
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadnaam();
        et_name.setText(naam);
        name_txt.setText(naam);
    }
    public void updateui_foredit(View view){
        edit_btn.setVisibility(View.GONE);
        name_txt.setVisibility(View.GONE);
    et_name.setVisibility(View.VISIBLE);
    save_btn.setVisibility(View.VISIBLE);

    }
}
