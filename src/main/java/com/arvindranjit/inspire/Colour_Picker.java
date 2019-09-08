package com.arvindranjit.inspire;

import android.graphics.Color;

import java.util.Random;

public class Colour_Picker {


    private String mColors[] = {
            "#FF5722",
            "#00E676",
            "#03A9F4",
            "#EC407A",
            "#AB47BC",
            "#2196F3",
            "#78909C",
            "#D32F2F",
            "#FF1744",
            "#3F51B5",
            "#009688",
            "#558B2F",
            "#FF9800"

    };



    private String mColors2[] = {
            "#fad0c4",
            "#fbc2eb",
            "#ffd1ff",
            "#fcb69f",
            "#fecfef",
            "#fda085",
            "#a6c1ee",
            "#e6dee9",
            "#c2e9fb",
            "#96e6a1",
            "#8fd3f4",
            "#e2ebf0",
            "#d57eeb",


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
