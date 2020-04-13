package com.parana.kdhd;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements FacebookFragment.OnFragmentInteractionListener, LiveRequestsFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button reqSec = findViewById(R.id.req_sec);
        Button pendingSec = findViewById(R.id.pending_sec);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container_view) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            FacebookFragment fbFragment = new FacebookFragment();


            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            //firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_view, fbFragment).commit();
        }

        reqSec.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                SendReqFragment sendReqFragment = new SendReqFragment();
                // Code here executes on main thread after user presses button
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, sendReqFragment).addToBackStack(null).commit();
            }
        });

        pendingSec.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                LiveRequestsFragment liveRequestsFragment = new LiveRequestsFragment();
                // Code here executes on main thread after user presses button
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, liveRequestsFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {


    }

    @Override
    public void onListFragmentInteraction(Request item) {

    }
}
