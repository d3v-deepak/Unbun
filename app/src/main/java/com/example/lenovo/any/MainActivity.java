package com.example.lenovo.any;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
public String usercity;

   // public FirebaseFirestore db = FirebaseFirestore.getInstance();
    //public CollectionReference notebookRef = db.collection(usercity);

    public FirebaseFirestore db ;
    public CollectionReference notebookRef ;


    //public String answer2;

    private NoteAdapter adapter;
    //String answer2;


  /*  public void answer(View view){

        Intent intent=new Intent(MainActivity.this, AnswerActivity.class);
        startActivity(intent);
}*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setUpRecyclerView();

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
    int rep_count=note.getReport_count();
                String classname=MainActivity.class.getSimpleName();

                Intent intent=new Intent(MainActivity.this, AnswerActivity.class);
                //notebookRef.document(id).update("","");
               intent.putExtra("name",name);
intent.putExtra("usrname",usernaam);
               intent.putExtra("date",date);
                intent.putExtra("docid",id);
                intent.putExtra("ques",ques);
                intent.putExtra("rep_count",rep_count);
                intent.putExtra("Activityname",classname);
                intent.putExtra("preans",preans);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

              FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null){

            sendToLogin();

        }

        load();
                db = FirebaseFirestore.getInstance();
        notebookRef = db.collection(usercity+" mainactivity");
      //  Toast.makeText(this,usercity, Toast.LENGTH_SHORT).show();

       setUpRecyclerView();

        adapter.startListening();
       }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
public void add(View view){


    startActivity(new Intent(MainActivity.this, NewNoteActivity.class));


}


public void sendToLogin(){

    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
    startActivity(loginIntent);
    finish();


}


    public void load(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        usercity = sharedPreferences.getString("lc", "");
    }


    public void gotonotification(View view){

        startActivity(new Intent(this,Notifications.class));

    }

public void to_other_activities(View view){

        Intent intent=new Intent(this,technology.class);
        startActivity(intent);
    CustomIntent.customType(this, "left-to-right");
       // finish();




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