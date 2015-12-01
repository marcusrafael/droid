package br.ufpb.ccae.dcx.lcc.tcc.droid.persistence;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import br.ufpb.ccae.dcx.lcc.tcc.droid.adapt.NetworkAdaptation;
import br.ufpb.ccae.dcx.lcc.tcc.droid.async.Asyncable;
import br.ufpb.ccae.dcx.lcc.tcc.droid.async.VerifyInternetConnection;
import br.ufpb.ccae.dcx.lcc.tcc.droid.fragment.ChallengeFragment;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Location;
import br.ufpb.ccae.dcx.lcc.tcc.droid.util.DroidURL;

/**
 * Created by xavier on 11/23/15.
 */
public class RemoteDatabaseFacade {

    private static RemoteDatabaseFacade instance;

    private String URL = "http://52.34.93.123:8080/droidapi/";
    private Context context;

    private RemoteDatabaseFacade(Context context) {
        this.context = context;
//        VerifyInternetConnection verifyInternetConnection = new VerifyInternetConnection(this.context, this);
//        verifyInternetConnection.execute("http://52.34.93.123:8080/droidapi/");
    }


    public static RemoteDatabaseFacade getInstance(Context context) {

        if(instance == null) {
            instance = new RemoteDatabaseFacade(context);
        }
        return instance;
    }



    public List<Challenge> getAllChallenges() {

        NetworkAdaptation.challenges.clear();

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {

                        Challenge challenge = new Gson().fromJson(response.getJSONObject(i).toString(), Challenge.class);
                        NetworkAdaptation.challenges.add(challenge);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) { }

        };

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL + "challenges",
                responseListener,
                errorListener);

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonObjectRequest);
        queue.start();

        return NetworkAdaptation.challenges;

    }

    public List<Location> getAllLocations() {

        final List<Location> locations = new ArrayList<>();

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
            public void onErrorResponse(VolleyError error) { }

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


