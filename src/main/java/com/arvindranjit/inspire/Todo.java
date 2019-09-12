package com.arvindranjit.inspire;

public class Todo {


    public static final String TABLE_NAME = "todo";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LABEL = "label";
    public static final String COLUMN_BYTIMESTAMP = "bytimestamp";
    public static final String COLUMN_DIFFICULTY = "difficulty";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_COLOR1 = "color1";
    public static final String COLUMN_COLOR2 = "color2";
    public static final String COLUMN_DATEFLAG = "dateflag";
    public static final String COLUMN_TIMEFLAG = "timeflag";

    private int id;
    private String label;
    private String timestamp;
    private String bytimestamp;
    private int status;
    private int difficulty;
    private int color1;
    private int color2;
    private int timeflag;
    private int dateflag;




    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_LABEL + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + COLUMN_BYTIMESTAMP + " STRING,"
                    + COLUMN_DIFFICULTY + " INTEGER,"
                    + COLUMN_STATUS + " INTEGER,"
                    + COLUMN_COLOR1 + " INTEGER,"
                    + COLUMN_COLOR2+ " INTEGER,"
                    + COLUMN_DATEFLAG + " INTEGER,"
                    + COLUMN_TIMEFLAG + " INTEGER"
                    + ")";

    public Todo() {
    }

    public Todo(int id, String label, String timestamp, String bytimestamp, int difficulty, int status,int color1, int color2,int dateflag, int timeflag) {
        this.id = id;
        this.label = label;
        this.timestamp = timestamp;
        this.bytimestamp = bytimestamp;
        this.difficulty = difficulty;
        this.status = status;
        this.color1 = color1;
        this.color2 = color2;
        this.dateflag = dateflag;
        this.timeflag = timeflag;
    }

    public int getId() {
        return id;
    }
    public String getLabel() {
        return label;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getDifficulty() {
        return difficulty;
    }
    public int getStatus() {
        return status;
    }
    public String getByTimestamp() {
        return bytimestamp;
    }
    public int getcolor1() {
        return color1;
    }
    public int getcolor2() {
        return color2;
    }
    public int getTimeflag() {
        return timeflag;
    }
    public int getDateflag() {
        return dateflag;
    }



    public void setId(int id) {
        this.id = id;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setByTimestamp(String bytimestamp) {
        this.bytimestamp = bytimestamp;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setcolor1(int color1) {
        this.color1 = color1;
    }
    public void setcolor2(int color2) {
        this.color2 = color2;
    }
    public void setTimeflag(int timeflag) {
        this.timeflag = timeflag;
    }
    public void setDateflag(int dateflag) {
        this.dateflag = dateflag;
    }









}
