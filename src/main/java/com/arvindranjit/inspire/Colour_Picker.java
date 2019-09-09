package com.arvindranjit.inspire;

import android.graphics.Color;

import java.util.Random;

public class Colour_Picker {


    private String mColors[] = {
            "#FF1744",
            "#E53935",
            "#E91E63",
            "#9C27B0",
            "#D500F9",
            "#7C4DFF",
            "#3D5AFE",
            "#2196F3",
            "#43A047",
            "#00E676",
            "#76FF03",
            "#EEFF41",
            "#C6FF00",
            "#FFFF00",
            "#FFA000",
            "#FF6D00",
            "#F4511E",
            "#90A4AE",

    };



    private String mColors2[] = {
            "#FF1744",
            "#E53935",
            "#E91E63",
            "#9C27B0",
            "#D500F9",
            "#7C4DFF",
            "#3D5AFE",
            "#2196F3",
            "#43A047",
            "#00E676",
            "#76FF03",
            "#EEFF41",
            "#C6FF00",
            "#FFFF00",
            "#FFA000",
            "#FF6D00",
            "#F4511E",
            "#90A4AE",

    };


    public int[] getGcolors(int a) {
        String color = "#ff9a9e";
        String color2 = "#fad0c4";


        color = mColors[a];
        color2 = mColors2[a];
        int colorAsInt = Color.parseColor(color);
        int colorAsInt2 = Color.parseColor(color2);

        return new int[]{colorAsInt, colorAsInt2};


    }

}
