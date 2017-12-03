package org.zygotekillers.hackaton.activities;

import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import org.zygotekillers.hackaton.R;
import org.zygotekillers.hackaton.fragments.CoachAuthFragment;

public class ChooseScreenActivity extends AppCompatActivity {

    private RelativeLayout partLayout,coachLayout;
    private Animation up,down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_screen);
        getSupportActionBar().hide();

        //init views
        initViews();

        //creating animations


        //click listeners
        coachLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createOpenAnimation(true);
            }
        });

    }

    //start creating open animations
    private void createOpenAnimation(final boolean isCoach) {
        Point size=new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        up=new TranslateAnimation(0,0,0,-size.y/2);
        down=new TranslateAnimation(0,0,0,size.y/2);
        up.setDuration(700);
        down.setDuration(700);
        down.setFillAfter(true);
        up.setFillAfter(true);
        partLayout.startAnimation(up);
        coachLayout.startAnimation(down);
        up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.choose_layout).setVisibility(View.GONE);
                Fragment fragment=null;
                if (isCoach)
                    fragment=new CoachAuthFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).commit();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }



    //start init views
    private void initViews() {
        partLayout= (RelativeLayout) findViewById(R.id.participant_layout);
        coachLayout= (RelativeLayout) findViewById(R.id.coach_layout);
    }
}
