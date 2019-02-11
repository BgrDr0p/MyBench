package mybench.parisnanterre.fr.mybench;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ClosestBenchesActivity extends Activity {
    ListView listView ;
    ArrayList<Bench> listeBancs = new ArrayList<Bench>();


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


        // Defined Array values to show in ListView
        /*String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };*/
        ArrayList<String> values = new ArrayList<String>();
        int i = 0;
        while (i < listeBancs.size()){
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
                Toast.makeText(getApplicationContext(),
                        "Ce banc est à :"+listeBancs.get(position).getX()+";"+ listeBancs.get(position).getY()+" et se trouve à"+ listeBancs.get(position).getDistance() , Toast.LENGTH_LONG)
                        .show();



            }

        });
    }

}