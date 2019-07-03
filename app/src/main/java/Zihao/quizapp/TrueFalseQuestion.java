package Zihao.quizapp;

public class TrueFalseQuestion extends Question {
    private boolean mAnswer;

    public TrueFalseQuestion(int textResId, boolean answer, int hintTextResId)
    {
        super(textResId, answer, hintTextResId);
        mAnswer = answer;
    }

    @Override
    public boolean checkAnswer(boolean ans)
    {
        return mAnswer == ans;
    }
}
