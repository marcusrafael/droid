package br.ufpb.ccae.dcx.lcc.tcc.droid.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Answer;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Location;


/**
 * Created by xavier on 10/28/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "droid.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {

            TableUtils.createTable(connectionSource, Answer.class);
            TableUtils.createTable(connectionSource, Challenge.class);
            TableUtils.createTable(connectionSource, Location.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {

            TableUtils.dropTable(connectionSource, Answer.class, true);
            TableUtils.dropTable(connectionSource, Challenge.class, true);
            TableUtils.dropTable(connectionSource, Location.class, true);

            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {

        super.close();

    }
}


