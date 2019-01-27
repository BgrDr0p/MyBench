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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final LatLng BANC1 = new LatLng(48.86125629633995, 2.3263978958129883);
    private static final LatLng BANC2 = new LatLng(48.845259549865254, 2.3134374618530273);
    private static final LatLng BANC3 = new LatLng(48.83387658166071, 2.3323631286621094);


    private Marker banc1;
    private Marker banc2;
    private Marker banc3;



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

        LatLng centerCamera = new LatLng(48.872156,2.347464);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centerCamera));
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mMap.setMyLocationEnabled(true);
        }

        // Ajouter des bancs sous forme de marker

        banc1 = mMap.addMarker(new MarkerOptions()
                .position(BANC1)
                .title("premier banc")
                .snippet("Disponible")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        banc1.setTag(0);

        banc2 = mMap.addMarker(new MarkerOptions()
                .position(BANC2)
                .title("deuxieme banc")
                .snippet("Disponible")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        banc2.setTag(0);

        banc3 = mMap.addMarker(new MarkerOptions()
                .position(BANC3)
                .title(" troisieme banc")
                .snippet("Disponible")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        banc3.setTag(0);





    }

    
}
