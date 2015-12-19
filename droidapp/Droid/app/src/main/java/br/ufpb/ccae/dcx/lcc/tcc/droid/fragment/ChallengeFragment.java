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
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapt.ConnectionAdaptation;
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapt.LocalDatabaseConnection;
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapt.LocationAdaptation;
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapt.RemoteDatabaseConnection;
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapter.ChallengeAdapter;
import br.ufpb.ccae.dcx.lcc.tcc.droid.async.VerifyInternetConnection;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.util.DroidURL;
import br.ufpb.ccae.dcx.lcc.tcc.droid.util.RecyclerViewOnClickListener;


public class ChallengeFragment extends Fragment implements RecyclerViewOnClickListener {


    private List<Challenge> challenges = new ArrayList<>();

    public LinearLayoutManager linearLayoutManager;
    public RecyclerView  mRecyclerView;
    public ChallengeAdapter mChallengeAdapter;

    private ConnectionAdaptation connectionAdaptation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_challenge, container, false);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mChallengeAdapter = new ChallengeAdapter(getContext());
        mChallengeAdapter.setChallenges(challenges);
        mRecyclerView.setAdapter(mChallengeAdapter);
        mChallengeAdapter.setRecyclerViewOnClickListener(this);

        return view;

    }


    public void updateRecyclerView() {

        VerifyInternetConnection verifyInternetConnection = new VerifyInternetConnection(getContext());

        try {

            Boolean isConnected = verifyInternetConnection.execute(DroidURL.DROID_API_URL).get();

            if( isConnected ) { // executes when connected to the server

                connectionAdaptation = new RemoteDatabaseConnection(getActivity(), mRecyclerView);
                connectionAdaptation.update();

            } else {

                connectionAdaptation = new LocalDatabaseConnection(getActivity(), mRecyclerView);
                connectionAdaptation.update();

            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        this.updateRecyclerView();
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

}