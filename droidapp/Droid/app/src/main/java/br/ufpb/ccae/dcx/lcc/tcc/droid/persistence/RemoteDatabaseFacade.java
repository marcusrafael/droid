package br.ufpb.ccae.dcx.lcc.tcc.droid.persistence;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Location;
import br.ufpb.ccae.dcx.lcc.tcc.droid.util.DroidURL;

/**
 * Created by xavier on 11/23/15.
 */
public class RemoteDatabaseFacade {


    private static final String TAG = "REMOTE_FACADE";

    private static RemoteDatabaseFacade instance;

    private Context context;
    private List<Challenge> challenges;
    private List<Location> locations;


    private RemoteDatabaseFacade(Context context) {
        this.context = context;
        this.challenges = new ArrayList<>();
        this.locations = new ArrayList<>();
    }

    public static RemoteDatabaseFacade getInstance(Context context) {

        if(instance == null) {
            instance = new RemoteDatabaseFacade(context);
        }
        return instance;

    }


    public List<Challenge> getAllChallenges() {

        challenges.clear();

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {

                        Challenge challenge = new Gson().fromJson(response.getJSONObject(i).toString(), Challenge.class);
                        challenges.add(challenge);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG, error.toString());
                Toast.makeText(context, "ERRO AO CONNECTAR AO SERVIDOR", Toast.LENGTH_SHORT).show();

            }

        };

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                DroidURL.DROID_API_CHALLENGES_URL,
                responseListener,
                errorListener);

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonObjectRequest);
        queue.start();

        return challenges;

    }

    public List<Location> getAllLocations() {

        locations.clear();

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            for (int i = 0; i < response.length(); i++) {

                try {

                    Location location = new Gson().fromJson(response.getJSONObject(i).toString(), Location.class);
                    locations.add(location);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG, error.toString());
                Toast.makeText(context, "ERRO AO CONNECTAR AO SERVIDOR", Toast.LENGTH_SHORT).show();

            }

        };

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                DroidURL.DROID_API_LOCATIONS_URL,
                responseListener,
                errorListener);

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonObjectRequest);
        queue.start();

        return locations;

    }
}


