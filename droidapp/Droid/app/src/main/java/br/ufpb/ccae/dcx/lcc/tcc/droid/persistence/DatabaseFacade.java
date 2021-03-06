package br.ufpb.ccae.dcx.lcc.tcc.droid.persistence;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Answer;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Location;

/**
 * Created by xavier on 11/4/15.
 */
public class DatabaseFacade {

    private static DatabaseFacade instance;

    private DatabaseHelper databaseHelper;


    private DatabaseFacade(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static DatabaseFacade getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseFacade(context);
        }
        return instance;
    }


    public List<Challenge> getAllChallenges() {
        List<Challenge> challenges = new ArrayList<>();
        try {
            Dao<Challenge, String> challengeDAO = DaoManager.createDao(databaseHelper.getConnectionSource(), Challenge.class);
            challenges = challengeDAO.queryForAll();
        } catch (SQLException e) {
            return challenges;
        }
        return challenges;
    }

    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        try {
            Dao<Location, String> locationDAO = DaoManager.createDao(databaseHelper.getConnectionSource(), Location.class);
            locations = locationDAO.queryForAll();
        } catch (SQLException e) {
            return locations;
        }
        return locations;
    }

    public List<Answer> getAnswersFromChallenge(Challenge challenge) {
        List<Answer> answers = new ArrayList<>();
        try {
            Dao<Answer, String> answerDAO = DaoManager.createDao(databaseHelper.getConnectionSource(), Answer.class);
            answers = answerDAO.queryForEq("challenge_id", challenge.getId());
        } catch (SQLException e) {
            return answers;
        }
        return answers;
    }
}
