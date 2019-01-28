package mybench.parisnanterre.fr.mybench.BDD;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
// CREATION DE LA BDD ET DE LA TABLE TB_BANC
    public class BancManager {
    // INITIALISATION DE LA BDD ET DE LA TABLE
    private static final String TB_BANC = "TB_BANC";  // nom de la TABLE
    private static final String  BANC_ID = "BANC_ID"; // colonnes de la table
    private static final String BANC_TITRE = "BANC_TITRE";
    private static final String BANC_LATITUDE = "BANC_LATITUDE";
    private static final String BANC_LONGITUDE = "BANC_LONGITUDE";
    private static final String BANC_INFO = "BANC_INFO";

    public static final String CREATE_TABLE_BANC = "CREATE TABLE "+TB_BANC+
            " (" +
            " "+BANC_ID+" INTEGER primary key," +
            " "+BANC_TITRE+" TEXT" +
            " "+BANC_LATITUDE+" TEXT" +
            " "+BANC_LONGITUDE+" TEXT" +
            " "+BANC_INFO+" TEXT" +
            ");";

    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    public BancManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);

    }
    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addBanc(Banc banc) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();


        values.put(BANC_TITRE,banc.getTitre());
        values.put(BANC_LATITUDE,banc.getLatitude());
        values.put(BANC_LONGITUDE,banc.getLongitude());
        values.put(BANC_INFO,banc.getInfo());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TB_BANC,null,values);
    }
    public int modAnimal(Banc banc) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(BANC_ID, banc.getID());

        String where = BANC_ID+" = ?";
        String[] whereArgs = {banc.getID()+""};

        return db.update(TB_BANC, values, where, whereArgs);
    }

    public int supAnimal(Banc banc) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = BANC_ID+" = ?";
        String[] whereArgs = {banc.getID()+""};

        return db.delete(TB_BANC, where, whereArgs);
    }

    public Banc getAnimal(int id) {
        // Retourne l'animal dont l'id est passé en paramètre

        Banc B =new Banc(0);

        Cursor c = db.rawQuery("SELECT * FROM "+TB_BANC+" WHERE "+BANC_ID+"="+id, null);
        if (c.moveToFirst()) {
            B.setId(c.getInt(c.getColumnIndex(BANC_ID)));
            B.setTitre(c.getString(c.getColumnIndex(BANC_TITRE)));
            B.setLatitude(c.getDouble(c.getColumnIndex(BANC_LATITUDE)));
            B.setLongitude(c.getDouble(c.getColumnIndex(BANC_LONGITUDE)));
            B.setInfo(c.getString(c.getColumnIndex(BANC_TITRE)));
            c.close();
        }

        return B;
    }

    public Cursor getBanc() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TB_BANC, null);
    }






}