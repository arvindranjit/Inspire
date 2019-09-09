package com.arvindranjit.inspire;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.arvindranjit.inspire.Todo;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Todo> todosList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView todo;
        public TextView timestamp;
        public TextView datestamp;
        public CardView cardview;
        public ConstraintLayout constraintLayout;

        public MyViewHolder(View view) {
            super(view);
            todo = view.findViewById(R.id.label);
            timestamp = view.findViewById(R.id.timestamp);
            datestamp = view.findViewById(R.id.datestamp);
            cardview = view.findViewById(R.id.cardview);
            constraintLayout = view.findViewById(R.id.constraintLayout3);
        }
    }


    public RecyclerAdapter(Context context, List<Todo> todosList) {
        this.context = context;
        this.todosList = todosList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Todo todo = todosList.get(position);

        holder.todo.setText(todo.getLabel());

        String bydatetime =  todo.getByTimestamp();

        String years = bydatetime.substring(0,4);

        String months = bydatetime.substring(5,7);
        String days = bydatetime.substring(8,10);
        String hours = bydatetime.substring(11,13);
        String minutes = bydatetime.substring(14,16);
        int colour = todo.getcolor1();


      int yearsint = Integer.parseInt(years);
        int monthsint = Integer.parseInt(months);
        int daysint = Integer.parseInt(days);
        int hoursint = Integer.parseInt(hours);

        String ap = "am";


        if(hoursint>11){

            ap = "pm";

            if(hoursint>12){
                hoursint = hoursint - 12;
            }
        }else if(hoursint == 0){

            hoursint = 12;
        }


        String time = "Due by " + hoursint + ":" + minutes + " " + ap;



        Calendar c = Calendar.getInstance();
      c.set(Calendar.YEAR, yearsint);
        c.set(Calendar.MONTH, monthsint);
       c.set(Calendar.DAY_OF_MONTH, daysint);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());



        if(todo.getStatus()==0) {
            holder.timestamp.setText(time);
            holder.datestamp.setText(currentDateString);
        } else {

            holder.timestamp.setText("Completed");
            holder.datestamp.setText("");
        }


        if(todo.getStatus()==0) {

            Colour_Picker mcolour_picker = new Colour_Picker();
            int[] gColors = mcolour_picker.getGcolors(colour);
            int colour1 = gColors[0];
            holder.constraintLayout.setBackgroundColor(colour1);
        } else{

            Colour_Picker mcolour_picker = new Colour_Picker();
            int[] gColors = mcolour_picker.getGcolors(17);
            int colour1 = gColors[0];
            holder.constraintLayout.setBackgroundColor(colour1);

        }




       /* GradientDrawable gd2 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {gColors[0], gColors[1]});
        gd2.setCornerRadius(100); */














    }

    @Override
    public int getItemCount() {
        return todosList.size();
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }

}