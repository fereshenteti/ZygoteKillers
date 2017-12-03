package org.zygotekillers.hackaton;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.zygotekillers.hackaton.models.Team;

/**
 * Created by djamiirr on 03/12/17.
 */

public class TeamsViewHolder extends RecyclerView.ViewHolder {

        public TextView team_name;
        public TextView authorView;
        public ImageView starView;
        public TextView num_table;
        public TextView salle;

        public TeamsViewHolder(View itemView) {
            super(itemView);

            team_name = (TextView) itemView.findViewById(R.id.team_name);
            num_table = (TextView) itemView.findViewById(R.id.num_table);
            salle = (TextView) itemView.findViewById(R.id.salle);
        }

        public void bindToPost(Team team, View.OnClickListener starClickListener) {
            team_name.setText(team.getTeamName());
            //authorView.setText(post.author);
            num_table.setText(String.valueOf(team.getTable()));
            salle.setText(team.getSalle());

        }

}
