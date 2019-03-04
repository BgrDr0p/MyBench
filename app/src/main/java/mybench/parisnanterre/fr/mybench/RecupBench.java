package mybench.parisnanterre.fr.mybench;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RecupBench extends AppCompatActivity {

    private Button recup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recup);
        recup = (Button)findViewById(R.id.btn_recup);

        recup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    //On appelle la base de données avec une requête de connection, et un email
                    AsyncTask threadConnection = new RecupBenchMethod().execute("read");

                    //On récupère l'identifiant de ce qui a été retourné par la base de données sous forme de String
                    String response = (String) threadConnection.get();
                    //On convertit le String en JSON
                    //JSONObject json = new JSONObject(response);
                    JSONArray json = new JSONArray(response);
                    ArrayList<Object> banc= new ArrayList<>();

                    for (int i = 0; i <json.length(); i++)
                    {
                        banc.add(json.get(i));
                      //  String id = json.getString("BENCH_ID");
                      // String title = json.getString("BENCH_TITLE");
                     //   Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG).show();

                    }
                    //On récupère la donnée d'identifiant id


                    // recuperation des elements venant de la vue

                    // creation du listener sur l'objet bouton




                }
                catch (ExecutionException e )
                {
                    e.printStackTrace();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }




            }
        });
    }



            private class RecupBenchMethod extends AsyncTask<String, Void, String> {
                @TargetApi(Build.VERSION_CODES.KITKAT)
                @Override
                protected String doInBackground(String... args) {

                    HttpQuery httpQuery = new HttpQuery();

                    try {
                        return httpQuery.Read(args[0]);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                // onPostExecute displays the results of the AsyncTask.
                @Override
                protected void onPostExecute(String result) {

                    if (result == null) Log.e("ERROR1", result);

                     Toast.makeText(getApplicationContext(),"operation reussie "+result,Toast.LENGTH_LONG).show();
                }

                @Override
                protected void onPreExecute() {

                    // Things to be done before execution of long running operation. For
                    // example showing ProgessDialog

                }

            }


        }

