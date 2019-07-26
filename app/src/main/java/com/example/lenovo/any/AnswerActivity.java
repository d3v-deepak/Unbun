package com.example.lenovo.any;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import maes.tech.intentanim.CustomIntent;

import static com.example.lenovo.any.LoginActivity.FORUSERNAME;
import static com.example.lenovo.any.LoginActivity.FORUSRNAME;
import static com.example.lenovo.any.NewNoteActivity.SHARED_PREFS;

public class AnswerActivity extends AppCompatActivity {

    public EditText answertext;
    public String nameofanswerrer;
    public String naam;
    public String userstateinfo;
   public String antxt;
   public String text;
   public String location;
   public CheckBox anonymousity;
public Button report_btn;
    public String usercity;
    public Button addbtn;
    public Button delete_btn;
public String activityname;
    public int priorityofdoc;
    public String id;
   public TextView anstext;
   public int rep_count;
    public TextView nametxt;
    public String idofdoc;
    public TextView answerthis;
    public Button savebtn;
    public String preans;
    public String usrnaam;
    public String abc;
    public String personemail;
    public FirebaseFirestore db ;
    public CollectionReference notebookRef ;
 public    String  nametxtofanswerer;
    public TextView quetxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
      //  getSupportActionBar().setDisplayShowHomeEnabled(true);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       answertext = findViewById(R.id.edit_text_answer);
        addbtn = findViewById(R.id.addansbtn);
        quetxt=findViewById(R.id.ques);
        anstext=findViewById(R.id.anstext);
      // nameofanswerrer=findViewById(R.id.edit_text_name);
        nametxt=findViewById(R.id.name);
        report_btn=findViewById(R.id.report_btn);
        delete_btn=findViewById(R.id.delete_btn);
  savebtn=findViewById(R.id.save);
answerthis=findViewById(R.id.answerthis);
anonymousity=findViewById(R.id.checkanonymous);
 Bundle bundle=getIntent().getExtras();
   //     Intent intent7 = getIntent();
     //   String abc=intent7.getStringExtra("docid");


  //      nametxt.setText(abc);


        //Intent intentques = getIntent();
        abc=bundle.getString("ques");
        quetxt.setText(abc);
usrnaam=bundle.getString("usrname");//email
      //  Intent intentname = getIntent();
        rep_count=bundle.getInt("rep_count");

       String nameofquestioner=bundle.getString("name");
        nametxt.setText(nameofquestioner);
idofdoc=bundle.getString("docid");
        //Intent intentprean = getIntent();
         preans=bundle.getString("preans");
        anstext.setText(preans);//name+answers
activityname=bundle.getString("Activityname");

        //Intent intentdate = getIntent();
        String date=bundle.getString("date");
        nameofquestioner=nameofquestioner+" added this on "+date;
        nametxt.setText(nameofquestioner);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id== android.R.id.home)
        {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void addanswer(View view) {
//Bundle bundle2=getIntent().getExtras();     // Intent intent5 = getIntent();
        //String abc=bundle2.getString("ques");
       // Intent intent6=getIntent();
       //String preans=intent6.getStringExtra("preans")

        activityname=activityname.toLowerCase();

        load();

        db = FirebaseFirestore.getInstance();
        notebookRef = db.collection(usercity);
        if(activityname.equals("buisness")||activityname.equals("sports")||activityname.equals("technology")||
                activityname.equals("politics")||activityname.equals("movies")||
                activityname.equals("mystate")||activityname.equals("mainactivity")) {
            notebookRef = db.collection(usercity + " " + activityname);
        }
      /*  if(activityname.equals("mainactivity")){
            load();
            notebookRef = db.collection(usercity);
        }*/

loadnaam();
         nametxtofanswerer=naam;
         antxt=answertext.getText().toString();
        if (antxt.trim().isEmpty())
        {
            return;
        }

        if(anonymousity.isChecked()){

            nametxtofanswerer="anonymous";

        }




    antxt=nametxtofanswerer+"\n"+antxt+"\n\n"+preans;
      //  notebookRef.document(idofdoc).update("answer",antxt);


        anstext.setText(antxt);


    }




/*public void loadusername(){

    SharedPreferences username = getSharedPreferences(FORUSRNAME, MODE_PRIVATE);
    usrnaam=username.getString("usrname","");


}
*/


    public void loadnaam(){


        SharedPreferences usrname = getSharedPreferences(FORUSERNAME, MODE_PRIVATE);
        naam=usrname.getString("username","");//acct name
    }


    public void load(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        usercity = sharedPreferences.getString("lc", "");


    }

public void finalsave(View view){
        load();
    notebookRef.document(idofdoc).update("answer",antxt);
    loadnaam();

text=naam +" answered your question "+"'"+abc+"'";
id=idofdoc;
priorityofdoc=2;

    userstateinfo=usercity+" _users";
location=usercity+" "+activityname;
   // loadusername();






    db.collection(userstateinfo).document(usrnaam).collection("notifications").add(new Notification(id,priorityofdoc,location,text));

    finish();
}
////////////////no use////////////////////////////
public void updateui(View view){

        answertext.setVisibility(View.VISIBLE);
        addbtn.setVisibility(View.VISIBLE);
        savebtn.setVisibility(View.VISIBLE);
        anonymousity.setVisibility(View.VISIBLE);

        answerthis.setVisibility(View.INVISIBLE);



}

    @Override
    protected void onStart() {
        super.onStart();
        load();
       // Toast.makeText(this, "idofdoc is"+idofdoc+"\ncity is"+usercity, Toast.LENGTH_SHORT).show();
email_personusername();
        if(usrnaam.equals(personemail)){
    //delete button logic
            delete_btn.setVisibility(View.VISIBLE);



}
    }
    public void email_personusername(){
        SharedPreferences username = getSharedPreferences(FORUSRNAME, MODE_PRIVATE);
        personemail=username.getString("usrname","");
    }
    public void report_this(View view){
//get the value of report count
        //add the value of report by 1
        //and add the question location id to db collection
     int flag=0;
       rep_count=rep_count+1;
       if(rep_count>=10)
       {
    flag=1;
       }

        activityname=activityname.toLowerCase();

        load();

        db = FirebaseFirestore.getInstance();
 //       notebookRef = db.collection(usercity);
        notebookRef = db.collection(usercity + " " + activityname);
notebookRef.document(idofdoc).update("report_count",rep_count);
        Toast.makeText(this, "You reported this question", Toast.LENGTH_SHORT).show();


        if(flag==1){
Map<String ,Object> repinfo=new HashMap<>();
repinfo.put("location",usercity + " " + activityname);
repinfo.put("id of document",idofdoc);
repinfo.put("Question",abc);

            db.collection(" reports").
                    document().set(repinfo);

        }

        report_btn.setVisibility(View.GONE );

    }
    public void delete_this(View view){
       //1 take user city +""mainactivity
        //take id
        //then db.1.id.delete


        activityname=activityname.toLowerCase();

        load();

        db = FirebaseFirestore.getInstance();
        notebookRef = db.collection(usercity);
        if(activityname.equals("buisness")||activityname.equals("sports")||activityname.equals("technology")||
                activityname.equals("politics")||activityname.equals("movies")||
                activityname.equals("mystate")||activityname.equals("mainactivity")) {
            notebookRef = db.collection(usercity + " " + activityname);
            notebookRef.document(idofdoc).delete();
            finish();
        }


        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void finish() {
        super.finish();
  //      CustomIntent.customType(this, "right-to-left");
    }
}