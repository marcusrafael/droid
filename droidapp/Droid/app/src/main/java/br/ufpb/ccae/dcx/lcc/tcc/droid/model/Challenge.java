package br.ufpb.ccae.dcx.lcc.tcc.droid.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by xavier on 10/17/15.
 */
@DatabaseTable(tableName = "challenge")
public class Challenge implements Serializable {


    public static final String EASY = "Fácil";
    public static final String MEDIUM = "Médio";
    public static final String HARD = "Difícil";


    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String description;

    @DatabaseField
    private String level;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Location location;


    public Challenge() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
