package com.vnapnic.myvib.fragments;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.adapter.PopupAdapter;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.customs.FontTextView;
import com.vnapnic.myvib.model.ATMAddress;
import com.vnapnic.myvib.model.AtmLocator;
import com.vnapnic.myvib.utils.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/5/2016.
 */
public class MapsFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnInfoWindowClickListener, View.OnClickListener {
    protected Location location;
    private View viewRoot;
    private MainActivity activity;
    private GoogleMap maps;
    private ImageView btnSearch;
    private AutoCompleteTextView edittext;
    private FontTextView btnBranches;
    private FontTextView btnATM;
    private FontTextView tvPos;
    private GoogleApiClient googleApiClient;
    private static final String PAGE = "key.page";
    private int page;
    protected List<ATMAddress> atmddress;


    public static MapsFragment newInstance(int tabMap) {
        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(newBundle(tabMap));
        return fragment;
    }

    private static Bundle newBundle(int page) {
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE, page);
        return bundle;
    }

    //  lifecycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            initDataFromBundle(savedInstanceState);
        } else {
            initDataFromBundle(getArguments());
        }
    }

    private void initDataFromBundle(Bundle savedInstanceState) {
        page = savedInstanceState.getInt(PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_maps, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.edittext = (AutoCompleteTextView) view.findViewById(R.id.edittext);
        this.btnATM = (FontTextView) view.findViewById(R.id.btnATM);
        this.tvPos = (FontTextView) view.findViewById(R.id.tvPos);
        this.btnBranches = (FontTextView) view.findViewById(R.id.btnBranches);
        this.btnSearch = (ImageView) view.findViewById(R.id.btnSearch);
        btnATM = (FontTextView) view.findViewById(R.id.btnATM);
        this.maps = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();


        apiClient();
        changeData(page);
        changeTab(page);

        this.btnATM.setOnClickListener(this);
        this.tvPos.setOnClickListener(this);
        this.btnBranches.setOnClickListener(this);
    }

    private void changeData(int page) {
        if (page == 1)
            onAddMapsATM();
        else if (page == 2)
            onAddMapsATM();
        else
            onAddMapsATM();
    }

    private void apiClient() {
        this.googleApiClient = new Builder(activity).addApi(LocationServices.API).
                addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this).
                addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this).build();
        this.googleApiClient.connect();
    }

    protected void createMe(double d, double d2) {
        Logger.d("namit", d + " " + d2);
        this.maps.addMarker(new MarkerOptions().position(new LatLng(d, d2)).title(getString(R.string.me)).snippet(getString(R.string.current_location)).icon(BitmapDescriptorFactory.fromResource((int) R.drawable.my_location)));
        this.maps.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(d, d2), 17.0f));
    }

    protected void setLoction(Location location) {
        createMe(location.getLatitude(), location.getLongitude());
        if (page == 1) {
            setData(atmddress, R.drawable.atm_icon, false);
        } else if (page == 2) {
            setData(atmddress, R.drawable.pos_icon, false);
        } else {
            setData(atmddress, R.drawable.branches_icon, false);
        }
    }

    public void changeTab(int tab) {
        switch (tab) {
            case 1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnATM.setTextColor(getActivity().getResources().getColor(R.color.choose_button, activity.getTheme()));
                } else {
                    btnATM.setTextColor(getActivity().getResources().getColor(R.color.choose_button));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvPos.setTextColor(getActivity().getResources().getColor(R.color.black, activity.getTheme()));
                } else {
                    tvPos.setTextColor(getActivity().getResources().getColor(R.color.black));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnBranches.setTextColor(getActivity().getResources().getColor(R.color.black, activity.getTheme()));
                } else {
                    btnBranches.setTextColor(getActivity().getResources().getColor(R.color.black));
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnATM.setTextColor(getActivity().getResources().getColor(R.color.black, activity.getTheme()));
                } else {
                    btnATM.setTextColor(getActivity().getResources().getColor(R.color.black));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvPos.setTextColor(getActivity().getResources().getColor(R.color.choose_button, activity.getTheme()));
                } else {
                    tvPos.setTextColor(getActivity().getResources().getColor(R.color.choose_button));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnBranches.setTextColor(getActivity().getResources().getColor(R.color.black, activity.getTheme()));
                } else {
                    btnBranches.setTextColor(getActivity().getResources().getColor(R.color.black));
                }
                break;
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnATM.setTextColor(getActivity().getResources().getColor(R.color.black, activity.getTheme()));
                } else {
                    btnATM.setTextColor(getActivity().getResources().getColor(R.color.black));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvPos.setTextColor(getActivity().getResources().getColor(R.color.black, activity.getTheme()));
                } else {
                    tvPos.setTextColor(getActivity().getResources().getColor(R.color.black));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnBranches.setTextColor(getActivity().getResources().getColor(R.color.choose_button, activity.getTheme()));
                } else {
                    btnBranches.setTextColor(getActivity().getResources().getColor(R.color.choose_button));
                }
                break;
        }

    }


    protected void createLocation() {
        this.location = LocationServices.FusedLocationApi.getLastLocation(this.googleApiClient);
        if (this.location != null) {
            Logger.d("namit", "Location lat = " + this.location.getLatitude() + ", long = " + this.location.getLongitude() + " ");
            setLoction(this.location);
        }
    }


    public void onAddMapsATM() {
        Gson gson = new Gson();
        AtmLocator response = gson.fromJson(getJson(activity, "response-export-atm.json"), AtmLocator.class);
        Logger.d("namit", ".........JSON MAP size: " + response.List.size());
        atmddress = new ArrayList<>();
        atmddress = response.List;
    }

    public void onAddMapsBranch() {
        Gson gson = new Gson();
        AtmLocator response = gson.fromJson(getJson(activity, "response-export-branch.json"), AtmLocator.class);
        Logger.d("namit", ".........JSON MAP size: " + response.List.size());
        atmddress = new ArrayList<>();
        atmddress = response.List;
    }

    public void onAddMapsPost() {
        Gson gson = new Gson();
        AtmLocator response = gson.fromJson(getJson(activity, "response-export-post.json"), AtmLocator.class);
        Logger.d("namit", ".........JSON MAP size: " + response.List.size());
        atmddress = new ArrayList<>();
        atmddress = response.List;
    }

    protected void setData(List<ATMAddress> list, int i, boolean z) {
        Logger.d("namit", "count total  = " + list.size());
        for (ATMAddress aTMAddress : list) {
            this.maps.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.valueOf(aTMAddress.getLatitude()).doubleValue(), Double.valueOf(aTMAddress.getLongitude()).doubleValue()))
                    .title(aTMAddress.getTargetName())
                    .snippet(aTMAddress.getAddress())
                    .icon(BitmapDescriptorFactory.fromResource(i)));
            this.maps.setInfoWindowAdapter(new PopupAdapter(activity.getLayoutInflater()));
            this.maps.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this);
        }
    }

    public String getJson(Context context, String json) {
        String jsonString = parseFileToString(context, json);
        return jsonString;
    }

    public String parseFileToString(Context context, String filename) {
        try {
            InputStream stream = context.getAssets().open(filename);
            int size = stream.available();

            byte[] bytes = new byte[size];
            stream.read(bytes);
            stream.close();

            return new String(bytes);

        } catch (IOException e) {
            Logger.d("GuiFormData", "IOException: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            ((MainActivity)getActivity()).setUptoolBar(ToolbarTyper.NONE_BACK);
            ((MainActivity)getActivity()).setTitle(getActivity().getResources().getString(R.string.find_us));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.googleApiClient != null && this.googleApiClient.isConnected()) {
            this.googleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (this.location != null) {
            setLoction(this.location);
        } else {
            createLocation();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Logger.d("namit", "Location connection fail ErrorCode = " + connectionResult.getErrorMessage());
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnATM:
                page = 1;
                changeTab(page);
                updateData(page);
                break;
            case R.id.tvPos:
                page = 2;
                changeTab(page);
                updateData(page);
                break;
            case R.id.btnBranches:
                page = 3;
                changeTab(page);
                updateData(page);
                break;
        }
    }

    public void updateData(int page) {
        if (page == 1) {
            updateLocator(atmddress, R.drawable.atm_icon, false);
        } else if (page == 2) {
            updateLocator(atmddress, R.drawable.pos_icon, false);
        } else {
            updateLocator(atmddress, R.drawable.branches_icon, false);
        }
    }

    public void updateLocator(List<ATMAddress> list, int i, boolean z) {
        this.maps.clear();
        createMe(location.getLatitude(), location.getLongitude());
        for (ATMAddress aTMAddress : list) {
            this.maps.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.valueOf(aTMAddress.getLatitude()).doubleValue(), Double.valueOf(aTMAddress.getLongitude()).doubleValue()))
                    .title(aTMAddress.getTargetName())
                    .snippet(aTMAddress.getAddress())
                    .icon(BitmapDescriptorFactory.fromResource(i)));
            this.maps.setInfoWindowAdapter(new PopupAdapter(activity.getLayoutInflater()));
            this.maps.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this);
        }
    }
}