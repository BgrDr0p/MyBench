package mybench.parisnanterre.fr.mybench;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mybench.parisnanterre.fr.mybench.BDD.MarkerDataSource;
import mybench.parisnanterre.fr.mybench.BDD.MyMarkerObj;
import mybench.parisnanterre.fr.mybench.R;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private Double Latitude = 0.00;
    private Double Longitude = 0.00;

    private GoogleMap mMap;

    Context context = this;
    MarkerDataSource data = new MarkerDataSource(context);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            // Instance pour acceder à la BDD
            //  MarkerDataSource data = new MarkerDataSource(context);
            // ouverture de la bdd
            data.open();
            // data.close

            //  Insertion dans la bdd
            /*
            data.addMarker(new MyMarkerObj("Banc simple", "Type Stalingrad ", "48.84421693211892, 2.4379933164371344"));
            data.addMarker(new MyMarkerObj("Banc double","Type Stalingrad","48.84462393068919, 2.449371479377353"));
            data.addMarker(new MyMarkerObj("Banc simple","Type Stalingrad", "48.86488440054312, 2.38154009068625"));
            data.addMarker(new MyMarkerObj("Banc simple", "Type Stalingrad", "48.84206181110276, 2.388888661335503"));
            data.addMarker(new MyMarkerObj("Banc double","Type Foch","48.841798798995406, 2.3899635222814877"));
            data.addMarker(new MyMarkerObj("Banc simple","Type Foch", "48.829347031218646, 2.308172585069839"));
            data.addMarker(new MyMarkerObj("Banc simple", "Type Foch", "48.86247961833146, 2.413008445073410"));
            data.addMarker(new MyMarkerObj("Banc double","Type Foch","48.851947509969634, 2.39115733470809"));
            data.addMarker(new MyMarkerObj("Banc simple","Type Foch", "48.86135121011293, 2.378851443111679"));
            */
        } catch (Exception e) {
            Log.i("Erreur  ", e.toString());
        }


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng centerCamera = new LatLng(48.872156, 2.347464);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centerCamera));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }


        //
       /* try {
            List<MyMarkerObj> m = data.getMyMarkers();
            for (int i = 0; i < m.size(); i++)
            {

                String[] slatlng =  m.get(i).getPosition().trim().split(", ");
                Double LATITUDE = Double.valueOf(String.valueOf(slatlng[0]));
                Double LONGITUDE = Double.valueOf(String.valueOf(slatlng[1]));
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(LATITUDE,LONGITUDE))
                        .title(m.get(i).getTitle())
                        .snippet(m.get(i).getSnippet())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))


                );

            }
        } catch (Exception e) {
            Log.i("Erreur ", e.toString());
        }


    }
*/
    }
}