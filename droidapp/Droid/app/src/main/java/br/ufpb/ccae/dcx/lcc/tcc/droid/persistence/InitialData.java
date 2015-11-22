package br.ufpb.ccae.dcx.lcc.tcc.droid.persistence;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Answer;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Location;

/**
 * Created by xavier on 10/28/15.
 */
public class InitialData {

    public static void loadInitialData(Context context) {


        try {

            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            Dao<Answer, String> answerDAO = DaoManager.createDao(databaseHelper.getConnectionSource(), Answer.class);
            Dao<Challenge, String> challengeDAO = DaoManager.createDao(databaseHelper.getConnectionSource(), Challenge.class);
            Dao<Location, String> locationDAO = DaoManager.createDao(databaseHelper.getConnectionSource(), Location.class);

            if(challengeDAO.countOf() == 0) {

                Location joaoPessoa = new Location();
                joaoPessoa.setDescription("João Pessoa");
                joaoPessoa.setLatitude(-7.0833);
                joaoPessoa.setLongitude(-34.8333);
                joaoPessoa.setRadius(150000);
                locationDAO.create(joaoPessoa);

                Location recife = new Location();
                recife.setDescription("Recife");
                recife.setLatitude(-8.0500);
                recife.setLongitude(-34.9000);
                recife.setRadius(150000);
                locationDAO.create(recife);

                Location salvador = new Location();
                salvador.setDescription("Salvador");
                salvador.setLatitude(-12.9747);
                salvador.setLongitude(-38.4767);
                salvador.setRadius(150000);
                locationDAO.create(salvador);

                Location maceio = new Location();
                maceio.setDescription("Maceió");
                maceio.setLatitude(-9.6658);
                maceio.setLongitude(-35.7350);
                maceio.setRadius(150000);
                locationDAO.create(maceio);

                Location saoLuis = new Location();
                saoLuis.setDescription("São Luís");
                saoLuis.setLatitude(-2.5283);
                saoLuis.setLongitude(-44.3044);
                saoLuis.setRadius(150000);
                locationDAO.create(saoLuis);

                Location natal = new Location();
                natal.setDescription("Natal");
                natal.setLatitude(-5.7400);
                natal.setLongitude(-35.2000);
                natal.setRadius(200000);
                locationDAO.create(natal);

                Location aracaju = new Location();
                aracaju.setDescription("Aracaju");
                aracaju.setLatitude(-10.9167);
                aracaju.setLongitude(-37.0500);
                aracaju.setRadius(150000);
                locationDAO.create(aracaju);

                Location fortaleza = new Location();
                fortaleza.setDescription("Fortaleza");
                fortaleza.setLatitude(-3.7183);
                fortaleza.setLongitude(-38.5428);
                fortaleza.setRadius(600000);
                locationDAO.create(fortaleza);

                Location teresina = new Location();
                teresina.setDescription("Teresina");
                teresina.setLatitude(-5.0949);
                teresina.setLongitude(-42.8042);
                teresina.setRadius(150000);
                locationDAO.create(teresina);

                // =========

                Challenge c1 = new Challenge();
                c1.setDescription("Qual a capital do estado de Alagoas?");
                c1.setLevel(Challenge.EASY);;
                c1.setLocation(maceio);
                challengeDAO.create(c1);

                Answer a11 = new Answer(c1, false, "Recife");
                Answer a12 = new Answer(c1, false, "João Pessoa");
                Answer a13 = new Answer(c1, true, "Maceió");
                Answer a14 = new Answer(c1, false, "Aracajú");

                answerDAO.create(a11);
                answerDAO.create(a12);
                answerDAO.create(a13);
                answerDAO.create(a14);

                // =========

                Challenge c2 = new Challenge();
                c2.setDescription("Qual a capital do estado da Bahia?");
                c2.setLevel(Challenge.MEDIUM);
                c2.setLocation(salvador);
                challengeDAO.create(c2);

                Answer a21 = new Answer(c2, false, "Recife");
                Answer a22 = new Answer(c2, false, "João Pessoa");
                Answer a23 = new Answer(c2, true, "Salvador");
                Answer a24 = new Answer(c2, false, "Aracajú");

                answerDAO.create(a21);
                answerDAO.create(a22);
                answerDAO.create(a23);
                answerDAO.create(a24);

                // =========

                Challenge c3 = new Challenge();
                c3.setDescription("Qual a capital do estado do Ceará?");
                c3.setLevel(Challenge.HARD);
                c3.setLocation(fortaleza);
                challengeDAO.create(c3);

                Answer a31 = new Answer(c3, false, "Salvador");
                Answer a32 = new Answer(c3, false, "Recife");
                Answer a33 = new Answer(c3, false, "João Pessoa");
                Answer a34 = new Answer(c3, true, "Fortaleza");

                answerDAO.create(a31);
                answerDAO.create(a32);
                answerDAO.create(a33);
                answerDAO.create(a34);

                // =========

                Challenge c4 = new Challenge();
                c4.setDescription("Qual a capital do estado do Maranhão?");
                c4.setLevel(Challenge.EASY);
                c4.setLocation(saoLuis);
                challengeDAO.create(c4);

                Answer a41 = new Answer(c4, false, "Natal");
                Answer a42 = new Answer(c4, true, "São Luís");
                Answer a43 = new Answer(c4, false, "João Pessoa");
                Answer a44 = new  Answer(c4, false, "Fortaleza");

                answerDAO.create(a41);
                answerDAO.create(a42);
                answerDAO.create(a43);
                answerDAO.create(a44);

                // =========

                Challenge c5 = new Challenge();
                c5.setDescription("Qual a capital do estado da Paraíba?");
                c5.setLevel(Challenge.MEDIUM);
                c5.setLocation(joaoPessoa);
                challengeDAO.create(c5);

                Answer a51 = new Answer(c5, false, "Salvador");
                Answer a52 = new Answer(c5, false, "São Luís");
                Answer a53 = new Answer(c5, false, "Fortaleza");
                Answer a54 = new Answer(c5, true, "João Pessoa");

                answerDAO.create(a51);
                answerDAO.create(a52);
                answerDAO.create(a53);
                answerDAO.create(a54);

                // =========

                Challenge c6 = new Challenge();
                c6.setDescription("Qual a capital do estado do Piauí?");
                c6.setLevel(Challenge.HARD);
                c6.setLocation(teresina);
                challengeDAO.create(c6);

                Answer a61 = new Answer(c6, true, "Teresina");
                Answer a62 = new Answer(c6, false, "Aracajú");
                Answer a63 = new Answer(c6, false, "São Luís");
                Answer a64 = new Answer(c6, false, "Natal");

                answerDAO.create(a61);
                answerDAO.create(a62);
                answerDAO.create(a63);
                answerDAO.create(a64);

                // =========

                Challenge c7 = new Challenge();
                c7.setDescription("Qual a capital do estado de Pernambuco?");
                c7.setLevel(Challenge.EASY);
                c7.setLocation(recife);
                challengeDAO.create(c7);

                Answer a71 = new Answer(c7, false, "Aracajú");
                Answer a72 = new Answer(c7, false, "Maceió");
                Answer a73 = new Answer(c7, true, "Recife");
                Answer a74 = new Answer(c7, false, "Fortaleza");

                answerDAO.create(a71);
                answerDAO.create(a72);
                answerDAO.create(a73);
                answerDAO.create(a74);

                // =========

                Challenge c8 = new Challenge();
                c8.setDescription("Qual a capital do estado do Rio Grande do Norte?");
                c8.setLevel(Challenge.MEDIUM);
                c8.setLocation(natal);
                challengeDAO.create(c8);

                Answer a81 = new Answer(c8, true, "Natal");
                Answer a82 = new Answer(c8, false, "Aracajú");
                Answer a83 = new Answer(c8, false, "João Pessoa");
                Answer a84 = new Answer(c8, false, "Fortaleza");

                answerDAO.create(a81);
                answerDAO.create(a82);
                answerDAO.create(a83);
                answerDAO.create(a84);

                // =========

                Challenge c9 = new Challenge();
                c9.setDescription("Qual a capital do estado de Sergipe?");
                c9.setLevel(Challenge.HARD);
                c9.setLocation(aracaju);
                challengeDAO.create(c9);

                Answer a91 = new Answer(c9, false, "Natal");
                Answer a92 = new Answer(c9, true, "Aracaju");
                Answer a93 = new Answer(c9, false, "Maceió");
                Answer a94 = new Answer(c9, false, "Fortaleza");

                answerDAO.create(a91);
                answerDAO.create(a92);
                answerDAO.create(a93);
                answerDAO.create(a94);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
