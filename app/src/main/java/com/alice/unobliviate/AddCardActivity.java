package com.alice.unobliviate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String question = getIntent().getStringExtra("question");
        String answer = getIntent().getStringExtra("answer");

        ((EditText) findViewById(R.id.questionTextField)).setText(question);
        ((EditText) findViewById(R.id.answerTextField)).setText(answer);

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredQuestion = ((EditText) findViewById(R.id.questionTextField)).getText().toString();
                String enteredAnswer = ((EditText) findViewById(R.id.answerTextField)).getText().toString();
                String enteredWrongAnswer1 = ((EditText) findViewById(R.id.answerTextField2))
                        .getText().toString();
                String enteredWrongAnswer2 = ((EditText) findViewById(R.id.answerTextField3))
                        .getText().toString();

                if (enteredAnswer.isEmpty() || enteredQuestion.isEmpty()) {
                    Toast.makeText(AddCardActivity.this,
                            "Must enter both Question and Answer!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                Intent data = new Intent();
                // Pass relevant data back as a result
                data.putExtra("question", enteredQuestion);
                data.putExtra("answer", enteredAnswer);
                data.putExtra("wronganswer1", enteredWrongAnswer1);
                data.putExtra("wronganswer2", enteredWrongAnswer2);
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes the activity, pass data to parent
            }
        });
    }
}
