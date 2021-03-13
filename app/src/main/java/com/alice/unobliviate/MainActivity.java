package com.alice.unobliviate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_CARD_REQUEST_CODE = 20;
    private TextView questionSideView;
    private TextView answerSideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionSideView = findViewById(R.id.flashcard_question);
        answerSideView = findViewById(R.id.flashcard_answer);

        questionSideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionSideView.setVisibility(View.GONE);
                answerSideView.setVisibility(View.VISIBLE);
                resetBtnColors();
            }
        });

        answerSideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionSideView.setVisibility(View.VISIBLE);
                answerSideView.setVisibility(View.GONE);
                resetBtnColors();
            }
        });

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(myIntent, ADD_CARD_REQUEST_CODE);
            }
        });

        findViewById(R.id.editBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("question", questionSideView.getText().toString());
                intent.putExtra("answer", answerSideView.getText().toString());
                MainActivity.this.startActivityForResult(intent, ADD_CARD_REQUEST_CODE);
            }
        });

    }

    void resetBtnColors() {
        List<Integer> choiceList = Arrays.asList(R.id.answer_choice_1, R.id.answer_choice_2, R.id.answer_choice_3);
        for (int id : choiceList) {
            TextView choiceBtn = (TextView) findViewById(id);
            choiceBtn.setBackgroundColor(getColor(R.color.colorPrimary));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_CARD_REQUEST_CODE && resultCode == RESULT_OK) {
            Snackbar.make(questionSideView,
                    "Card successfully created",
                    Snackbar.LENGTH_SHORT)
                    .show();

            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");

            String wrongAnswer1 = data.getExtras().getString("wronganswer1");
            String wrongAnswer2 = data.getExtras().getString("wronganswer2");

            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);

            ((TextView) findViewById(R.id.answer_choice_1)).setText(wrongAnswer1);
            ((TextView) findViewById(R.id.answer_choice_2)).setText(wrongAnswer2);
            ((TextView) findViewById(R.id.answer_choice_3)).setText(answer);

        }
    }
}
