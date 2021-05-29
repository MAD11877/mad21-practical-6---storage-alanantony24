package sg.edu.np.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String ID = "ID";
    public static final String FOLLOWED = "FOLLOWED";
    public static final String USERS = "USERS";

    public DBHandler(Context context) {
        super(context, "UserDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USERS + "(" + NAME + " TEXT, " + DESCRIPTION + " TEXT, " + ID + " INT, " + FOLLOWED + " INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }
    public void addUser(User u){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, u.getName());
        values.put(DESCRIPTION, u.getDescription());
        values.put(ID, u.getId());
        values.put(FOLLOWED, u.isFollowed());
        db.insert(USERS, null, values);
        db.close();
    }
    public ArrayList<User> getUsers(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS", null);
        User u = null;
        ArrayList<User> usersList = new ArrayList<>();
        while(cursor.moveToNext()){
            u = new User();
            u.setName(cursor.getString(0));
            u.setDescription(cursor.getString(1));
            u.setId(cursor.getInt(2));
            u.setFollowed(cursor.getInt(3) == 1? true: false);
            usersList.add(u);
        }
        cursor.close();
        db.close();
        return usersList;
    }
    public void updateUser(User u, int id){
        id = u.id;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FOLLOWED, u.isFollowed());
        db.update(USERS, cv, ID + " = ? " , new String[]{String.valueOf(u.getId())});
    }
}
