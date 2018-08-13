package com.work.hany.playinseoul.common.map;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.network.AreaTour;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;

public class MapDetailFragment extends DaggerFragment implements MapDetailContract.View {
    public static final String ARGUMENT_TOUR = "ARGUMENT_TOUR";
    public static final String BUNDLE_TOUR = "BUNDLE_TOUR";

    @Inject
    @Named("MapDetailFragment")
    AreaTour areaTour;

    @Inject
    public MapDetailFragment(){ }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";


    MapView mapView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, null, false);
        TextView addrTextView = rootView.findViewById(R.id.map_addr_text_view);

        addrTextView.setText(areaTour.getFullAddress());
        mapView = rootView.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                LatLng latLng = new LatLng(areaTour.getMapy(), areaTour.getMapx());

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f));
                googleMap.addMarker(new MarkerOptions().position(latLng));

                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.addMarker(new MarkerOptions().position(new LatLng(areaTour.getMapy(), areaTour.getMapx())).title("Marker"));
            }
        });

    }

    //    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
//        if (mapViewBundle == null) {
//            mapViewBundle = new Bundle();
//            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
//        }
//
//        mMapView.onSaveInstanceState(mapViewBundle);
//    }



    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }


    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
