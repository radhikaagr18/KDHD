package com.parana.kdhd;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

public class AddRequest {

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef = database.getReference("requests");
    static DatabaseReference cities = database.getReference("cities");

    public static String addRequest(Request request){

        String reqId = myRef.push().getKey();
        myRef.child(reqId).setValue(request);
        cities.child("created").child(request.city).child(reqId).setValue(true);
        Log.i("msg: ",reqId);
        getRequests("created",request.city);
        return reqId;
    };

    public static List<Request> getRequests(String status,String district){
        List<Request> msgs = new ArrayList<>();
        cities.child(status).child(district).addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot s: children){
                    Log.i("msg",s.getKey());
                    myRef.child(s.getKey()).addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // Get Post object and use the values to update the UI
                                    Request post = dataSnapshot.getValue(Request.class);
                                    Log.i("user",post.username);
                                    msgs.add(post);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // Getting Post failed, log a message
                                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                                    // ...
                                }
                            });


                }
            };
            public void onCancelled(DatabaseError databaseError) { }
        });
        return msgs;
    }

/*    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // Get Post object and use the values to update the UI
            Post post = dataSnapshot.getValue(Post.class);
            // ...
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            // ...
        }
    };
database.get.addValueEventListener(postListener);*/

}
