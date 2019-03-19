package mybench.parisnanterre.fr.mybench;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;


import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class ClosestBenchesActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    ListView listView ;
    ArrayList<Bench> listeBancs = new ArrayList<Bench>();
    private GoogleApiClient mGoogleApiClient;
  //  private Location currentLocation;
    private LocationManager locationManager;
    //private LocationRequest mLocationRequest; // comprendre comment LocationRequest fonctionne
    private FusedLocationProviderClient mFusedLocationClient;
    public LatLng latLng;
    private RequestQueue mQueue; // queue des requêtes API envoyées, utilisé ligne 211~


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closest_benches);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);


        listeBancs.add(new Bench(48.82558556529131, 2.2970748146101685, "Banc porte de Vanves"));
        listeBancs.add(new Bench(48.84710111188743, 2.3696500305665693, "Banc avenue Ledru-Rollin"));
        listeBancs.add(new Bench(48.83248316220944, 2.3868537740028204, "Banc à Bercy"));
        listeBancs.add(new Bench(48.84839432099857, 2.3957903182003686, "Banc à Nation"));
        listeBancs.add(new Bench(48.84419482018623, 2.4398082679503297, "Banc chateau de Vincennes"));
        listeBancs.add(new Bench(48.82967862984375, 2.4008100192619177, "Banc porte de Charenton"));
        listeBancs.add(new Bench(48.852372565095216, 2.3695847475448555, "Banc à Bastille"));
        listeBancs.add(new Bench(48.85965097919809, 2.3721461680849063, "Banc à Richard Lenoir"));

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // NE PAS OUBLIER DE REGARDER https://stackoverflow.com/questions/28301273/onconnected-not-being-called-to-get-location-updates-googleplayapi-for-locati au cas où
        // tuto : https://stackoverflow.com/questions/33415033/getting-current-location-in-android-studio-app
        if (ContextCompat.checkSelfPermission(ClosestBenchesActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient); // cas où permission gps déjà acceptée, on récupère la position géo
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        }
        else {
            // requestLocationPermission(); // si pas déjà acceptée, requester la permission

        }

        Task<Location> l = mFusedLocationClient.getLastLocation();

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            /*Toast.makeText(ClosestBenchesActivity.this, "Lat :"+latLng.latitude+" ; Long : "+latLng.longitude,
                                    Toast.LENGTH_SHORT).show();*/
                        }
                    }
                });



        ArrayList<String> values = new ArrayList<String>();
        int i = 0;
        Double distance = 0.0;
        while (i < listeBancs.size()){
            /*Double latUser = (Double) latLng.latitude;
            Double longUser = (Double) latLng.longitude;
            Double latBanc = (Double) listeBancs.get(i).getX();
            Double longBanc = (Double) listeBancs.get(i).getY();*/

            //distance = Math.sqrt((Math.pow(latLng.latitude-0, 2) +  Math.pow(latLng.longitude-0, 2))); // calcul de la distance entre user et banc
            //distance = Math.hypot(3-2, 3-1);
            if(distance < 0) // mise à la valeur absolue
                distance = distance * (-1);

            listeBancs.get(i).setDistance(distance);
            values.add(listeBancs.get(i).getNom());
            i++;
        }
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                double latBanc = listeBancs.get(position).getX();
                double longBanc = listeBancs.get(position).getY();
                /*Toast.makeText(getApplicationContext(),
                        "Ce banc est à :"+listeBancs.get(position).getX()+";"+ listeBancs.get(position).getY()+" et se trouve à "+getDistanceMeters(latLng.latitude, latLng.longitude, latBanc, longBanc) + " metres " , Toast.LENGTH_LONG)
                        .show();*/
                Toast.makeText(getApplicationContext(),
                        "Votre banc se trouve à "+getDistanceMeters(latLng.latitude, latLng.longitude, latBanc, longBanc) + " metres " , Toast.LENGTH_LONG)
                        .show();

                /* //afficher distance en coordonnées (ancienne version donc)
                Toast.makeText(getApplicationContext(),
                        "Ce banc est à :"+listeBancs.get(position).getX()+";"+ listeBancs.get(position).getY()+" et se trouve à "+ Math.sqrt((Math.pow(latLng.latitude-listeBancs.get(position).getX(), 2) +  Math.pow(latLng.longitude-listeBancs.get(position).getY(), 2))) , Toast.LENGTH_LONG)
                        .show();
                 */



            }

        });
    }


    private void jsonParse() {

        String url = "https://api.myjson.com/bins/kp9wz";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("employees");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                String firstName = employee.getString("firstname");
                                int age = employee.getInt("age");
                                String mail = employee.getString("mail");

                                //mTextViewResult.append(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }


    public static long getDistanceMeters(double lat1, double lng1, double lat2, double lng2) {

        double l1 = toRadians(lat1);
        double l2 = toRadians(lat2);
        double g1 = toRadians(lng1);
        double g2 = toRadians(lng2);

        double dist = acos(sin(l1) * sin(l2) + cos(l1) * cos(l2) * cos(g1 - g2));
        if(dist < 0) {
            dist = dist + Math.PI;
        }

        return Math.round(dist * 6378100);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}