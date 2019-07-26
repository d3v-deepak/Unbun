package com.example.lenovo.any;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewNotification extends AppCompatActivity {
    public FirebaseFirestore db ;
    public CollectionReference notebookRef ;
    ///////////////////////////////////////////////
    /////////////////////////////////////////////////
    public EditText answertext;
    public String nameofanswerrer;
    public String naam;
    public String userstateinfo;
    public String antxt;
    public String location;
    public CheckBox anonymousity;
    public String usercity;
    public Button addbtn;

    public int priorityofdoc;
    public String id;
    public TextView quetxt;
    public TextView anstext;
    public TextView nametxt;
    public String idofdoc;
    public TextView answerthis;
    public Button savebtn;
    public String preans;
    public String usrnaam;
    /////////////////////////////////////////////













    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);
       ////////////////////////////////////////
                quetxt=findViewById(R.id.ques);//question
        anstext=findViewById(R.id.anstext);//answers
        // nameofanswerrer=findViewById(R.id.edit_text_name);
               // answerthis=findViewById(R.id.answerthis);
        nametxt=findViewById(R.id.name);//name
//////////////////////////////////////////////////////











        Bundle bundle=getIntent().getExtras();


        String idofquestion=bundle.getString("idofquestion");
        String locationofquestion=bundle.getString("locationofquestion");
       // Toast.makeText(this, ""+idofquestion+locationofquestion, Toast.LENGTH_SHORT).show();


        db = FirebaseFirestore.getInstance();
        db.collection(locationofquestion).document(idofquestion).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Note note=documentSnapshot.toObject(Note.class);
           String ques=     note.getDescription();
           ////////////////////////////////////
                nametxt.setText(note.getUsername());

           quetxt.setText(note.getDescription());

           anstext.setText(note.getAnswer());



                Toast.makeText(ViewNotification.this, ""+ques, Toast.LENGTH_SHORT).show();


            }
        });


    }
}
