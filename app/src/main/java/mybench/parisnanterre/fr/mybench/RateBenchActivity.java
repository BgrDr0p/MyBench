package mybench.parisnanterre.fr.mybench;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RateBenchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_bench);

        //get the spinner from the xml.
        Spinner dropdown_proprete = findViewById(R.id.note_proprete);
        Spinner dropdown_env = findViewById(R.id.note_env);
//create a list of items for the spinner.
        String[] notes = new String[]{"1", "2", "3", "4", "5" };
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, notes);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, notes);

//set the spinners adapter to the previously created one.
        dropdown_proprete.setAdapter(adapter);
        dropdown_env.setAdapter(adapter2);

        //dropdown.setAdapter(adapter2);
    }
}
