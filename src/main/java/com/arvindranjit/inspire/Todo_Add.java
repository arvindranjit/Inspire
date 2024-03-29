package com.arvindranjit.inspire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

public class Todo_Add extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    private DatabaseHelper db;
    int dateflag = 0;
    int timeflag = 0;
    private int mYear, mMonth, mDay, mHour, mMinute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_add);



        Button datebutton = (Button) findViewById(R.id.datebtn);
        Button timebutton = (Button) findViewById(R.id.timebtn);
        Button cancelbutton = (Button) findViewById(R.id.cancelbtn);
        TextView savetextview = findViewById(R.id.savetextview);
        TextView timetextview = findViewById(R.id.timetextview);
        TextView datetextview = findViewById(R.id.datetextview);
        final TextInputEditText labeledittext = findViewById(R.id.labelTextInputEditText);
        final TextInputEditText difficultyedittext = findViewById(R.id.diffTextInputEditText);

        db = new DatabaseHelper(this);

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



                String months = "11";
                String days = "22";
                String hours = "22";
                String minutes = "22";
                String year = "2011";


                if(dateflag==1){

                    months = Integer.toString(mMonth);
                    days = Integer.toString(mDay);
                    year = Integer.toString(mYear);

                    if(mMonth<10){

                        months = '0' +months;

                    }
                    if(mDay<10){

                        days = '0' +days;

                    }



                }

                if(timeflag==1){
                    hours = Integer.toString(mHour);
                    minutes = Integer.toString(mMinute);

                    if(mMinute<10){

                        minutes = '0' +minutes;

                    }
                    if(mHour<10){

                        hours = '0' +hours;

                    }

                }









                if(!(labels.matches("")) ){


                    String datetime = year + "-" + months + "-" + days + " " + hours + ":" + minutes;


                    Random r = new Random();
                    int color1 = r.nextInt(17);
                    int color2 = r.nextInt(10);
                    int difficultyint = 0;


                    long id = db.insertTodo(labels,datetime,difficultyint,color1,color2,dateflag,timeflag);





                    finish();


                } else{

                    Toast.makeText(getApplicationContext(),"Please enter label",Toast.LENGTH_SHORT).show();
                }


            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });



    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.timetextview);
        mMinute= minute;
        mHour = hourOfDay;
        int hour = mHour;
        String ap = "am";

        if(mHour>11){

            ap = "pm";

            if(mHour>12){
                hour = hour - 12;
            }
        } else if(mHour == 0){

            hour = 12;
        }
        String minutes = Integer.toString(minute);
        if(minute<10){

            minutes = '0' +minutes;

        }

        textView.setText(hour + ":" + minutes + " " + ap);
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
