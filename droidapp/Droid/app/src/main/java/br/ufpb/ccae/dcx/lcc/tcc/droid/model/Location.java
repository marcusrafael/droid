package br.ufpb.ccae.dcx.lcc.tcc.droid.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by xavier on 10/28/15.
 */
@DatabaseTable(tableName = "location")
public class Location implements Serializable {


    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String description;

    @DatabaseField
    private double latitude;

    @DatabaseField
    private double longitude;

    @DatabaseField
    private double radius;

    @ForeignCollectionField(eager = true, maxEagerForeignCollectionLevel = 1)
    private Collection<Challenge> challenges;


    public Location() { }


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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Collection<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(Collection<Challenge> challenges) {
        this.challenges = challenges;
    }
}
