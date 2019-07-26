package com.example.lenovo.any;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;

import static com.example.lenovo.any.LoginActivity.FORUSERNAME;
import static com.example.lenovo.any.LoginActivity.FORUSERSTATE;
import static com.example.lenovo.any.LoginActivity.FORUSRNAME;

public class NewNoteActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";

public String usrnaam;
    public String naam;
    public String userstate;
    private EditText editTextDescription;
public int report_count;
    private NumberPicker numberPickerPriority;
    public EditText othercityname;
    String usercity;
    public CheckBox tech;
    public CheckBox politics;
    public CheckBox mycity;
    public CheckBox buisness;
    public CheckBox sports;
    public CheckBox movies;
    public CheckBox mystate;

    public  String title;
    public String asker;
public CheckBox anonym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        anonym=findViewById(R.id.checkanonymous);
othercityname=findViewById(R.id.etothercity);
        tech=findViewById(R.id.checkBox_technology);
        politics=findViewById(R.id.checkBox_politics);
        mystate=findViewById(R.id.checkBox_yourstate);
        buisness=findViewById(R.id.checkBox_buisness);
        sports=findViewById(R.id.checkBox_sports);
        movies=findViewById(R.id.checkBox_movies);
        mycity=findViewById(R.id.checkBox_my_city);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
        load();
        loadusername();


    }
    public void saveNote(View view) {
        load();
        loadnaam();
        loaduserstate();
        if(anonym.isChecked()){

          title="anonymous";


        }


         title = naam;
String username=usrnaam;
report_count=0;
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();
       // numberPickerPriority.setValue(2);
        String answer="";

         Calendar calendar = Calendar.getInstance();
        String date = DateFormat.getDateInstance().format(calendar.getTime());


        if (description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a name and question", Toast.LENGTH_SHORT).show();
            return;
        }


 ////////////////////////////////////////////////////////////////////

        if (politics.isChecked()){

            CollectionReference notebookRef = FirebaseFirestore.getInstance()
                    .collection(userstate+" politics");
            notebookRef.add(new Note(title, description,answer,date,priority,username,report_count));
            Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show();

        }
        if (tech.isChecked()){

            CollectionReference notebookRef = FirebaseFirestore.getInstance()
                    .collection(userstate+" technology");
            notebookRef.add(new Note(title, description,answer,date,priority,username,report_count));
            Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show();

        }

        if (buisness.isChecked()){

            CollectionReference notebookRef = FirebaseFirestore.getInstance()
                    .collection(userstate+" buisness");
            notebookRef.add(new Note(title, description,answer,date,priority,username,report_count));
            Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show();

        }

        if (movies.isChecked()){

            CollectionReference notebookRef = FirebaseFirestore.getInstance()
                    .collection(userstate+" movies");
            notebookRef.add(new Note(title, description,answer,date,priority,username,report_count));
            Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show();

        }

        if (sports.isChecked()){

            CollectionReference notebookRef = FirebaseFirestore.getInstance()
                    .collection(userstate+" sports");
            notebookRef.add(new Note(title, description,answer,date,priority,username,report_count));
            Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show();

        }
        if (mystate.isChecked()){

            CollectionReference notebookRef = FirebaseFirestore.getInstance()
                    .collection(userstate+" mystate");
            notebookRef.add(new Note(title, description,answer,date,priority,username,report_count));
            Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show();

        }

        if(mycity.isChecked()){
            load();
            CollectionReference notebookRef = FirebaseFirestore.getInstance()
                    .collection(usercity+" mainactivity");
            notebookRef.add(new Note(title, description,answer,date,priority,username,report_count));//(oneliner,email,dpcode
            Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show();
            //  Toast.makeText(this, asker, Toast.LENGTH_SHORT).show();

        }



          if(othercityname.getText()!=null)
        {
           String Cityname= othercityname.getText().toString();
            CollectionReference notebookRef = FirebaseFirestore.getInstance()
                    .collection(Cityname);
            notebookRef.add(new Note(title, description,answer,date,priority,username,report_count));
            Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show();

        }
        finish();

}


    public void load(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        usercity = sharedPreferences.getString("lc", "");


    }

    public void loadnaam(){


        SharedPreferences usrname = getSharedPreferences(FORUSERNAME, MODE_PRIVATE);
        naam=usrname.getString("username","");//person name from email
    }

    public void loadusername(){

        SharedPreferences username = getSharedPreferences(FORUSRNAME, MODE_PRIVATE);
        usrnaam=username.getString("usrname","");//contains email


    }

    public void loaduserstate(){

        SharedPreferences stateofuser = getSharedPreferences(FORUSERSTATE,MODE_PRIVATE);
       userstate =stateofuser.getString("state","");//contains state


    }

   public void toothercity(View view){
        othercityname.setVisibility(View.VISIBLE);

   }


}
