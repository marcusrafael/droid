package br.ufpb.ccae.dcx.lcc.tcc.droid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.concurrent.ExecutionException;

import br.ufpb.ccae.dcx.lcc.tcc.droid.R;
import br.ufpb.ccae.dcx.lcc.tcc.droid.activity.ChallengeActivity;
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapt.LocationAdaptation;
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapter.ChallengeAdapter;
import br.ufpb.ccae.dcx.lcc.tcc.droid.async.VerifyInternetConnection;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.persistence.LocalDatabaseFacade;
import br.ufpb.ccae.dcx.lcc.tcc.droid.util.DroidURL;
import br.ufpb.ccae.dcx.lcc.tcc.droid.util.RecyclerViewOnClickListener;


public class ChallengeFragment extends Fragment implements RecyclerViewOnClickListener {


    private List<Challenge> challenges;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerView  mRecyclerView;
    private LocationAdaptation locationAdaptation;
    private ChallengeAdapter mChallengeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        this.challenges = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_challenge, container, false);


        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        locationAdaptation = new LocationAdaptation(getActivity(), this);
        locationAdaptation.connect();

        VerifyInternetConnection verifyInternetConnection = new VerifyInternetConnection(getContext());
        try {
            Boolean isConnected = verifyInternetConnection.execute(DroidURL.DROID_API_URL).get();

            if( ! isConnected ) { // executes when connected to the server
                this.challenges = LocalDatabaseFacade.getInstance(getContext()).getAllChallenges();
                mChallengeAdapter = new ChallengeAdapter(getContext());
                mChallengeAdapter.setChallenges(challenges);
                mRecyclerView.setAdapter(mChallengeAdapter);
                mChallengeAdapter.setRecyclerViewOnClickListener(this);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClickListener(View view, int position) {

        Intent intent = new Intent(getActivity(), ChallengeActivity.class);
        intent.putExtra("CHALLENGE", challenges.get(position));
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return super.onOptionsItemSelected(item);

    }

    public void update() {
        this.updateList(this);
    }


    public void updateList(final RecyclerViewOnClickListener recyclerViewOnClickListener) {

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                Log.d("CONTEXTO:", response.toString());

                challenges.clear();

                for (int i = 0; i < response.length(); i++) {

                    try {

                        float[] distance = new float[2];


                        Challenge challenge = new Gson().fromJson(response.getJSONObject(i).toString(), Challenge.class);

                        android.location.Location.distanceBetween(locationAdaptation.getCurrentLocation().getLatitude(), locationAdaptation.getCurrentLocation().getLongitude(),
                                challenge.getLocation().getLatitude(), challenge.getLocation().getLongitude(), distance);

                        double radius = challenge.getLocation().getRadius();
                        Log.d("RADIUS:", String.valueOf(radius));

                        if(distance[0] < radius) { // is inside area

                            challenges.add(challenge);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                mChallengeAdapter = new ChallengeAdapter(getContext());
                mChallengeAdapter.setChallenges(challenges);
                mRecyclerView.setAdapter(mChallengeAdapter);
                mChallengeAdapter.setRecyclerViewOnClickListener(recyclerViewOnClickListener);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) { }

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