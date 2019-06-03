package com.app.shaalastic.Users;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.shaalastic.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Users.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Users#newInstance} factory method to
 * create an instance of this fragment.
 */


public class Users extends Fragment{

    /*
    TODO: Rename parameter arguments, choose names that match
    the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    */
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /* TODO: Rename and change types of parameters */
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ArrayList<UserData> data=new ArrayList<>();
    public Users() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Assignments.
     */
    // TODO: Rename and change types and number of parameters
    public static Users newInstance(String param1, String param2) {
        Users fragment = new Users();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("StaticFieldLeak")
    public class Downloadtask extends AsyncTask<String,Void,JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            final String Token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2RlbW8uZS1kdWNhdGUuaW4vYXBpL3VzZXIvbG9naW4iLCJpYXQiOjE1NTk1ODkyOTAsImV4cCI6MTU1OTU5Mjg5MCwibmJmIjoxNTU5NTg5MjkwLCJqdGkiOiJ1S2xZNjdOcUd4NUQwV1lZIiwic3ViIjoxLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.1WIAyOkH60X_Z7g2NZeRBYool-4cxhxcdR5S6IP3JN8";
            URL url;
            try {
                url= new URL(strings[0]);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url.toString(),null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                }) {
                    public Map<String, String> getHeaders() {

                        HashMap<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization", "Bearer "+Token);
                        return headers;

                    }
                };



                RequestQueue mQueue;
                mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                mQueue.add(request);





            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Downloadtask task=new Downloadtask();
        task.execute("https://demo.e-ducate.in/api/users");

    }


    /*public void jsonParse() {
        final String Token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2RlbW8uZS1kdWNhdGUuaW4vYXBpL3VzZXIvbG9naW4iLCJpYXQiOjE1NTk1NDg3NTcsImV4cCI6MTU1OTU1MjM1NywibmJmIjoxNTU5NTQ4NzU3LCJqdGkiOiJIdXdLRTdDUGtva3Z0anhNIiwic3ViIjoxLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.JlgbzA8LkYhFE712F58WZXyvYXNR-t30rHRFo7GWcu4";
        String url = "https://demo.e-ducate.in/api/users";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("users");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject Users = jsonArray.getJSONObject(i);

                                String Name = Users.optString("name");
                                String email = Users.optString("email");
                                String phno = Users.optString("phone_number");

                                String gender = Users.optString("gender");

                                String uid = Users.optString("uid_no");

                                String role = Users.optString("role_id");

                                data.add(new UserData(Name, email, role, phno, gender, uid));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        }) {
            public Map<String, String> getHeaders() {

                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer "+Token);
                return headers;

            }
        };



        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        mQueue.add(request);
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user, container, false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.userRecycler);

        recyclerView.setAdapter(new UserAdapter(getActivity(),data));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
