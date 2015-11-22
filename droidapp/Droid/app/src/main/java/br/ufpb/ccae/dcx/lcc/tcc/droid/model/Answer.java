package br.ufpb.ccae.dcx.lcc.tcc.droid.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by xavier on 10/17/15.
 */
@DatabaseTable(tableName = "answer")
public class Answer implements Serializable {


    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private boolean correct;

    @DatabaseField
    private String description;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Challenge challenge;


    public Answer() { }


    public Answer(Challenge challenge, boolean correct, String description) {
        this.challenge = challenge;
        this.correct = correct;
        this.description = description;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
