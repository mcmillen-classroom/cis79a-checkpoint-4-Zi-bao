package Zihao.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private LinearLayout mTrueFalseContainer;
    private LinearLayout mFillTheBlankContainer;
    private EditText mEditText;
    private Button mCheckButton;
    private TextView mScoreTextView;
    private Button mTrueButton;
    private Button mFalsebutton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private Button mHintButton;
    private Button mResetButton;


    private Question[] mQuestions;
    private int mIndex;
    private int mScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalsebutton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mTrueFalseContainer = (LinearLayout) findViewById(R.id.true_false_container);
        mFillTheBlankContainer = (LinearLayout) findViewById(R.id.fill_the_blank_container);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mHintButton = (Button) findViewById(R.id.hint_button);
        mResetButton = (Button) findViewById(R.id.reset_button);


        mTrueButton.setOnClickListener(this);
        mFalsebutton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mPrevButton.setOnClickListener(this);
        mHintButton.setOnClickListener(this);
        mResetButton.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.text_view);
        mScoreTextView = (TextView) findViewById(R.id.score_text_view);




        mQuestions = new Question[5];
        mIndex = 0;
        mScore = 0;


        mQuestions[0] = new TrueFalseQuestion(R.string.question_1, true,R.string.hint_1);
        mQuestions[1] = new TrueFalseQuestion(R.string.question_2, false,R.string.hint_2);
        mQuestions[2] = new TrueFalseQuestion(R.string.question_3, true,R.string.hint_3);

        String[] q4Answers =getResources().getStringArray(R.array.question_4_answers);
        mQuestions[3] = new FillTheBlankQuestion(R.string.question_4, R.string.hint_4, q4Answers);
        mQuestions[4] = new TrueFalseQuestion(R.string.question_5, false,R.string.hint_5);



        mScoreTextView.setText("Score: "+ mScore);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.true_button) {
            checkAnswer(true);




        }

        else if (view.getId() == R.id.false_button ) {
            checkAnswer(false);

        }
        else if (view.getId()==R.id.check_button)
        {
            checkAnswer(mEditText.getText().toString());
        }

        else if(view.getId() == R.id.prev_button){
            mIndex--;



        }

        else if (view.getId() == R.id.next_button) {

            mIndex++;
            mFalsebutton.setEnabled(true);
            mTrueButton.setEnabled(true);


            setupQuestion();




        }
        else if (view.getId() == R.id.hint_button){
            Toast myToast2 = Toast.makeText(this,mQuestions[mIndex].getmHintTextResId(), Toast.LENGTH_LONG);
            myToast2.show();
        }
        else if(view.getId() == R.id.reset_button){
            mScore = 0;
            mScoreTextView.setText("Score: "+ mScore);
        }
        if(mIndex>mQuestions.length-1 || mIndex<0){
            mIndex = 0;

        }



        mTextView.setText(mQuestions[mIndex].getTextResId());


    }

    public void setupQuestion()
    {
        mTextView.setText(mQuestions[mIndex].getTextResId());

        if(mQuestions[mIndex].isTrueFalseQuestion())
        {
            mTrueFalseContainer.setVisibility(View.VISIBLE);
            mFillTheBlankContainer.setVisibility(View.GONE);
        }
        else if(mQuestions[mIndex].isFillTheBlankQuestion())
        {
            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankContainer.setVisibility(View.VISIBLE);
        }
    }
    public boolean checkAnswer(boolean userInput){
        if(mQuestions[mIndex].checkAnswer (userInput)){
            mFalsebutton.setEnabled(false);
            mTrueButton.setEnabled(false);
            mScore++;
            mScoreTextView.setText("Score: "+ mScore);



        }
        else{
            mFalsebutton.setEnabled(false);
            mTrueButton.setEnabled(false);
            mScore--;
            mScoreTextView.setText("Score: "+ mScore);
            Toast myToast = Toast.makeText(this, "You are incorrect", Toast.LENGTH_SHORT);
            myToast.show();
            return false;

        }


    }
    public boolean checkAnswer(String userInpuut)
    {

    }

}