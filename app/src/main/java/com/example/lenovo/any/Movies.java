package com.example.lenovo.any;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import maes.tech.intentanim.CustomIntent;

import static com.example.lenovo.any.LoginActivity.FORUSERSTATE;

public class Movies extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public String usercity;
    public String userstate;
    public Button statebtn;
    public FirebaseFirestore db ;
    public CollectionReference notebookRef ;
    private NoteAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
    statebtn=findViewById(R.id.mystate)   ;
    }

    public void setUpRecyclerView() {


        Query query = notebookRef.orderBy("priority", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();

        adapter = new NoteAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);




        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final DocumentSnapshot documentSnapshot, int position) {
                String id = documentSnapshot.getId();
                Note note = documentSnapshot.toObject(Note.class);
                String preans=note.getAnswer();
                String ques=note.getDescription();
                String date=note.getDate();
                String name=note.getTitle();
                String usernaam=note.getUsername();
                String classname=Movies.class.getSimpleName();

                //      Intent intent2 = getIntent();
                //   answer2 = ;
                //     answer2=null;
                //  DocumentReference documentReference=documentSnapshot.getReference();
                //documentReference.update("answer",intent2.getStringExtra("newans"));
                Intent intent=new Intent(Movies.this, AnswerActivity.class);
                //notebookRef.document(id).update("","");
                intent.putExtra("name",name);
                intent.putExtra("usrname",usernaam);
                intent.putExtra("date",date);
                intent.putExtra("docid",id);
                int rep_count=note.getReport_count();
                intent.putExtra("ques",ques);
                intent.putExtra("preans",preans);
                intent.putExtra("Activityname",classname);
                startActivity(intent);




            }
        });}
    @Override
    protected void onStart() {
        super.onStart();
        loaduserstate();

statebtn.setText(userstate);


        db = FirebaseFirestore.getInstance();
        notebookRef = db.collection(userstate+" movies");

        setUpRecyclerView();

        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }












    public void gotonotification(View view){

        startActivity(new Intent(this,Notifications.class));

    }



    public void loaduserstate(){

        SharedPreferences stateofuser = getSharedPreferences(FORUSERSTATE,MODE_PRIVATE);
        userstate =stateofuser.getString("state","");//contains state


    }



    public void go_to_mystate(View view){
        Intent intent=new Intent(this,Mystate.class);
        startActivity(intent);
        CustomIntent.customType(this, "left-to-right");


    }
    public void go_to_home(View view){

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
finish();
    }

    public void go_to_sports(View view) {
        Intent intent = new Intent(this, Sports.class);
        startActivity(intent);
        CustomIntent.customType(this, "right-to-left");


    }
    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this, "fadein-to-fadeout");
    }
    public void go_to_profile(View view){

        Intent intent=new Intent(this,Accountdetails.class);
        startActivity(intent);
    }


}
