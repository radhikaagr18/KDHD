package com.parana.kdhd;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FacebookFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FacebookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FacebookFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ImageView postImage;
    TextView postText;

    String FACEBOOKURL;

    RequestQueue queue;

    JSONObject response;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FacebookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FacebookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FacebookFragment newInstance(String param1, String param2) {
        FacebookFragment fragment = new FacebookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinner = getView().findViewById(R.id.districts_spinner);
        spinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.districts_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        final String tag = "#trivandrum";

        postImage = getView().findViewById(R.id.post_image);
        postText = getView().findViewById(R.id.post_text);

        FACEBOOKURL="https://graph.facebook.com/PranaElectronicsIndia/feed?"
                +"fields=id,story,created_time,message,full_picture&access_token=EAADquiqZCjt4BAAbcdNEsgGsrYVs8DpRupG15LTP2FA65QhdMciTENEFegumZA2KwvAHqZBfmwRqEjqa1Usz6gkJMSwxNBGW9r5zYAo3im3bGZCL5E2LQN2XJjFtJkHApvPZAF64cDbywH2p6Rrwke6kkYiOEiALUijkFZCqzZCdcBOhXLHF5K3lIVxOXZCeb3LZC5nTk8hlpUwZDZD&limit=15";

        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(FACEBOOKURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (null != response) {
                            //handle your response
                            viewPost(response,postImage,postText,tag);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);

        Glide.with(this).load("https://scontent.fcok1-1.fna.fbcdn.net/v/t1.0-9/p960x960/92863154_3055720331152351_2211893640450539520_o.jpg?_nc_cat=111&_nc_sid=8024bb&_nc_oc=AQkZdTdv47i-i-PqDobRuxcNDV6j831mk778URpQ1zloxIVSHzt4pIE32ukl0W9tksJEBN_LMqIDEanqn_yIeepf&_nc_ht=scontent.fcok1-1.fna&_nc_tp=6&oh=7924ca9114ff9c4ccf7879ab1fe2ceec&oe=5EB8DF08").into(postImage);
        postText.setText("ലോക് ഡൗണ്\u200D നിലനില്\u200Dക്കുമ്പോള്\u200D അവശ്യ മരുന്നുകള്\u200D ലഭിക്കാന്\u200D മലപ്പുറം ജില്ലയില്\u200D ഇനി പ്രയാസമുണ്ടാവില്ല. രോഗികള്\u200Dക്ക് വേണ്ട മരുന്നുകള്\u200D വീടുകളിലെത്തിച്ചു നല്\u200Dകുന്ന 'സഞ്ജീവനി' പദ്ധതിക്ക് തുടക്കമായതായി ജില്ലാ കലക്ടര്\u200D ജാഫര്\u200D മലിക് അറിയിച്ചു. രോഗികളുടേയും വീട്ടിലുള്ളവരുടേയും പൊതു സമ്പര്\u200Dക്കം ഇല്ലാതാക്കുക എന്ന ലക്ഷ്യത്തോടെയാണ് പുതിയ സംവിധാനം ആരംഭിച്ചിരിക്കുന്നത്.");
    }

    private void viewPost(JSONObject response, ImageView postImage, TextView postText,String tag){
        try {
            JSONArray data = response.getJSONArray("data");
            for (int j = 0;j < data.length();j++){
                JSONObject tmp = data.getJSONObject(j);
                try {
                    String msg = tmp.getString("message");
                    if(msg.contains(tag)){
                        String image_url = tmp.getString("full_picture");
                        Glide.with(this).load(image_url).into(postImage);
                        postText.setText(msg);
                    }
                }catch (JSONException e){
                    continue;
                };

            }
        }
        catch (JSONException e){
            Log.i("error: ",e.toString());
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facebook, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String tag0 = parent.getItemAtPosition(position).toString();
        final String tag = "#" + tag0.toLowerCase();
        Log.i("tag: ",tag);
        //viewPost(response,postImage,postText,tag);
        JsonObjectRequest request = new JsonObjectRequest(FACEBOOKURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (null != response) {
                            //handle your response
                            viewPost(response,postImage,postText,tag);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        viewPost(response,postImage,postText,"#kasaragod");

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
