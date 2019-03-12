package mybench.parisnanterre.fr.mybench;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainBenchActivity extends AppCompatActivity {

    private String TAG = MainBenchActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    // URL to get bench JSON
    private static String url = "https://mybench.000webhostapp.com/benchs.json";

    ArrayList<HashMap<String, String>> benchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benchs_activity);

        benchList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new GetBenchs().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetBenchs extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainBenchActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray benchs = jsonObj.getJSONArray("benchs");

                    // looping through All Contacts
                    for (int i = 0; i < benchs.length(); i++) {
                        JSONObject c = benchs.getJSONObject(i);

                        String id = c.getString("id");
                        String title = c.getString("title");
                        String snippet = c.getString("snippet");

                        // Phone node is JSON Object
                        JSONObject position = c.getJSONObject("position");
                        String latitude = position.getString("latitude");
                        String longitude = position.getString("longitude");

                        // tmp hash map for single contact
                        HashMap<String, String> bench = new HashMap<>();

                        // adding each child node to HashMap key => value
                        bench.put("id", id);
                        bench.put("title", title);
                        bench.put("snippet", snippet);
                        bench.put("latitude", latitude);
                        bench.put("longitude", longitude);


                        // adding contact to contact list
                        benchList.add(bench);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainBenchActivity.this, benchList,
                    R.layout.list_item, new String[]{"title", "snippet",
                    "latitude", "longitude"}, new int[]{R.id.title,
                    R.id.snippet, R.id.latitude, R.id.longitude});

            lv.setAdapter(adapter);
        }

    }
}
