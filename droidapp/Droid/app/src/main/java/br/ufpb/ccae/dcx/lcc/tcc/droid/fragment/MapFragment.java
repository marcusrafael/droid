package br.ufpb.ccae.dcx.lcc.tcc.droid.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.ufpb.ccae.dcx.lcc.tcc.droid.R;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Location;
import br.ufpb.ccae.dcx.lcc.tcc.droid.persistence.DatabaseFacade;


public class MapFragment extends Fragment {

    private SupportMapFragment fragment;
    private GoogleMap map;
    private List<Location> locations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DatabaseFacade databaseFacade = DatabaseFacade.getInstance(getActivity());
        locations = databaseFacade.getAllLocations();
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, fragment).commit();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (map == null) {
            map = fragment.getMap();
            map.setMyLocationEnabled(true);
        }
        loadMarkers();
    }

    private void loadMarkers() {



        for (Location location : locations) {

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            double radius = location.getRadius();
            LatLng latLng = new LatLng(latitude, longitude);

            map.addMarker(new MarkerOptions().position(latLng).visible(false));
            CircleOptions area = new CircleOptions()
                    .center(latLng)
                    .radius(radius)
                    .fillColor(Color.argb(100, 255, 0, 0))
                    .strokeColor(Color.argb(100, 255, 0, 0));
            map.addCircle(area);

        }

    }

}
