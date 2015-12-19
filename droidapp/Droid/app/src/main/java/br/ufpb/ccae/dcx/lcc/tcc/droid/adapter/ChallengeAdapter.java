package br.ufpb.ccae.dcx.lcc.tcc.droid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.ccae.dcx.lcc.tcc.droid.R;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.util.RecyclerViewOnClickListener;


/**
 * Created by xavier on 10/17/15.
 */
public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder> {

    private List<Challenge> challenges;
    private LayoutInflater layoutInflater;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;


    public ChallengeAdapter(Context context) {
        challenges = new ArrayList<>();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public RecyclerViewOnClickListener getRecyclerViewOnClickListener() {
        return recyclerViewOnClickListener;
    }

    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener recyclerViewOnClickListener) {
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    @Override
    public ChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.challenge, parent, false);
        return new ChallengeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ChallengeViewHolder holder, int position) {

        holder.challenge.setText(challenges.get(position).getDescription());
        holder.level.setText(String.valueOf(challenges.get(position).getDifficulty()));

    }

    @Override
    public int getItemCount() {

        return challenges.size();

    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public class ChallengeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView challenge;
        public TextView level;

        public ChallengeViewHolder(View itemView) {

            super(itemView);

            challenge = (TextView) itemView.findViewById(R.id.challenge);
            level = (TextView) itemView.findViewById(R.id.difficulty);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(recyclerViewOnClickListener != null) {
                recyclerViewOnClickListener.onClickListener(v, getLayoutPosition());
            }
        }
    }





}