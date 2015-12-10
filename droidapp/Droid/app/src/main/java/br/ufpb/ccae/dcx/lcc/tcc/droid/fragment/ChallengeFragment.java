package br.ufpb.ccae.dcx.lcc.tcc.droid.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapter.ChallengeAdapter;
import br.ufpb.ccae.dcx.lcc.tcc.droid.async.Updatable;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.ConnectionManager;
import br.ufpb.ccae.dcx.lcc.tcc.droid.util.RecyclerViewOnClickListener;


public class ChallengeFragment extends Fragment implements RecyclerViewOnClickListener, Updatable {


    private List<Challenge> challenges = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;
    private RecyclerView  mRecyclerView;
    private ChallengeAdapter mChallengeAdapter;
    private ConnectionManager connectionManager;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.fragment_challenge, container, false);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        update();

        connectionManager = ConnectionManager.getInstance(getActivity(), this);
        connectionManager.updateChallenges();

        return view;

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

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    @Override
    public void update() {

        mChallengeAdapter = new ChallengeAdapter(getActivity());
        mChallengeAdapter.setChallenges(challenges);
        mRecyclerView.setAdapter(mChallengeAdapter);
        mChallengeAdapter.setRecyclerViewOnClickListener(this);

    }


}