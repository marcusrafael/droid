package br.ufpb.ccae.dcx.lcc.tcc.droid.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by xavier on 11/24/15.
 */
public class VerifyInternetConnection extends AsyncTask<String, String, Boolean> {

    private Context context;
    private ProgressDialog progressDialog;


    public VerifyInternetConnection(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Connecting to the server...");
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            return (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    protected void onProgressUpdate(String... values) {
        progressDialog.dismiss();
        progressDialog.setMessage("Verifying internet connection...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        progressDialog.dismiss();
        if (result) {
            progressDialog.setMessage("The device is connected to the server!");
        } else {
            progressDialog.setMessage("Unable to connectToGoogleApiClient the server!");
        }
        progressDialog.show();
        progressDialog.dismiss();

    }
}
