package org.zygotekillers.hackaton.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.zygotekillers.hackaton.CoachsHelper;
import org.zygotekillers.hackaton.R;
import org.zygotekillers.hackaton.ScrollingActivity;
import org.zygotekillers.hackaton.models.Coach;
import org.zygotekillers.hackaton.models.Competition;
import org.zygotekillers.hackaton.models.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;


/**
 * Created by djamiirr on 03/12/17.
 */

public class CoachAuthFragment extends Fragment implements ValueEventListener,View.OnClickListener {


    private static final String TAG = "userCheck";
    public MaterialEditText cin;
    public AppCompatButton continuer;
    public TextView loading;

    //Firebase variables
    private DatabaseReference mRootRef;
    private DatabaseReference mProfRef;

    private ArrayList<Coach> coaches;
    private ArrayList<Team> teams;

    private FirebaseAuth mAuth;

    private String username;

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_coach_auth,null);


        coaches = new ArrayList<>();
        teams = new ArrayList<>();

        // 1
        initFirebase();

        // 2
        initView();

        return view;
    }


    // 1
    private void initFirebase() {

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously();
        mProfRef = mRootRef.child("competitions");
        mAuth = FirebaseAuth.getInstance();
        mRootRef.addValueEventListener(this);

    }

    // 2
    private void initView() {

        cin = (MaterialEditText) view.findViewById(R.id.cin);


        continuer = view.findViewById(R.id.continuer);
        continuer.setOnClickListener(this);

        continuer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSecondaryText));
        continuer.setEnabled(false);

        loading = (TextView) view.findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);
    }



    @Override
    public void onResume() {
        super.onResume();
        if(CoachsHelper.getInstance(getContext()).isLoaded()) {
            continuer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            continuer.setEnabled(true);
            loading.setVisibility(View.INVISIBLE);
        }else {
            initFirebase();
            initView();
        }
    }



    @Override
    public void onClick(View view) {
        //for (Coach c : CoachsHelper.getInstance(getContext()).getCoachs()){
            /*if (c.getUsername().equals(cin.getText().toString())){
                CoachsHelper.getInstance(getContext()).setCoach(c.getUsername());*/

                startActivity(new Intent(getActivity(), ScrollingActivity.class).putExtra("title",cin.getText().toString()));
            //}
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        coaches.clear();

        for (DataSnapshot keys : dataSnapshot.child("joint").getChildren()) {
            Coach c = new Coach();
            c.setUsername(keys.getKey());
            c.setComp(new Competition(keys.child("competition").getValue().toString()));
            coaches.add(c);
        }

        for (DataSnapshot keys : dataSnapshot.child("competitions").child("DevFest").child("teams").getChildren()) {
            Team t = new Team();
            t.setTeamName(keys.getKey());
            t.setTable(keys.child("table").getValue().toString());
            teams.add(t);
        }

        CoachsHelper.getInstance(getContext()).setCoaches(coaches);
        CoachsHelper.getInstance(getContext()).setTeams(teams);

        continuer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        continuer.setEnabled(true);
        loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
