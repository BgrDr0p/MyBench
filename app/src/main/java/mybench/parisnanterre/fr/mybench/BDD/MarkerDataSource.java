package mybench.parisnanterre.fr.mybench.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class MarkerDataSource {
    MySQLHelper dbhelper;
    SQLiteDatabase db ;

    String[] cols = {MySQLHelper.TITLE, MySQLHelper.SNIPPET, MySQLHelper.POSITION};

    public MarkerDataSource(Context c) {
        dbhelper = new MySQLHelper(c);

    }

    public void open() throws SQLException{
        db = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.getReadableDatabase();
        if (db != null && db.isOpen())
        db.close();
    }
    // ajouter un banc
    public long addMarker(MyMarkerObj m){
        ContentValues v = new ContentValues();

        v.put(MySQLHelper.TITLE, m.getTitle());
        v.put(MySQLHelper.SNIPPET, m.getSnippet());
        v.put(MySQLHelper.POSITION,m.getPosition());




        return db.insert(MySQLHelper.TABLE_NAME, null, v);

    }

    public List<MyMarkerObj> getMyMarkers(){

        List<MyMarkerObj> markers = new ArrayList<MyMarkerObj>();

        Cursor cursor = db.query(MySQLHelper.TABLE_NAME, cols, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MyMarkerObj m = cursorToMarker(cursor);
            markers.add(m);
            cursor.moveToNext();
        }
        cursor.close();


        return markers;
    }

    public void deleteMarker(MyMarkerObj m){
        db.delete(MySQLHelper.TABLE_NAME, MySQLHelper.POSITION + " = '" + m.getPosition() + "'", null);
    }


    private MyMarkerObj cursorToMarker(Cursor cursor) {
        MyMarkerObj m = new MyMarkerObj();
        m.setTitle(cursor.getString(0));
        m.setSnippet(cursor.getString(1));

        m.setPosition(cursor.getString(2));
        return m;
    }



}
