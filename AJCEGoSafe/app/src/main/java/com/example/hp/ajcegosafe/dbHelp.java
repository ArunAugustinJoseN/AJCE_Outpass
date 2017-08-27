package com.example.hp.ajcegosafe;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by hp on 18-08-2017.
 */

public class dbHelp extends SQLiteOpenHelper {

    public static final  String DB_NAME="Outpass";
    public static final  int VERSION_NO=1;
    public static final String TABLE_NAME="StudentDetails";
    public static final String TABLE_NAME1="outpass";
    public static final String TABLE_NAME2="loginRole";
    public static final String TABLE_NAME3="login";

    public static final String ID="student_id";
    public static final String LOGID="log_id";
    public static final String NAME="student_name";
    public static final String GEN="gender";
    public static final String DOB="Date_of_birth";
    public static final String STNO="student_number";
    public static final String PTNO="parent_number";

    public static final String OID="outpass_id";
    public static final String PUR="purpose";
    public static final String DATE="current_date";
    public static final String DOL="date_of_leaving";
    public static final String DOR="date_of_return";
    public static final String TOL="time_of_leving";
    public static final String TOR="time_of_return";
    public static final String STATUS="status";

    public static final String RID="role_id";
    public static final String ROLE="role";

    public static final String LID="login_id";
    public static final String RID2="roleid";
    public static final String ADNO="student_admno";
    public static final String PASS="password";


    public dbHelp(Context context) {
        super(context, DB_NAME, null, VERSION_NO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String ROLE_TABLE = "CREATE TABLE "+ TABLE_NAME2 +" ("+ RID +" INTEGER PRIMARY KEY, " +
                ""+ ROLE +" TEXT NOT NULL)";
        db.execSQL(ROLE_TABLE);



        String LOGIN_TABLE = "CREATE TABLE "+ TABLE_NAME3 +" ("+ LID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ RID2 +" INT NOT NULL, " +
                ""+ ADNO +" INT NOT NULL, "+ PASS +
                " TEXT NOT NULL, FOREIGN KEY ("+RID2+") REFERENCES "+TABLE_NAME2+"("+RID+"))";
        db.execSQL(LOGIN_TABLE);

        String PROFILE_TABLE = "CREATE TABLE "+ TABLE_NAME +" ("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ LOGID +" INT NOT NULL, " +
                ""+ NAME +" TEXT NOT NULL, "+ DOB +" DATE NOT NULL, "+ GEN +" TEXT NOT NULL, "+ STNO +" INT NOT NULL, "+ PTNO +
                " INT NOT NULL, FOREIGN KEY ("+LOGID+") REFERENCES "+TABLE_NAME3+"("+LID+"))";
        db.execSQL(PROFILE_TABLE);

        String OUTPASS_TABLE = "CREATE TABLE "+ TABLE_NAME1 +" ("+ OID +" INTEGER PRIMARY KEY, " +
                ""+ PUR +" TEXT NOT NULL, "+ DATE +" DATE NOT NULL, "+ DOL +" DATE NOT NULL, "+ DOR +
                " DATE NOT NULL, "+ TOL +" TIME NOT NULL, "+ TOR + " TIME NOT NULL, "+ STATUS + " INT NOT NULL)";
        db.execSQL(OUTPASS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    public void insertRole(){
//        SQLiteDatabase db=this.getWritableDatabase();
//        String query = " INSERT INTO "+TABLE_NAME2+ " VALUES('0','ADMIN');";
//        db.execSQL(query);
//        String query1 = " INSERT INTO "+TABLE_NAME2+ " VALUES('1','SECURITY');";
//        db.execSQL(query1);
//        String query2 = " INSERT INTO "+TABLE_NAME2+ " VALUES('2','STUDENT');";
//        db.execSQL(query2);
//    }

    public ArrayList<Profile> getSearch() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur = db.rawQuery("select * from "+TABLE_NAME, null);
        ArrayList<Profile> plist = new ArrayList<Profile>();

        if(cur.getCount()!=0) {
            cur.moveToFirst();
            int c = cur.getCount();
            for (int i = 0; i < c; i++) {
                Profile pro=new Profile();
                String name= cur.getString(cur.getColumnIndex(NAME));
                pro.setName(name);
                cur.moveToNext();
                plist.add(pro);
            }
            return plist;
        }

        return null;
    }
}
