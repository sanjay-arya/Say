package sanjay.arya.say;

import java.util.List;

/**
 * Created by sanjay on 11/23/15.
 */
public interface FindCallback {
    void done(List<SayObject> list);

    void error(Exception e);
}
