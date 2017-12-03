package org.zygotekillers.hackaton;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.zygotekillers.hackaton.models.Coach;
import org.zygotekillers.hackaton.models.Team;
import org.zygotekillers.hackaton.recycler_views.ParticipantsRVAdapter;

import java.util.ArrayList;
import java.util.List;


public class ScrollingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        setTitle("Welcome "+ getIntent().getStringExtra("title"));

        final List<Team> teams=new ArrayList<>();//=CoachsHelper.getInstance(this).getTeams();

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.messages_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ParticipantsRVAdapter adapter=new ParticipantsRVAdapter(teams);
        recyclerView.setAdapter(adapter);

        new AsyncTask<Void,Void,Void>(){


            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                teams.add(new Team("Zygote Killers", "S2", "3"));
                teams.add(new Team("Hanzo", "S2", "2"));
                teams.add(new Team("for the horde", "S2", "5"));
                teams.add(new Team("ProDevs", "S1", "4"));
                teams.add(new Team("Skills Builders", "S1", "1"));
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }.execute();


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
