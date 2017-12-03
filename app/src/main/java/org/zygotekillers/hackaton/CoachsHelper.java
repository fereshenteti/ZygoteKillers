package org.zygotekillers.hackaton;

import android.content.Context;

import org.zygotekillers.hackaton.models.Coach;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adnenhamdouni on 01/05/2016.
 */
public class CoachsHelper {

    private static volatile CoachsHelper mInstance = null;

    private ArrayList<Coach> coaches = null;

    private String username;

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    private boolean isLoaded;

    private Context mContext;

    public static CoachsHelper getInstance(Context context) {

        synchronized (CoachsHelper.class) {
            if (mInstance == null) {
                mInstance = new CoachsHelper(context);
            }
        }
        return mInstance;
    }

    private CoachsHelper(Context context) {
        mContext = context;
        coaches = new ArrayList<>();
    }

    public void setCoaches(List<Coach> coaches) {

        coaches.clear();
        if (coaches != null & coaches.size() > 0) {
            for (Coach coach : coaches) {
                coaches.add(coach);
            }
        }
    }

    public List<Coach> getCoachs() {
        List<Coach> _coaches = new ArrayList<>();
        if (coaches != null & coaches.size() > 0) {
            for (Coach coach : coaches) {
                _coaches.add(coach);
            }
        }
        return _coaches;
    }

    public String getCoach() {
        return username;
    }

    public void setCoach(String username) {
        this.username = username;
    }

}
