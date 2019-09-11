package com.arvindranjit.inspire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Todo_Edit extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{
    Todo t;
    private DatabaseHelper db;
    private List<Todo> todosList = new ArrayList<>();
    int dateflag = 0;
    int timeflag = 0;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String bydatetime;
    String dateglobal;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo__edit);


        db = new DatabaseHelper(this);

        todosList.addAll(db.getAllTodos());

        Intent mIntent = getIntent();
        position = mIntent.getIntExtra("position", 0);







        Button datebutton = (Button) findViewById(R.id.datebtn);
        Button timebutton = (Button) findViewById(R.id.timebtn);
        Button cancelbutton = findViewById(R.id.cancelbtn);
        Button deletebutton = (Button) findViewById(R.id.deletebtn);
        TextView savetextview = findViewById(R.id.savetextview);
        final TextView timetextview = findViewById(R.id.timetextview);
        TextView datetextview = findViewById(R.id.datetextview);
        final TextInputEditText labeledittext = findViewById(R.id.labelTextInputEditText);
        final TextInputEditText difficultyedittext = findViewById(R.id.diffTextInputEditText);


        db = new DatabaseHelper(this);
         t = todosList.get(position);



       String labels = t.getLabel();
       int difficulty = t.getDifficulty();

        labeledittext.setText(labels);
        difficultyedittext.setText(Integer.toString(difficulty));



        bydatetime = t.getByTimestamp();
        dateglobal = bydatetime;
        String hours = bydatetime.substring(11,13);
        String minutes = bydatetime.substring(14,16);
        mMinute = Integer.parseInt(minutes);
        mHour = Integer.parseInt(hours);
        int hoursint = mHour;
        int minutesint = mMinute;
        String years = bydatetime.substring(0,4);

        String months = bydatetime.substring(5,7);
        String days = bydatetime.substring(8,10);
        mYear = Integer.parseInt(years);
        mMonth = Integer.parseInt(months);
        mDay = Integer.parseInt(days);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, mYear);
        c.set(Calendar.MONTH, mMonth);
        c.set(Calendar.DAY_OF_MONTH, mDay);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        datetextview.setText(currentDateString);

        String ap = "am";


        if(hoursint>11){

            ap = "pm";

            if(hoursint>12){
                hoursint = hoursint - 12;
            }
        }else if(hoursint == 0){

            hoursint = 12;
        }


        String time =  hoursint + ":" + minutes + " " + ap;
        timetextview.setText(time);






        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });



        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        savetextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String labels = labeledittext.getText().toString();

                if ((labels.matches(""))) {
                    Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();

                } else {



                int difficultyint = 0;


                String months = Integer.toString(mMonth);
                String days = Integer.toString(mDay);
                String years = Integer.toString(mYear);
                String minutes = Integer.toString(mMinute);
                String hours = Integer.toString(mHour);

                mMonth = Integer.parseInt(months);
                mDay = Integer.parseInt(days);
                mYear = Integer.parseInt(years);


                String ap = "am";


                if (mMinute < 10) {

                    minutes = '0' + minutes;

                }
                if (mMonth < 10) {

                    months = '0' + months;

                }
                if (mDay < 10) {

                    days = '0' + days;

                }
                if (mHour < 10) {

                    hours = '0' + hours;

                }


                // updating note text
                String datetime = mYear + "-" + months + "-" + days + " " + hours + ":" + minutes;
                t.setLabel(labels);


                t.setByTimestamp(datetime);
                    difficultyint = 0;
                t.setDifficulty(difficultyint);

                // updating note in db
                db.updateTodo(t);
                finish();


            }
        }
        });


        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }



            });





        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                db.deleteNote(todosList.get(position));
                todosList.remove(t);
                finish();
            }



        });




    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.timetextview);
        mMinute= minute;
        mHour = hourOfDay;
        String ap = "am";
        int hourint = mHour;
        if(mHour>11){

            ap = "pm";

            if(mHour>12){
                hourint = hourint - 12;
            }
        } else if(mHour == 0){

            hourint = 12;
        }
        String minutes = Integer.toString(minute);

        String hours = Integer.toString(hourOfDay);

        if(minute<10){

            minutes = '0' +minutes;

        }

        if(hourOfDay<10){

            hours= '0' +hours;

        }

        textView.setText(hourint + " : " + minutes + " " + ap);

        timeflag = 1;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        mYear = year;
        mDay = dayOfMonth;
        mMonth = month;
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.datetextview);
        textView.setText(currentDateString);
        dateflag = 1;
    }






}
