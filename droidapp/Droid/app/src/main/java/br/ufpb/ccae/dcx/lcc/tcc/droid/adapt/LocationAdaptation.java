package br.ufpb.ccae.dcx.lcc.tcc.droid.adapt;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.ccae.dcx.lcc.tcc.droid.fragment.ChallengeFragment;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Location;
import br.ufpb.ccae.dcx.lcc.tcc.droid.persistence.LocalDatabaseFacade;

/**
 * Created by xavier on 11/4/15.
 */
public class LocationAdaptation implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static List<Challenge> challenges = new ArrayList<>();

    private android.location.Location currentLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Context context;
    private ChallengeFragment challengeFragment;

    public LocationAdaptation(Context context, ChallengeFragment clazz) {
        this.context = context;
        this.challengeFragment = clazz;
    }

    public synchronized void connect() {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private void initLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
    }

    private void startLocationUpdate() {
        initLocationRequest();
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,
                LocationAdaptation.this);
    }

    private void stopLocationUpdate() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, LocationAdaptation.this);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("CONTEXTO:", "onConnected()");
        startLocationUpdate();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("CONTEXTO:", "onConnectionSuspended()");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("CONTEXTO:", "onConnectionFailed()");
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        Log.d("CONTEXTO:", "onLocationChanged()");
        Log.d("LAT:", String.valueOf(location.getLatitude()));
        Log.d("LON:", String.valueOf(location.getLongitude()));
        updateLocations(location);
    }

    public void updateLocations(android.location.Location location) {

        this.currentLocation = location;

        challenges.clear();

        LocalDatabaseFacade localDatabaseFacade = LocalDatabaseFacade.getInstance(context);
        List<Location> databaseLocations = localDatabaseFacade.getAllLocations();


        float[] distance = new float[2];
        for(Location loc : databaseLocations) {

            android.location.Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                    loc.getLatitude(), loc.getLongitude(), distance);

            if(distance[0] < loc.getRadius()) { // is inside area

                List<Challenge> cs = localDatabaseFacade.getChallengesByLatLong(loc.getLatitude(), loc.getLongitude());
                challenges.addAll(cs);

            }
        }

        challengeFragment.update();
    }

    public android.location.Location getCurrentLocation() {
        return currentLocation;
    }
}
