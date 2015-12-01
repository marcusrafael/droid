package br.ufpb.ccae.dcx.lcc.tcc.droid.adapt;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.ccae.dcx.lcc.tcc.droid.adapter.ChallengeAdapter;
import br.ufpb.ccae.dcx.lcc.tcc.droid.async.Asyncable;
import br.ufpb.ccae.dcx.lcc.tcc.droid.async.VerifyInternetConnection;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.persistence.LocalDatabaseFacade;
import br.ufpb.ccae.dcx.lcc.tcc.droid.persistence.RemoteDatabaseFacade;

/**
 * Created by xavier on 11/22/15.
 */
public class NetworkAdaptation {

    private static NetworkAdaptation instance;
    private Context context;
    public static List<Challenge> challenges = new ArrayList<>();

    private NetworkAdaptation(Context context) {
        this.context = context;

    }

    public static NetworkAdaptation getInstance(Context context) {

        if(instance == null) {
            instance = new NetworkAdaptation(context);
        }
        return instance;

    }
}
