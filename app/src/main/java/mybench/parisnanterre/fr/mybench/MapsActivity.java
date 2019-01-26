package mybench.parisnanterre.fr.mybench;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import mybench.parisnanterre.fr.mybench.BDD.Banc;
import mybench.parisnanterre.fr.mybench.BDD.BancBDD;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {



    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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

        Marker premierBanc = mMap.addMarker(new MarkerOptions().position(new LatLng(48.86125629633995, 2.3263978958129883))
                .title("Banc1").snippet(" mon premier banc"));

        Marker deuxiemeBanc = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.845259549865254, 2.3134374618530273))
                .title("Banc2").snippet(" mon deuxieme banc"));

        Marker troisiemeBanc = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.83387658166071, 2.3323631286621094))
                .title("Banc3").snippet(" mon troisieme banc"));






    }

    
}
