package br.ufpb.ccae.dcx.lcc.tcc.droid.adapt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapter.ChallengeAdapter;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;

/**
 * Created by xavier on 12/19/15.
 */
public class LocalDatabaseConnection implements ConnectionAdaptation {

    private Context context;
    private RecyclerView recyclerView;

    public LocalDatabaseConnection(Context context, RecyclerView recyclerView) {

        this.context = context;
        this.recyclerView = recyclerView;

    }


    @Override
    public void update() {

        LocationAdaptation locationAdaptation = new LocationAdaptation(context, recyclerView);
        locationAdaptation.connect();

    }

}
