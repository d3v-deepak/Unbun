package com.example.lenovo.any;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    public String usrloc;
    public String userstateinfo;
     public String usrnam;
    private SignInButton signIn;
    private Button signOut;
    public Button contbtn;
    public ImageView logopic;
    public ImageView avtr1;
    public ImageView avtr2;
    public EditText editloc;
    public String personName;
    public TextView connect;
    public TextView ntercity;
    public TextView slctusrnam;
    public TextView nterusrnam;
    public EditText usernam;
    public String userstate;
    public TextView txtviewstate;
    public EditText edittxtstate;
    public TextView usrnamxist;
    public FirebaseFirestore db ;
    public CollectionReference notebookRef ;


    public String personEmail;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String FORUSERNAME = "foraskername";
    public static final String FORUSRNAME = "forusrname";
    public static final String FORUSERSTATE = "forusrstate";

    public static final int RC_SIGN_IN = 1;
    GoogleSignInClient mGoogleSignInClient;
    public static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
connect=findViewById(R.id.connecttxt);
ntercity=findViewById(R.id.textViewlocation);
usrnamxist=findViewById(R.id.textViewnameexist);
slctusrnam=findViewById(R.id.textViewselectusrname);
nterusrnam=findViewById(R.id.textViewnterusrname);
usernam=findViewById(R.id.editTextusername);
txtviewstate=findViewById(R.id.textViewstate);
edittxtstate=findViewById(R.id.editTextlstate);//state

       // chooseavtr=findViewById(R.id.avtartextView);
     editloc=findViewById(R.id.editTextlocation);
/*           avtr1=findViewById(R.id.avtar1imageView);
        avtr2=findViewById(R.id.avtar2imageView);
  */      signIn = (SignInButton)findViewById(R.id.sign_in_button);
        signOut = (Button) findViewById(R.id.sign_out);
        mAuth = FirebaseAuth.getInstance();

        contbtn=findViewById(R.id.continuebtn);
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        signOut.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mAuth.signOut(); //get signed out
                signOut.setVisibility(View.GONE);
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this,"you are not able to log in to google",Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {

        signOut.setVisibility(View.INVISIBLE);
        signIn.setVisibility(View.INVISIBLE);
      connect.setVisibility(View.INVISIBLE);
      editloc.setVisibility(View.VISIBLE);
      ntercity.setVisibility(View.VISIBLE);
edittxtstate.setVisibility(View.VISIBLE);
txtviewstate.setVisibility(View.VISIBLE);


     // nterusrnam.setVisibility(View.VISIBLE);
      //  slctusrnam.setVisibility(View.VISIBLE);
       // usrnamxist.setVisibility(View.VISIBLE);
       // usernam.setVisibility(View.VISIBLE);


        contbtn.setVisibility(View.VISIBLE);
        /*avtr1.setVisibility(View.VISIBLE);
        avtr2.setVisibility(View.VISIBLE);
        editloc.setVisibility(View.VISIBLE);
        chooseavtr.setVisibility(View.VISIBLE);
        ntercity.setVisibility(View.VISIBLE);

*/
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            personName = acct.getDisplayName();
            //String personGivenName = acct.getGivenName();
            // String personFamilyName = acct.getFamilyName();
            personEmail = acct.getEmail();
            //String personId = acct.getId();
            //Uri personPhoto = acct.getPhotoUrl();


        }


    }




    public void showusernameallert(View view){

        slctusrnam.setVisibility(View.VISIBLE);

    }

    public void saveinitialdata(View view){
//editloc==cityname

        if(editloc.length()==0 ||edittxtstate.length()==0 ){
            Toast.makeText(this, "Please Enter a valid location", Toast.LENGTH_SHORT).show();
        }
        else {
            usrloc=editloc.getText().toString();

userstate=edittxtstate.getText().toString();//state

            usrnam=personEmail;
                userstate=userstate.toLowerCase();
                usrloc=usrloc.toLowerCase();
            usrnam=usrnam.toLowerCase();

            userstateinfo=usrloc+" _users";

            user usr=new user(usrnam);
//take state instead of username and use state as a collection
            //usrloc+kar as a city users


            db = FirebaseFirestore.getInstance();
           // notebookRef = db.collection("users");

            //notebookRef.add(usr);

        //    db.collection("users").document(usrnam).set(usr);
db.collection(userstateinfo).document(personEmail).set(usr);





            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("lc", usrloc);//city namee
                editor.apply();



            SharedPreferences usrname = getSharedPreferences(FORUSERNAME, MODE_PRIVATE);
            SharedPreferences.Editor editorforname = usrname.edit();
            editorforname.putString("username",personName);//acctname of person
               editorforname.apply();
             //   editor.putString("useremail",personEmail);

            SharedPreferences username = getSharedPreferences(FORUSRNAME, MODE_PRIVATE);
            SharedPreferences.Editor editorforusername = username.edit();
            editorforusername.putString("usrname", usrnam);//email
            editorforusername.apply();


            SharedPreferences stateofuser=getSharedPreferences(FORUSERSTATE,MODE_PRIVATE);
            SharedPreferences.Editor editorforuserstate= stateofuser.edit();
            editorforuserstate.putString("state",userstate);

editorforuserstate.apply();





            Intent intent=new Intent(this,MainActivity.class);
           startActivity(intent);

            finish();





        }



    }


}
