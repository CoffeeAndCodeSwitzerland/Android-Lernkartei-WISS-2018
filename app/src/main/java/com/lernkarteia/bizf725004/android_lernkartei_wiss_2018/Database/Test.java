package com.lernkarteia.bizf725004.android_lernkartei_wiss_2018.Database;

import android.content.Context;
import android.util.Log;

public class Test {


    DBDriver db;

    public Test(Context c) {
        db  = new DBDriver(c, "Test");
        testdb();
    }

    private void testdb(){
        try{
            db.doCommand("create table testtable(id integer primary key autoincrement, Kartei text)");
        } catch (Exception e){
            Log.wtf("DB", "Exception on creating table");
        }

        try{
            db.doCommand("insert into testtable(Kartei) values ('Dada'), " +
                    "('Hansjörg'), " +
                    "('MiHa04'), " +
                    "('MaPf06'), " +
                    "('Lethal Tempo')");
        }catch (Exception e){
            Log.wtf("DB", "Exception on inserting data");

        }

        try{
            if (db.doQuery("select Kartei from testtable"))
                Log.d("DB", "Query was successfully run");

        } catch (Exception e){
            Log.wtf("DB", "Fuckthisshit: " + e.getMessage());
        }

        try{
            db.getResultPIntValueOf("Kartei");
        } catch (Exception e) {
            Log.wtf("DB", "Getting column failed: " + e.getMessage());
        }

        try{
            db.resetResult();
            db.isThereAResult();
            if (db.getResultValueOf(0) == null) Log.wtf("DB","pls kill me now");
        } catch(Exception e) {
            Log.wtf("DB", "Getting dada failed: " + e.getMessage());
        }

        try {
            db.doCommand("Drop table testtable");
        } catch (Exception e) {
            Log.wtf("DB",  "Failed dropping the table: " + e.getMessage());
        }

        Log.d("DB", "Finished the test");
    }
}
