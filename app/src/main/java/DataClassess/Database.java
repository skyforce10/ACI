package DataClassess;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@androidx.room.Database(entities = {OrderMasterTable.class,OrderDetailTable.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract OrderMasterTableDao orderMasterTableDao();
    public abstract OrderDetailTableDao orderDetailTableDao();

    private static volatile Database INSTANCE;
    private static final String DB_NAME = "orderdata";

    private static final RoomDatabase.Callback databaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // Call a method to insert default data when the database is first created
            new PopulateDatabaseAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDatabaseAsync extends AsyncTask<Void, Void, Void> {
        private final OrderMasterTableDao orderMasterTableDao_var;
        private final OrderDetailTableDao orderDetailTableDao_var;


        PopulateDatabaseAsync(Database database) {
            orderMasterTableDao_var = database.orderMasterTableDao();
            orderDetailTableDao_var=database.orderDetailTableDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Insert your default data here

            return null;
        }
    }

    public static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    File databaseFile = new File(context.getExternalFilesDir(null), DB_NAME);
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, databaseFile.getAbsolutePath())
                            .addCallback(databaseCallback)  // Add the callback
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
