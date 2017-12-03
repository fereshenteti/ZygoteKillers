package org.zygotekillers.hackaton;

import android.content.Context;

import org.zygotekillers.hackaton.models.Coach;
import org.zygotekillers.hackaton.models.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adnenhamdouni on 01/05/2016.
 */
public class CoachsHelper {

    private static volatile CoachsHelper mInstance = null;

    private ArrayList<Coach> coaches = null;
    private ArrayList<Team> teams = null;

    private String username, teamName;

    public boolean isLoaded() {
        return isLoaded;
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
        teams = new ArrayList<>();
    }

    public void setCoaches(List<Coach> _coaches) {

        coaches.clear();
        if (_coaches != null & _coaches.size() > 0) {
            for (Coach coach : _coaches) {
                coaches.add(coach);
            }
        }
    }

    public void setTeams(List<Team> _team) {

        coaches.clear();
        if (_team != null & _team.size() > 0) {
            for (Team team : _team) {
                teams.add(team);
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


    public List<Team> getTeams() {
        List<Team> _teams = new ArrayList<>();
        if (teams != null & teams.size() > 0) {
            for (Team team : teams) {
                _teams.add(team);
            }
        }
        return _teams;
    }

    public String getCoach() {
        return username;
    }
    public String getTeam() {
        return teamName;
    }

    public void setCoach(String username) {
        this.username = username;
    }
    public void setTeam(String teamName) {
        this.teamName = teamName;
    }

}
