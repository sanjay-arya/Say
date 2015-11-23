package sanjay.arya.say;

import java.util.HashMap;

/**
 * Created by sanjay on 11/23/15.
 */
public class SayObject {

    HashMap say = new HashMap();

    void putsay(String column, String value) {
        say.put(column, value);
    }

    public String getString(String column) {
        return say.get(column).toString();
    }

    public int getInt(String column) {
        return Integer.parseInt(say.get(column).toString());
    }

    public float getfloat(String column) {
        return Float.parseFloat(say.get(column).toString());
    }

    public long getlong(String column) {
        return Long.parseLong(say.get(column).toString());
    }

    public Double getDouble(String column) {
        return Double.parseDouble(say.get(column).toString());
    }

    public Boolean getBoolean(String column) {
        return Boolean.parseBoolean(say.get(column).toString());
    }

}
