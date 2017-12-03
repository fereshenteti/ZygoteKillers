package org.zygotekillers.hackaton.recycler_views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.zygotekillers.hackaton.R;
import org.zygotekillers.hackaton.models.Team;

import java.util.List;

/**
 * Created by djamiirr on 03/12/17.
 */

public class ParticipantsRVAdapter extends RecyclerView.Adapter<ParticipantsRVAdapter.ViewHolder> {

    private List<Team>teams;
    public ParticipantsRVAdapter(List<Team>data){
        teams=data;
        Log.e("test","test");

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Team team=teams.get(position);
        holder.nom_team.setText(team.getTeamName());
        holder.table.setText(team.getTable());
        holder.salle.setText(team.getSalle());
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nom_team,salle,table;
        public ViewHolder(View itemView) {
            super(itemView);
            nom_team=itemView.findViewById(R.id.team_name);
            salle=itemView.findViewById(R.id.salle);
            table=itemView.findViewById(R.id.num_table);
        }
    }
}
