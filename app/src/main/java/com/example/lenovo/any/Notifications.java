package com.example.lenovo.any;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

import static com.example.lenovo.any.LoginActivity.FORUSRNAME;
import static com.example.lenovo.any.LoginActivity.SHARED_PREFS;

public class Notifications extends AppCompatActivity {
    public TextView notitext;
    public  String usrnaam;
    private NotificationAdapter adapter;
    public String idofquestion;
    public String locationofquestion;

    public String usrcity;
    public String userstateinfo;























    public  int i=0;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
public String idofdocs[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
       // notitext=findViewById(R.id.nottext);
        loadusername();
        loadcity();

           setUpRecyclerView();
    }


    @Override
    protected void onStart() {
        super.onStart();
        loadusername();
        loadcity();

        userstateinfo=usrcity+" _users";
       // db.collection( userstateinfo).document(usrnaam).collection("notifications");
adapter.startListening();








    }
    public void loadusername(){

        SharedPreferences username = getSharedPreferences(FORUSRNAME, MODE_PRIVATE);
        usrnaam=username.getString("usrname","");//emaailid


    }
    public void loadcity(){

        SharedPreferences username = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        usrcity=username.getString("lc","");


    }

    private void setUpRecyclerView(){

        loadcity();
        loadusername();
        userstateinfo=usrcity+" _users";



        Query query=db.collection( userstateinfo).document(usrnaam).collection("notifications").orderBy("priorityofdoc",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Notification> options=new FirestoreRecyclerOptions.Builder<Notification>().
                setQuery(query,Notification.class).build();

        adapter=new NotificationAdapter(options);
        RecyclerView recyclerView=findViewById(R.id.recycler_view_notifications);
        recyclerView.setHasFixedSize(true);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setAdapter(adapter);


         /////////////////////////////swipe logic

         new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                 ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
             @Override
             public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                 return false;
             }

             @Override
             public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    adapter.deleteItem(viewHolder.getAdapterPosition());
             }
         }).attachToRecyclerView(recyclerView );
















////////////////////////////////////////////////////
         adapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(  DocumentSnapshot documentSnapshot, int position) {
                 Notification  notification=documentSnapshot.toObject(Notification.class);
                 idofquestion=notification.getId();
                 locationofquestion=notification.getLocation();
                 Intent intent=new Intent(Notifications.this, ViewNotification.class);
                 intent.putExtra("idofquestion",idofquestion);
intent.putExtra("locationofquestion",locationofquestion);

                 startActivity(intent);

               //
                 //
                            }

         }
         );

    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    public void go_to_profile(View view){

        Intent intent=new Intent(this,Accountdetails.class);
        startActivity(intent);
    }


    public void go_to_home(View view){

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
