package sanjay.arya.say;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjay on 11/23/15.
 */
public class SayQuery {

    String columns = "*";
    String Table_Name = "";
    String Where = "";
    String orderBy = "";
    String limit = "";

    public SayQuery(String Table_Name) {
        this.Table_Name = Table_Name;
    }

    public void whereEqualTo(String column, String value) {
        if (Where.length() > 0)
            Where = " AND " + column + " = \"" + value + "\"";
        else
            Where = " where " + column + " = \"" + value + "\"";
    }

    public void whereLessThan(String column, String value) {
        if (Where.length() > 0)
            Where = " AND " + column + " < \"" + value + "\"";
        else
            Where = " where " + column + " < \"" + value + "\"";
    }

    public void whereNotEqualTo(String column, String value) {
        if (Where.length() > 0)
            Where = " AND " + column + " != \"" + value + "\"";
        else
            Where = " where " + column + " != \"" + value + "\"";
    }

    public void whereGreaterThan(String column, String value) {
        if (Where.length() > 0)
            Where = " AND " + column + " > \"" + value + "\"";
        else
            Where = " where " + column + " > \"" + value + "\"";
    }

    public void whereLessThanOrEqualTo(String column, String value) {
        if (Where.length() > 0)
            Where = " AND " + column + " <= \"" + value + "\"";
        else
            Where = " where " + column + " <= \"" + value + "\"";
    }

    public void whereGreaterThanOrEqualTo(String column, String value) {
        if (Where.length() > 0)
            Where = " AND " + column + " >= \"" + value + "\"";
        else
            Where = " where " + column + " >= \"" + value + "\"";
    }

    public void whereContains(String column, String value) {
        if (Where.length() > 0)
            Where = " AND " + column + " LIKE\"%" + value + "%\"";
        else
            Where = " where " + column + " LIKE\"%" + value + "%\"";
    }

    public void whereStartsWith(String column, String value) {
        if (Where.length() > 0)
            Where = " AND " + column + " LIKE\"" + value + "%\"";
        else
            Where = " where " + column + " LIKE\"" + value + "%\"";
    }

    public void whereEndsWith(String column, String value) {
        if (Where.length() > 0)
            Where = " AND " + column + " LIKE\"%" + value + "\"";
        else
            Where = " where " + column + " LIKE\"%" + value + "\"";
    }

    /*public void whereExists(String column) {

    }

    public void whereDoesNotExist(String column) {

    }*/

    public void orderByAscending(String column) {
        orderBy = " order by " + column + "asc";
    }

    public void orderByDescending(String column) {
        orderBy = " order by " + column + "desc";
    }

    public void setLimit(int newLimit) {
        limit = " limit " + newLimit;
    }

    /*public void setSkip(int newskip) {

    }*/

    public int getSkip() {
        return 0;
    }

    public String getQuery() {
        return "select " + columns + " from " + Table_Name + Where + orderBy;
    }

    public void find(FindCallback findCallback) {

        try {
            Cursor cursor = Say.say.rawQuery(getQuery(), null);
            String[] colNames = cursor.getColumnNames();

            List<SayObject> list = new ArrayList<>();

            Log.i("count", cursor.getCount() + "");
            if (cursor.moveToFirst() && cursor != null) {
                do {
                    SayObject say = new SayObject();
                    for (int i = 0; i < colNames.length; i++) {
                        String columns = colNames[i];
                        String value = cursor.getString(cursor.getColumnIndex(columns));
                        say.putsay(columns, value);
                    }
                    list.add(say);

                } while (cursor.moveToNext());

                findCallback.done(list);
            }
        } catch (Exception e) {
            findCallback.error(e);
        }


    }

    /*public void findInBackground() {

    }*/

}
