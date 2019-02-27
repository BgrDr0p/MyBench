package mybench.parisnanterre.fr.mybench;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;
import mybench.parisnanterre.fr.mybench.BDD.MarkerDataSource;
import mybench.parisnanterre.fr.mybench.BDD.MyMarkerObj;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;

    Context context = this;
    MarkerDataSource data = new MarkerDataSource(context);






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

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


        LatLng centerCamera = new LatLng(48.872156,2.347464);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centerCamera));
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mMap.setMyLocationEnabled(true);
        }
        // Setting a custom info window adapter for the google map
        CustomInfoWindowsAdapter markerInfoWindowAdapter = new CustomInfoWindowsAdapter(getApplicationContext());
        googleMap.setInfoWindowAdapter(markerInfoWindowAdapter);
        LatLng position = new LatLng(48.82558556529131, 2.297074814610168);

        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.banc);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);
        Marker m = mMap.addMarker( new MarkerOptions().position(position).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));







        }





    }




