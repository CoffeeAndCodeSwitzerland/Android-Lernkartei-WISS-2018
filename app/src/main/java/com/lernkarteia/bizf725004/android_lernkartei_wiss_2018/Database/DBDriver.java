package com.lernkarteia.bizf725004.android_lernkartei_wiss_2018.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * class DB Driver
 * 
 * @author Michèle, Thierry, Marcel
 */
public class DBDriver extends SQLiteOpenHelper{

	Cursor result; //Output of the SQL query

    public DBDriver(Context context, String databaseName) {
        super(context, databaseName, null, 1);

    }

    /**
     * To execute a SQL query (like SELECT)
     *
     * @param query
     * @return the result set or null for errors
     * @throws Exception
     */
    public boolean doQuery(String query) throws Exception {
        //try {
            SQLiteDatabase db = this.getWritableDatabase();
            result = db.rawQuery(query, null);
            return true;
        //} catch (Exception e) {
            //return false;
        //}
    }

    public Cursor getLastResult() {return result;}

	public void setLastResultSet(Cursor lastResultSet) {
		this.result = lastResultSet;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public enum ResultType {
		DONE, NOTDONE, WARNING, FATAL_ERROR, NO_CONNECTION_ERROR, WRONG_PRARMETER_ERROR
	}


	/**
	 * To execute a SQL command for Create, Insert, Update, Delete
	 * but except a query like SELECT (Read)
	 * 
	 * @param SQLcommand
	 * @return 0    nothing done
	 * 		   n>0  row count (number of operations)
	 *         -1 	on error
	 * @throws Exception 
	 */
	public boolean doCommand(String SQLcommand) throws Exception {
		// Returns: either (1) the row count for SQL Data Manipulation Language (DML) statements or
		//                 (2) 0 for SQL statements that return nothing
		try {
		    SQLiteDatabase db = this.getWritableDatabase();
		    db.execSQL(SQLcommand);
		    return true;
		} catch (Exception e) {
	       return false;
        }
	}

	/**
	 * To check if there is a valid result set
	 * 
	 * @return true if ok, false if empty or on errors
	 * @throws Exception 
	 */
	public boolean isThereAResult() throws Exception {
		try {
			if (result != null) {
				return result.moveToNext();
			}
			throw new Exception ("did not have any resultset ready");
		} catch (SQLException e) {
		}
		return false;
	}


	public void resetResult() {
	    result.moveToFirst();
    }
	
	/**
	 * To get a value of specific attribute of the SQL result set
	 * 
	 * @param attr
	 * @return the value or null if not found or on errors
	 * @throws Exception 
	 */
	public String getResultValueOf(int attr) throws Exception {
        if (result != null) {
            try {
                return result.getString(attr);
            } catch (SQLException e) {
                throw new Exception("did not found the attribute in the resulset:" + e.getMessage() + attr);
            }
        }
        return null;
	}

	/**
	 * To get a positive integer value of specific attribute of the SQL result
	 * set
	 * 
	 * @param attr
	 * @return the integer value >= 0 or -1, -2 on errors
	 * @throws Exception 
	 */
	public int getResultPIntValueOf(String attr) throws Exception {
		if (attr == null) {
			throw new Exception ("may not seek a {null} attribute in the resultset");
		}
		try {
		    if (result != null) {
		        //Returns: the number of the column with the name attr
                if (result.getColumnIndex(attr) > 0) {
                    if (result.getString(result.getColumnIndex(attr)) == null) {
                        return  result.getColumnIndex(attr);
                    }
                    return result.getColumnIndex(attr);
                }
                return 0;
            }
			throw new Exception ("did not have any resultset ready to get the integer value of the attribute "+attr);
		} catch (SQLException e) {
			throw new Exception ("did not found the integer value of the attribute in the resulset "+attr);
		}
	}


    /**
     * We are very sorry for you if you have to understand this here
     */
}
