package org.zygotekillers.hackaton.recycler_views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.zygotekillers.hackaton.R;
import org.zygotekillers.hackaton.models.Coach;

import java.util.List;

/**
 * Created by djamiirr on 03/12/17.
 */

public class CoachesRVAdapter extends RecyclerView.Adapter<CoachesRVAdapter.ViewHolder> {

    private List<Coach>teams;
    public CoachesRVAdapter(List<Coach>data){
        teams=data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_coaches,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Coach team=teams.get(position);
        holder.nomCoach.setText(team.getNom()+" "+team.getPrenom());
        holder.domaineCoach.setText(team.getDomaine());
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomCoach, domaineCoach,table;
        public ViewHolder(View itemView) {
            super(itemView);
            nomCoach =itemView.findViewById(R.id.nom_coach);
            domaineCoach =itemView.findViewById(R.id.domain_coach);

        }
    }
}
