package mybench.parisnanterre.fr.mybench;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowsAdapter implements GoogleMap.InfoWindowAdapter {


    private Context context;

    public CustomInfoWindowsAdapter(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.custom_infowindow, null);
        /*
        TextView tvGir = (TextView) v.findViewById(R.id.environnement);
        TextView tvDetails = (TextView) v.findViewById(R.id.pollution);
        // TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
        tvGir.setText("Mauvais paysage");
        tvDetails.setText("Très pollué");
        //tvLng.setText("Longitude:"+ latLng.longitude);
        */


        return v;
    }


    }







