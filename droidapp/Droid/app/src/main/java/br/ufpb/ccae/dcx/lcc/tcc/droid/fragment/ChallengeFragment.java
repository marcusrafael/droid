package br.ufpb.ccae.dcx.lcc.tcc.droid.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.ccae.dcx.lcc.tcc.droid.R;
import br.ufpb.ccae.dcx.lcc.tcc.droid.activity.ChallengeActivity;
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapt.LocationAdaptation;
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapter.ChallengeAdapter;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Location;
import br.ufpb.ccae.dcx.lcc.tcc.droid.persistence.DatabaseFacade;
import br.ufpb.ccae.dcx.lcc.tcc.droid.util.RecyclerViewOnClickListener;


public class ChallengeFragment extends Fragment implements RecyclerViewOnClickListener {

    private RecyclerView mRecyclerView;
    private ChallengeAdapter mChallengeAdapter;
    private LocationAdaptation locationAdaptation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_challenge, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mChallengeAdapter = new ChallengeAdapter(getActivity(), LocationAdaptation.challenges);
        mRecyclerView.setAdapter(mChallengeAdapter);
        mChallengeAdapter.setRecyclerViewOnClickListener(this);

        locationAdaptation = new LocationAdaptation(getActivity());
        locationAdaptation.connect();
        
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClickListener(View view, int position) {

        Intent intent = new Intent(getActivity(), ChallengeActivity.class);
        intent.putExtra("CHALLENGE", LocationAdaptation.challenges.get(position));
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return super.onOptionsItemSelected(item);

    }

}