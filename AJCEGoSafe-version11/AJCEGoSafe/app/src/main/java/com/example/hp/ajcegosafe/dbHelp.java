package com.example.hp.ajcegosafe;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hp on 18-08-2017.
 */

public class dbHelp extends SQLiteOpenHelper {

    /*data base*/
    public static final  String DB_NAME="Outpass";
    public static final  int VERSION_NO=1;
    public static final String TABLE_NAME="StudentDetails";
    public static final String TABLE_NAME1="outpass";
    public static final String TABLE_NAME2="loginRole";
    public static final String TABLE_NAME3="login";
    public static final String TABLE_BRANCH="branch";
    public static final String TABLE_BATCH="batch";
    public static final String TABLE_START="start";

/* Student profile table */
    public static final String ID="student_id";
    public static final String LOGID="log_id";
    public static final String BID2="branch_id";
    public static final String ADNO2="admission_No";
    public static final String NAME="student_name";
    public static final String YEAR="year_duration";
    public static final String GEN="gender";
    public static final String DOB="Date_of_birth";
    public static final String STNO="student_number";
    public static final String PTNO="parent_number";

 /* Outpass table */
    public static final String OID="outpass_id";
    public static final String PID="profile_id";
    public static final String SNAME="student_name";
    public static final String PUR="purpose";
    public static final String DATE="current_date";
    public static final String DOL="date_of_leaving";
    public static final String DOR="date_of_return";
    public static final String TOL="time_of_leving";
    public static final String TOR="time_of_return";
    public static final String STATUS="status";

 /* Login role defining table */
    public static final String RID="role_id";
    public static final String ROLE="role";

 /* Login table */
    public static final String LID="login_id";
    public static final String RID2="roleid";
    public static final String ADNO="student_admno";
    public static final String PASS="password";

/* Branch table */
    public static final String BRID="branch_id";
    public static final String STREAMS="branches";

/* Batch Table */
    public static final String BID="batch_id";
    public static final String BNAME="batch_name";
    public static final String BRID2="branch_id";

/* Start Table */
    public static final String SID="start_id";
    public static final String ADNO3="admission_no";
    public static final String ROLE_ID="roll_id";

