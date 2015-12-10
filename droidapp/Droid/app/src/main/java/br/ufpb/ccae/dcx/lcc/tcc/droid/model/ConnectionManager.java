package br.ufpb.ccae.dcx.lcc.tcc.droid.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.ufpb.ccae.dcx.lcc.tcc.droid.async.VerifyInternetConnection;
import br.ufpb.ccae.dcx.lcc.tcc.droid.fragment.ChallengeFragment;
import br.ufpb.ccae.dcx.lcc.tcc.droid.persistence.LocalDatabaseFacade;
import br.ufpb.ccae.dcx.lcc.tcc.droid.persistence.RemoteDatabaseFacade;
import br.ufpb.ccae.dcx.lcc.tcc.droid.util.DroidURL;

/**
 * Created by xavier on 12/2/15.
 */
public class ConnectionManager {

    private Context context;
    private static ConnectionManager instance;

    private ChallengeFragment fragment;

    private ConnectionManager(Context context, ChallengeFragment fragment) {
        this.context = context;
        this.fragment = fragment;

    }

    public static ConnectionManager getInstance(Context context, ChallengeFragment fragment) {

        if(instance == null) {
            instance = new ConnectionManager(context, fragment);
        }
        return instance;

    }

    public List<Challenge> getChallenges() {

        VerifyInternetConnection verifyInternetConnection = new VerifyInternetConnection(context);

        try {

            Boolean isConnected = verifyInternetConnection.execute(DroidURL.DROID_API_URL).get();

            if(isConnected) { // executes when connected to the server

                return RemoteDatabaseFacade.getInstance(context).getAllChallenges();

            } else {

                return LocalDatabaseFacade.getInstance(context).getAllChallenges();

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    public void updateChallenges() {

        List<Challenge> challenges = getChallenges();
        fragment.getChallenges().clear();
        fragment.setChallenges(challenges);
        fragment.update();

    }

}
