package br.ufpb.ccae.dcx.lcc.tcc.droid.adapt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import br.ufpb.ccae.dcx.lcc.tcc.droid.adapter.ChallengeAdapter;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.util.DroidURL;

/**
 * Created by xavier on 12/19/15.
 */
public class RemoteDatabaseConnection implements ConnectionAdaptation {


    private RequestQueue queue;
    private RecyclerView recyclerView;
    private ChallengeAdapter adapter;
    private List<Challenge> challenges;

    public RemoteDatabaseConnection(Context context, RecyclerView recyclerView) {

        this.queue = Volley.newRequestQueue(context);
        this.recyclerView = recyclerView;
        this.adapter = (ChallengeAdapter) recyclerView.getAdapter();
        this.challenges = adapter.getChallenges();

    }

    @Override
    public void update() {

        this.challenges.clear();

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

                adapter.setChallenges(challenges);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }

        };

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                DroidURL.DROID_API_CHALLENGES_URL,
                responseListener,
                errorListener);

        queue.add(jsonObjectRequest);
        queue.start();

    }
}
