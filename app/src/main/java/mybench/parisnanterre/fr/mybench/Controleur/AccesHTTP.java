package mybench.parisnanterre.fr.mybench.Controleur;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AccesHTTP extends AsyncTask<String, Integer, Long> {

    private ArrayList<NameValuePair> parametre;
    private String retour = null;
    public AsyncResponse delegate = null;

    public AccesHTTP(){

        parametre = new ArrayList<NameValuePair>();
    }

    /**
     * Ajout d'un parametre post
     * @param nom
     * @param valeur
     */
    public void addParam(String nom, String valeur){

        parametre.add(new BasicNameValuePair(nom, valeur));

    }

    /**
     * Connection en tache de fond dans un thread separer
     * @param strings
     * @return
     */

    @Override
    protected Long doInBackground(String... strings) {

        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]);

        try {
            paramCnx.setEntity(new UrlEncodedFormEntity(parametre));
            HttpResponse reponse = cnxHttp.execute(paramCnx);
            retour = EntityUtils.toString(reponse.getEntity()); // transformation de la reponse sous forme de toString

        } catch (UnsupportedEncodingException e) {
            Log.d("Erreur encodage","************** "+e.toString());
        } catch (ClientProtocolException e) {
            Log.d("Erreur de protocole","************** "+e.toString());
        } catch (IOException e) {
            Log.d("Erreur entrée sortie","************** "+e.toString());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Long result){

        delegate.processFinish(retour.toString());

    }
}
