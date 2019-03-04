package mybench.parisnanterre.fr.mybench;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class AddBench extends AppCompatActivity {

    private EditText title, environnement, pollution,bruit;
    private Button bouton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbench);

        // recuperation des elements venant de la vue

       title = (EditText)findViewById(R.id.type);
       environnement = (EditText)findViewById(R.id.environnement);

        pollution = (EditText)findViewById(R.id.pollution);
        bruit = (EditText)findViewById(R.id.bruit);
        bouton = (Button)findViewById(R.id.bouton);
       final String position = "48.84421693211892, 2.4379933164371344";
        // creation du listener sur l'objet bouton

        bouton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                // l'operation de communication doit se faire en arriere plan d'ou l'utilisation de l'asynctask

                new AddBenchMethod().execute("create",title.getText().toString(),position,environnement.getText().toString(), pollution.getText().toString(),bruit.getText().toString());

            }
        });
    }

    // Asynctask destine a l'operation d'arriere plan

    private class AddBenchMethod extends AsyncTask<String, Void,String> {
        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... args) {

            HttpQuery httpQuery = new HttpQuery();

            try {
                return httpQuery.Create(args[0],args[1],args[2],args[3],args[4],args[5]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            if(result != null) Log.e("ERROR",result);

            Toast.makeText(getApplicationContext(),"operation reussie "+result,Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPreExecute() {

            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog

        }
    }

}