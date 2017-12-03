package org.zygotekillers.hackaton.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.zygotekillers.hackaton.R;
import org.zygotekillers.hackaton.models.Coach;
import org.zygotekillers.hackaton.recycler_views.CoachesRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djamiirr on 03/12/17.
 */

public class ListCoachs extends Fragment {
    private View v;
    private TextView name,domaine;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_scrolling,container,false);
        name=v.findViewById(R.id.nom_coach);
        domaine=v.findViewById(R.id.domain_coach);

        final List<Coach>coaches=new ArrayList<>();

        getActivity().setTitle("Liste Des Coaches");

        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        RecyclerView recyclerView=v.findViewById(R.id.messages_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final CoachesRVAdapter adapter=new CoachesRVAdapter(coaches);
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
                coaches.add(new Coach("Slim ","Git/Github","Ben Hammouda","Slim"));
                coaches.add(new Coach("FeresH ","Firebase","Henteti","Feres"));
                coaches.add(new Coach("Djamiirr ","Android/Linux","Elleuch","Amir"));
                coaches.add(new Coach("Narcisse ","Web","Gagoba","Narcisse"));
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }.execute();

        return v;
    }
}
