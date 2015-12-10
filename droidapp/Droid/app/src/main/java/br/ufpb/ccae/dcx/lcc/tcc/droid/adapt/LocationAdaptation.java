package br.ufpb.ccae.dcx.lcc.tcc.droid.adapt;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by xavier on 11/4/15.
 */
public class LocationAdaptation implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    private static final long FIVE_MINUTES = 60000;
    private static final String TAG = "DROID";


    private Context context;
    private android.location.Location currentLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;


    public LocationAdaptation(Context context) {
        this.context = context;
    }

    public synchronized void connectToGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private void startRequestForLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(FIVE_MINUTES);
        mLocationRequest.setFastestInterval(FIVE_MINUTES);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
    }

    private void startGettingLocationUpdates() {
        startRequestForLocationUpdates();
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,
                LocationAdaptation.this);
    }

    private void stopGettingLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, LocationAdaptation.this);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected()");
        startGettingLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended()");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed()");
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        Log.d(TAG, "onLocationChanged()");
        Log.d("LAT:", String.valueOf(location.getLatitude()));
        Log.d("LON:", String.valueOf(location.getLongitude()));
    }

}
