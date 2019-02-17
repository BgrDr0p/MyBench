package mybench.parisnanterre.fr.mybench.Controleur;

import android.util.Log;
import mybench.parisnanterre.fr.mybench.Controleur.AccesHTTP;



import org.json.JSONArray;

public class AccessDistant implements AsyncResponse {


    // une constante pour l'adresse du serveur
    private static final String SERVERADD = "http://192.168.0.19/serveur.php";

    public AccessDistant(){

        super();

    }

    /**
     * Execution au retour du serveur distant
     * @param output
     */

    @Override
    public void processFinish(String output){

        Log.d("serveur", "****************"+output);

        String [] message = output.split("%");

        if(message.length > 1) {

            if (message[0].equals("enreg")) {
                Log.d("enreg", "*******************" + message[1]);

            } else {
                if (message[0].equals("dernier")) {
                    Log.d("dernier", "*******************" + message[1]);

                } else {
                    if (message[0].equals("erreur")) {
                        Log.d("erreur", "*******************" + message[1]);

                    }

                }
            }
        }

    }

    public void envoi(String operation, JSONArray lesdonneesJSON){

        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesdonneesJSON.toString());
    }




}
