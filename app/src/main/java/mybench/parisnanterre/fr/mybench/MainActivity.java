package mybench.parisnanterre.fr.mybench;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        private int STORAGE_LOCATION_CODE = 1;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        Button buttonRequest = findViewById(R.id.bt_Banc);
        Button buttonClosest = findViewById(R.id.bt_Closest);
        Button buttonFormulaire = findViewById(R.id.bt_formulaire);
<<<<<<< HEAD
        Button buttonBenchs = findViewById(R.id.bt_benchs);
=======
        Button buttonRecup = findViewById(R.id.btn_recup);

        buttonRecup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "You have already granted this permission!",
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), RecupBench.class);
                    startActivity(i);
                }
                else {
                    requestLocationPermission();
                }
                // test commit to branch
            }
        });
>>>>>>> 9d220ae181f8256368cabb8f670be8715e847236

        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "You have already granted this permission!",
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(i);
                }
                else {
                    requestLocationPermission();
                }

            }
        });

        buttonClosest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "You have already granted this permission!",
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), ClosestBenchesActivity.class);
                    startActivity(i);

            }
        });
        try
        {
            buttonFormulaire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "You have already granted this permission!",
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), AddBench.class);
                    startActivity(i);

                }
            })
            ;
        }
        catch (Exception e)
        {
            Log.i("Erreur0  ", e.toString());
        }

        buttonBenchs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You have already granted this permission!",
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainBenchActivity.class);
                startActivity(i);

            }
        })
        ;


    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                       @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, STORAGE_LOCATION_CODE);
                        }


                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, STORAGE_LOCATION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_LOCATION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);


            }
            else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}