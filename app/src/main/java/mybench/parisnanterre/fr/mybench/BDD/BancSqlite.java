package mybench.parisnanterre.fr.mybench.BDD;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
// CREATION DE LA BDD ET DE LA TABLE TB_BANC
public class BancSqlite extends SQLiteOpenHelper {
    // INITIALISATION DE LA BDD ET DE LA TABLE
    private static final String TB_BANC = "TB_BANC";  // nom de la TABLE
    // colonnes de la table
    private static final String  BANC_ID = "BANC_ID";
    private static final String BANC_TITRE = "BANC_TITRE";
    private static final String BANC_LATITUDE = "BANC_LATITUDE";
    private static final String BANC_LONGITUDE = "BANC_LONGITUDE";
    private static final String BANC_INFO = "BANC_INFO";

    private static final String CREATE_BDD =
            "CREATE TABLE " + TB_BANC + " ("
            + BANC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BANC_TITRE  + "TEXT NOT NULL,"
            + BANC_LATITUDE + "TEXT  NOT NULL,"
            + BANC_LONGITUDE + " TEXT  NOT NULL, "
            + BANC_INFO + "TEXT NOT NULL);";

    public BancSqlite(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }


    // création de la table
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TB_BANC + ";");
        onCreate(db);
    }
    // Ajouter d'autres méthodes pour modifier,supprimer le contenus des colonnes


}