    public dbHelp(Context context) {
        super(context, DB_NAME, null, VERSION_NO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    /* creating tables */
        String ROLE_TABLE = "CREATE TABLE "+ TABLE_NAME2 +" ("+ RID +" INTEGER PRIMARY KEY, " +
                ""+ ROLE +" TEXT NOT NULL)";
        db.execSQL(ROLE_TABLE);

        String LOGIN_TABLE = "CREATE TABLE "+ TABLE_NAME3 +" ("+ LID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ RID2 +" INT NOT NULL, " +
                ""+ ADNO +" TEXT NOT NULL, "+ PASS +
                " TEXT NOT NULL, FOREIGN KEY ("+RID2+") REFERENCES "+TABLE_NAME2+"("+RID+"))";
        db.execSQL(LOGIN_TABLE);

        String PROFILE_TABLE = "CREATE TABLE "+ TABLE_NAME +" ("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ LOGID +" INT NOT NULL, "+ BID2 +" INT NOT NULL, " +
                ""+ NAME +" TEXT NOT NULL, "+ DOB +" DATE NOT NULL, "+ ADNO2 +" INT NOT NULL, "+ GEN +" TEXT NOT NULL, "+ YEAR +" TEXT NOT NULL, "+ STNO +" INT NOT NULL, "+ PTNO +
                " INT NOT NULL, FOREIGN KEY ("+LOGID+") REFERENCES "+TABLE_NAME3+"("+LID+"), FOREIGN KEY ("+BID2+") REFERENCES "+TABLE_BATCH+"("+BID+"))";
        db.execSQL(PROFILE_TABLE);

        String OUTPASS_TABLE = "CREATE TABLE "+ TABLE_NAME1 +" ("+ OID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + PID +" INTEGER , "+
                ""+ SNAME +" TEXT NOT NULL, "+ PUR +" TEXT NOT NULL, "+ DATE +" TEXT NOT NULL, "+ DOL +" TEXT NOT NULL, "+ DOR +
                " TEXT NOT NULL, "+ TOL +" TIME NOT NULL, "+ TOR + " TIME NOT NULL, "+ STATUS + " INT NOT NULL, FOREIGN KEY ("+PID+") REFERENCES "+TABLE_NAME+"("+ID+"))";
        db.execSQL(OUTPASS_TABLE);

        String BRANCH_TABLE = "CREATE TABLE "+ TABLE_BRANCH +" ("+ BRID +" INTEGER PRIMARY KEY, " +
                ""+ STREAMS +" TEXT NOT NULL)";
        db.execSQL(BRANCH_TABLE);

        String BATCH_TABLE = "CREATE TABLE "+ TABLE_BATCH +" ("+ BID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ BRID2 +" INT NOT NULL, " +
                ""+ BNAME +" TEXT NOT NULL,  FOREIGN KEY ("+BRID2+") REFERENCES "+TABLE_BRANCH+"("+BRID+"))";
        db.execSQL(BATCH_TABLE);

        String START_TABLE = "CREATE TABLE "+ TABLE_START +" ("+ SID +" INTEGER PRIMARY KEY  AUTOINCREMENT, " +
                ""+ ADNO3 +" TEXT NOT NULL, "+ ROLE_ID +" INTEGER)";
        db.execSQL(START_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

 /* insert data into Role table */
    public void insertRole(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query = " INSERT INTO "+TABLE_NAME2+ " VALUES('0','ADMIN');";
        db.execSQL(query);
        String query1 = " INSERT INTO "+TABLE_NAME2+ " VALUES('1','SECURITY');";
        db.execSQL(query1);
        String query2 = " INSERT INTO "+TABLE_NAME2+ " VALUES('2','STUDENT');";
        db.execSQL(query2);
    }
  /* Insert into admin and security data in login table */
    public void admin(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query = " INSERT INTO "+TABLE_NAME3+ " ("+RID2+","+ADNO+","+PASS+") VALUES('0','admin','0000');";
        db.execSQL(query);
        String query2 = " INSERT INTO "+TABLE_NAME3+ " ("+RID2+","+ADNO+","+PASS+") VALUES('1','security','0000');";
        db.execSQL(query2);
    }
  /* Insert branch values */
    public  void insertBranch(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query4 = " INSERT INTO "+TABLE_BRANCH+ " VALUES('0','Stream');";
        db.execSQL(query4);
        String query = " INSERT INTO "+TABLE_BRANCH+ " VALUES('1','MCA');";
        db.execSQL(query);
        String query2 = " INSERT INTO "+TABLE_BRANCH+ " VALUES('2','BTech');";
        db.execSQL(query2);
        String query3 = " INSERT INTO "+TABLE_BRANCH+ " VALUES('3','MTech');";
        db.execSQL(query3);
    }
  /* insert batch values */
    public void insertBatch(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query20 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('1','Branch');";
        db.execSQL(query20);
        String query21 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('2','Branch');";
        db.execSQL(query21);
        String query22 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('3','Branch');";
        db.execSQL(query22);
        String query = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('1','MCA LE');";
        db.execSQL(query);
        String query2 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('1','MCA Regular');";
        db.execSQL(query2);
        String query3 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('1','MCA Integrated');";
        db.execSQL(query3);
        String query4 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('2','Electronics & Communication Engineering');";
        db.execSQL(query4);
        String query5 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('2','Mechanical Engineering');";
        db.execSQL(query5);
        String query6 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('2','Chemical Engineering');";
        db.execSQL(query6);
        String query7 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('2','Civil Engineering');";
        db.execSQL(query7);
        String query8 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('3','Civil Engineering');";
        db.execSQL(query8);
        String query9 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('2','Metallurgy');";
        db.execSQL(query9);
        String query10 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('2','Automobile Engineering');";
        db.execSQL(query10);
        String query11 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('2','Computer Science & Engineering');";
        db.execSQL(query11);
        String query12 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('3','Computer Science & Engineering');";
        db.execSQL(query12);
        String query13 = " INSERT INTO "+TABLE_BATCH+ " ("+BRID2+","+BNAME+") VALUES('3','Metallurgy');";
        db.execSQL(query13);
    }

/* display list of names from student profile table */
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

    /* display list of outpass history details from outpass table */
    public Cursor getOutpassHistory(Integer adno) {
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor cur = db1.rawQuery("select * from "+TABLE_NAME+" WHERE "+ADNO2+"="+adno, null);
        cur.moveToNext();
        String pid = cur.getString(cur.getColumnIndex(ID));
        Integer p_id = Integer.parseInt(pid);

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data = db.rawQuery("select * from "+TABLE_NAME1+ " WHERE "+PID+"="+p_id+" ORDER BY "+OID+" DESC ", null);
        return data;
    }

    /* display list of log table details*/
    public Cursor getLogDetails() {

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data = db.rawQuery("select * from "+TABLE_NAME3, null);
        return data;
    }


    /* display list of names and outpass details from outpass table */
    public ArrayList<OutpassData> getOutpass() {
        SQLiteDatabase db=this.getWritableDatabase();
        Integer status = 0;
        Cursor cur = db.rawQuery("select * from "+TABLE_NAME1+" WHERE "+STATUS+"=0", null);
        ArrayList<OutpassData> olist = new ArrayList<OutpassData>();

        if(cur.getCount()!=0 ) {
            cur.moveToFirst();
            int c = cur.getCount();
            for (int i = 0; i < c; i++) {
                OutpassData out = new OutpassData();
                String oid = cur.getString(cur.getColumnIndex(OID));
                String pid = cur.getString(cur.getColumnIndex(PID));
                String purpose = cur.getString(cur.getColumnIndex(PUR));
                String date = cur.getString(cur.getColumnIndex(DATE));
                String sname = cur.getString(cur.getColumnIndex(SNAME));

                    out.setOid(oid);
                    out.setPid(pid);
                    out.setName(sname);
                    out.setPurpose(purpose);
                    out.setDate(date);
                    cur.moveToNext();
                    olist.add(out);
                }
            return olist;
        }
        else if(cur.getCount()==0 ) {

            OutpassData out = new OutpassData();

            out.setOid("Nothing to show");
            out.setName("Nothing to show");
            out.setPurpose("Nothing to show");
            out.setPid("1");
            olist.add(out);
            return olist;
        }

        return null;
    }

    /* display list of names and outpass details from outpass table */
    public ArrayList<SecurityOutpassData> getOutpassForSecurity() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur = db.rawQuery("select * from "+TABLE_NAME1+" WHERE "+STATUS+"=1", null);
        ArrayList<SecurityOutpassData> olist2 = new ArrayList<SecurityOutpassData>();

        if(cur.getCount()!=0 ) {
            cur.moveToFirst();
            int c = cur.getCount();
            for (int i = 0; i < c; i++) {
                SecurityOutpassData out2 = new SecurityOutpassData();
                String oid = cur.getString(cur.getColumnIndex(OID));
                String sname = cur.getString(cur.getColumnIndex(SNAME));
                String status = cur.getString(cur.getColumnIndex(STATUS));
                String pid = cur.getString(cur.getColumnIndex(PID));

                out2.setOid(oid);
                out2.setName(sname);
                out2.setStatus(status);
                out2.setPid(pid);
                cur.moveToNext();
                olist2.add(out2);
            }
            return olist2;
        }
        else if(cur.getCount()==0 ) {

                SecurityOutpassData out2 = new SecurityOutpassData();

                out2.setOid("Nothing to show");
                out2.setName("Nothing to show");
                out2.setStatus("Nothing to show");
                out2.setPid("1");
                olist2.add(out2);
                return olist2;
        }

        return null;
    }

    /* display list of names and outpass details from outpass table */
    public ArrayList<SecurityOutpassReturn> getOutpassForSecurityReturn() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur = db.rawQuery("select * from "+TABLE_NAME1+" WHERE "+STATUS+"=3", null);
        ArrayList<SecurityOutpassReturn> olist = new ArrayList<SecurityOutpassReturn>();

        if(cur.getCount()!=0 ) {
            cur.moveToFirst();
            int c = cur.getCount();
            for (int i = 0; i < c; i++) {
                SecurityOutpassReturn out = new SecurityOutpassReturn();
                String oid = cur.getString(cur.getColumnIndex(OID));
                String sname = cur.getString(cur.getColumnIndex(SNAME));
                String pid = cur.getString(cur.getColumnIndex(PID));

                out.setR_name(sname);
                out.setR_oid(oid);
                out.setR_pid(pid);
                cur.moveToNext();
                olist.add(out);
            }
            return olist;
        }
        else if(cur.getCount()==0 ) {

            SecurityOutpassReturn out = new SecurityOutpassReturn();

            out.setR_oid("Nothing to show");
            out.setR_name("Nothing to show");
            out.setR_pid("1");
            olist.add(out);
            return olist;
        }

        return null;
    }

    /* get batch values from batch table-for spinner */
    public List<String> getAllBatches( String stream){
        List<String> batches = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BATCH +","+ TABLE_BRANCH +" WHERE "
                +TABLE_BRANCH+"."+STREAMS+" = '"+stream+"' AND "+TABLE_BATCH+"."+BRID2+" = "+TABLE_BRANCH+"."+BRID ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                batches.add(cursor.getString(cursor.getColumnIndex(BNAME)));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return batches;
    }

    /* get batch values from branch table-Streams-for spinner-student search */
    public List<String> getAllStrams(){
        List<String> streams = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BRANCH ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                streams.add(cursor.getString(cursor.getColumnIndex(STREAMS)));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return streams;
    }

    /* change status value into 1 in outpass table */
    public void changeStatusToOne( int oid){

        String selectQuery = "UPDATE "+TABLE_NAME1+ " SET "+STATUS+" = 1 WHERE "+OID+" = "+oid;

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(selectQuery);

    }

    /* change status value into 1 in outpass table */
    public void changeStatusToTwo( int oid){

        String selectQuery = "UPDATE "+TABLE_NAME1+ " SET "+STATUS+" = 2 WHERE "+OID+" = "+oid;

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(selectQuery);

    }

    /* change status value into 3 in outpass table */
    public void changeStatusToThree( int oid){

        String selectQuery = "UPDATE "+TABLE_NAME1+ " SET "+STATUS+" = 3 WHERE "+OID+" = "+oid;

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(selectQuery);

    }

    /* change status value into 4 in outpass table */
    public void changeStatusToFour( int oid){

        String selectQuery = "UPDATE "+TABLE_NAME1+ " SET "+STATUS+" = 4 WHERE "+OID+" = "+oid;

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(selectQuery);

    }

    public void deleteStartTableContent(){
        SQLiteDatabase db = this.getReadableDatabase();
        String delete = "DELETE FROM "+TABLE_START;
        db.execSQL(delete);
    }
}
