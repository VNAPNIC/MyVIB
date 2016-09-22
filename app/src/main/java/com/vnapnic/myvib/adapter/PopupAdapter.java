package com.vnapnic.myvib.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.vnapnic.myvib.R;

public class PopupAdapter implements InfoWindowAdapter {

    private View f8557a;
    private LayoutInflater f8558b;

    public PopupAdapter(LayoutInflater layoutInflater) {
        this.f8557a = null;
        this.f8558b = null;
        this.f8558b = layoutInflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        if (this.f8557a == null) {
            this.f8557a = this.f8558b.inflate(R.layout.popup, null);
        }
        ((TextView) this.f8557a.findViewById(R.id.title)).setText(marker.getTitle());
        ((TextView) this.f8557a.findViewById(R.id.snippet)).setText(Html.fromHtml(marker.getSnippet()));
        return this.f8557a;
    }
}
