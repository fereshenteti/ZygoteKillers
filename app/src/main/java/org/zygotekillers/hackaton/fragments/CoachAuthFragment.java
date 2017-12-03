package org.zygotekillers.hackaton.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.zygotekillers.hackaton.CoachsHelper;
import org.zygotekillers.hackaton.R;
import org.zygotekillers.hackaton.models.Coach;
import org.zygotekillers.hackaton.models.Competition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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

    private FirebaseAuth mAuth;

    private boolean isMawjoud = false;
    private boolean isFergha = false;
    private boolean hasChanged = false;
    //private boolean completed = false;

    private String username;

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_coach_auth,null);


        coaches = new ArrayList<>();

        // 1
        initFirebase();

        // 2
        initView();

        return view;
    }


    // 1
    private void initFirebase() {

        mRootRef = FirebaseDatabase.getInstance().getReference();
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


    // 4
    private void connectCoach(String _username) {

        if(CoachsHelper.getInstance(getContext()).getCoachs() != null
                && !CoachsHelper.getInstance(getContext()).getCoachs().isEmpty()) {

            for (Coach coach : CoachsHelper.getInstance(getContext()).getCoachs()){

                if(coach!=null) {

                    if (coach.getUsername().equals(_username)) {
                        isMawjoud = true;
                        CoachsHelper.getInstance(getContext()).setCoach(_username);
                        hasChanged = true;
                        Log.w(TAG, "MainActivity:mawjoud");
                        break;
                    } else {
                        isMawjoud = false;
                        hasChanged = true;
                        Log.w(TAG, "MainActivity:mouch mawjoud");
                    }
                }
            }
            isFergha = false;
        }else if(CoachsHelper.getInstance(getContext()).getCoachs() != null){
            isFergha = true;
            Toast.makeText(getContext(), "Vous n'êtes pas enregistrés comme coach ! Veuillez vous enregistrer auprès de notre plateforme d'abord.", Toast.LENGTH_SHORT).show();
        }else if(CoachsHelper.getInstance(getContext()).getCoachs() == null) {
            Toast.makeText(getContext(), "Veuillez attendre le chargement svp", Toast.LENGTH_SHORT).show();
        }

        Log.w(TAG, "MainActivity: isMawjoud = "+isMawjoud);
        Log.w(TAG, "MainActivity: isFergha = "+isFergha);


        /*
        if((hasChanged == true || isFergha == true) && isMawjoud == false){


            coaches.add(_username);
            CoachsHelper.getInstance(getContext()).setCoach(_username);

            //Map<String, Object> postValues = ens.toMap();

            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/joint/"+_username, competition);

            mRootRef.updateChildren(childUpdates);

            hasChanged = false;
            */



        //startActivity(new Intent(getActivity(), HomeActivity.class));
        continuer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSecondaryText));
        continuer.setEnabled(false);
        loading.setVisibility(View.VISIBLE);


    }


    @Override
    public void onClick(View view) {
        for (Coach c : CoachsHelper.getInstance(getContext()).getCoachs()){
            if (c.getUsername().equals(cin.getText().toString())){
                //Toast.makeText(getContext(), c.getComp().getCompName(), Toast.LENGTH_SHORT).show();
            }
        }

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

        CoachsHelper.getInstance(getContext()).setCoaches(coaches);
        Log.w(TAG, "MainActivity: liste enseignants : " + CoachsHelper.getInstance(getContext()).getCoachs().size());

        continuer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        continuer.setEnabled(true);
        loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
