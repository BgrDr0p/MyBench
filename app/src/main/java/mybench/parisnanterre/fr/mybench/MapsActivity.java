package mybench.parisnanterre.fr.mybench;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import mybench.parisnanterre.fr.mybench.BDD.MarkerDataSource;
import mybench.parisnanterre.fr.mybench.BDD.MyMarkerObj;
import mybench.parisnanterre.fr.mybench.BDD.MySQLHelper;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {



    private GoogleMap mMap;

    Context context = this;
   






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);





    }
















    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        LatLng centerCamera = new LatLng(48.872156,2.347464); // position de la caméra sur la  ville de Paris
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centerCamera));
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mMap.setMyLocationEnabled(true);
        }

        // Ajouter des bancs sous forme de marker a proximité  de la position de l'utilisateur
        MarkerDataSource data = new MarkerDataSource(context);

        data.open();



        //  data.addMarker(new MyMarkerObj("twitter","test" ,new LatLng(48.86125629633995, 2.3263978958129883)));
        data.addMarker(new MyMarkerObj("test", "test", "48.86125629633995, 2.3263978958129883"));
        data.addMarker(new MyMarkerObj("test2","test","48.845259549865254, 2.3134374618530273"));
        data.addMarker(new MyMarkerObj("test3","test", "48.83387658166071, 2.3323631286621094"));
        try {
            List<MyMarkerObj> m = data.getMyMarkers();
            for (int i = 0; i < m.size(); i++)
            {




                System.out.println("ICI");
                String[] slatlng =  m.get(i).getPosition().trim().split(", ");

                Double LATITUDE = Double.valueOf(String.valueOf(slatlng[0]));
                Double LONGITUDE = Double.valueOf(String.valueOf(slatlng[1]));
                System.out.println("LATITUDE " + LATITUDE);
                System.out.println("LONGITUDE " + LONGITUDE);



                 mMap.addMarker(new MarkerOptions()
                         .position(new LatLng(LATITUDE,LONGITUDE))
                         .title(m.get(i).getTitle())
                         .snippet(m.get(i).getSnippet())

                );


            }
        } catch (Exception e) {
            Log.i("hello2", e.toString());
        }








    }




}