package mybench.parisnanterre.fr.mybench;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancBDD {

    private static final int VERSION_BDD = 1; // version de la bdd
    private static final String NOM_BDD = "banc.db"; // nom du fichier de la bdd
    private static final String TB_BANC = "BDD_BANC"; // nom de la TABLE
    // colonnes de la bdd et leur ordre dans la table

    private static final String  BANC_ID = "BANC_ID";

    private static final String BANC_TITRE = "BANC_TITRE";
    private static final int NUM_BANC_TITRE = 0; //ordre de la colonne

    private static final String BANC_LATITUDE = "BANC_LATITUDE";
    private static final int NUM_BANC_LATITUDE = 1; //ordre de la colonne

    private static final String BANC_LONGITUDE = "BANC_LONGITUDE";
    private static final int NUM_BANC_LONGITUDE = 2; //ordre de la colonne

    private static final String BANC_INFO = "BANC_INFO";
    private static final int NUM_BANC_INFO = 3; //ordre de la colonne

    private SQLiteDatabase bdd;

    private BancSqlite BancSqlite;

    public BancBDD(Context context){
        //On crée la BDD et sa table
        BancSqlite = new BancSqlite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = BancSqlite.getWritableDatabase();
    }
    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertBanc(Banc banc){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        values.put(BANC_TITRE, banc.getTitre());
        values.put(BANC_LATITUDE,banc.getLatitude());
        values.put(BANC_LONGITUDE,banc.getLongitude());
        values.put(BANC_INFO,banc.getInfo());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TB_BANC, null, values);
    }

    public int updateBanc(int id, Banc banc){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(BANC_TITRE, banc.getTitre());
        values.put(BANC_LATITUDE,banc.getLatitude());
        values.put(BANC_LONGITUDE,banc.getLongitude());
        values.put(BANC_INFO,banc.getInfo());
        return bdd.update(TB_BANC, values, BANC_ID + " = " +id, null);
    }

    public int removeBancWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TB_BANC, BANC_ID + " = " +id, null);
    }


    public Banc getBancWithParameter(String Parameter) {
        // TODO méthode qui retourne des bancs selon des paramètres
        return null;
    }
    private Banc cursorToBanc(Cursor c)
    {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0) {
            return null;
        }
        c.moveToFirst();
        //On créé un livre
        Banc banc = new Banc();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        banc.setId(c.getInt(NUM_BANC_TITRE));
        banc.setTitre(c.getString(NUM_BANC_TITRE));
        banc.setLatitude(c.getDouble(NUM_BANC_LATITUDE));
        banc.setLongitude(c.getDouble(NUM_BANC_LONGITUDE));
        banc.setInfo(c.getString(NUM_BANC_TITRE));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return banc;

    }







}
