package br.ufpb.ccae.dcx.lcc.tcc.droid.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.ccae.dcx.lcc.tcc.droid.R;
import br.ufpb.ccae.dcx.lcc.tcc.droid.adapt.BatteryAdaptation;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Answer;
import br.ufpb.ccae.dcx.lcc.tcc.droid.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.droid.persistence.LocalDatabaseFacade;


public class ChallengeActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;

    private Challenge mChallenge;
    private TextView mChallengeTextView;
    private RadioButton mRadioButtonA;
    private RadioButton mRadioButtonB;
    private RadioButton mRadioButtonC;
    private RadioButton mRadioButtonD;
    private Button mOkButton;
    private List<Answer> mAnswers = new ArrayList<>();
    private BatteryAdaptation batteryAdaptation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        batteryAdaptation = new BatteryAdaptation();
        this.registerReceiver(batteryAdaptation, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        setContentView(R.layout.activity_challenge);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_arrow_back_white);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mChallenge = (Challenge) getIntent().getSerializableExtra("CHALLENGE");
//        mAnswers = LocalDatabaseFacade.getInstance(this).getAnswersFromChallenge(mChallenge);
        mAnswers.clear();
        mAnswers.addAll(mChallenge.getAnswers());

        mChallengeTextView = (TextView) findViewById(R.id.challenge);
        mChallengeTextView.setText(mChallenge.getDescription());

        mRadioButtonA = (RadioButton) findViewById(R.id.radioButtonA);
        mRadioButtonA.setText(mAnswers.get(0).getDescription());

        mRadioButtonB = (RadioButton) findViewById(R.id.radioButtonB);
        mRadioButtonB.setText(mAnswers.get(1).getDescription());

        mRadioButtonC = (RadioButton) findViewById(R.id.radioButtonC);
        mRadioButtonC.setText(mAnswers.get(2).getDescription());

        mRadioButtonD = (RadioButton) findViewById(R.id.radioButtonD);
        mRadioButtonD.setText(mAnswers.get(3).getDescription());

        mOkButton = (Button) findViewById(R.id.ok_button);

        final MaterialDialog mMaterialDialogCorrect = new MaterialDialog.Builder(this)
                .title(R.string.correct)
                .content(R.string.correct_content)
                .positiveText(R.string.challenge_continue)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                    }
                })
                .build();

        final MaterialDialog mMaterialDialogIncorrect = new MaterialDialog.Builder(this)
                .title(R.string.incorrect)
                .content(R.string.incorrect_content)
                .positiveText(R.string.challenge_retry)
                .build();

        mOkButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mRadioButtonA.isChecked()) {

                    if (mAnswers.get(0).isCorrect()) {
                        mMaterialDialogCorrect.show();
                    } else {
                        mMaterialDialogIncorrect.show();
                        resetRadioButtons();
                    }

                } else if (mRadioButtonB.isChecked()) {

                    if (mAnswers.get(1).isCorrect()) {
                        mMaterialDialogCorrect.show();
                    } else {
                        mMaterialDialogIncorrect.show();
                        resetRadioButtons();
                    }

                } else if (mRadioButtonC.isChecked()) {

                    if (mAnswers.get(2).isCorrect()) {
                        mMaterialDialogCorrect.show();
                    } else {
                        mMaterialDialogIncorrect.show();
                        resetRadioButtons();
                    }

                } else if (mRadioButtonD.isChecked()) {

                    if (mAnswers.get(3).isCorrect()) {
                        mMaterialDialogCorrect.show();
                    } else {
                        mMaterialDialogIncorrect.show();
                        resetRadioButtons();
                    }

                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(batteryAdaptation);
    }

    public void resetRadioButtons() {

        if(mRadioButtonA != null) mRadioButtonA.setChecked(false);
        if(mRadioButtonB != null) mRadioButtonB.setChecked(false);
        if(mRadioButtonC != null) mRadioButtonC.setChecked(false);
        if(mRadioButtonD != null) mRadioButtonD.setChecked(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }



}
