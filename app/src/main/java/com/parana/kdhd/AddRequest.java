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
    static List<Request> tmp = new ArrayList<>();

    public static String addRequest(String username,String age,String district,String phoneNo,String requestTxt,String latitude,String longitude){

        String reqId = myRef.push().getKey();
        Request request = new Request(reqId,username,age,district,phoneNo,requestTxt,latitude,longitude);
        myRef.child(reqId).setValue(request);
        cities.child("created").child(request.city).child(reqId).setValue(true);
        Log.i("msg: ",reqId);
        return reqId;
    };

    public  List<Request> getRequests(String status,String district){
        Request val = new Request();
        //tmp.clear();
        cities.child(status).child(district).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                tmp.clear();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot s: children){
                    Log.i("msg",s.getKey());
                    myRef.child(s.getKey()).addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // Get Post object and use the values to update the UI
                                    Request val = new Request();
                                    Request post = dataSnapshot.getValue(Request.class);
                                    val.log = post.log;
                                    val.lat = post.lat;
                                    val.age = post.age;
                                    val.city = post.city;
                                    val.details = post.details;
                                    val.phNo = post.phNo;
                                    val.postedTime = post.postedTime;
                                    val.username = post.username;
                                    val.id = post.id;
                                    Log.i("post:",val.toString());
                                    tmp.add(val);
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
        return tmp;
    }

    public void removeData(String district,String status, String id){
        cities.child(status).child(district).child(id).removeValue();
        myRef.child(id).removeValue();
    }

    public void approveRequest(String id, String district){
        cities.child("approved").child(district).child(id).setValue(true);
        cities.child("created").child(district).child(id).removeValue();
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
