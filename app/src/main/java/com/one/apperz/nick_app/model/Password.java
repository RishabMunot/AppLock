package com.one.apperz.nick_app.model;

import android.content.Context;

import io.paperdb.Paper;

public class Password {

    public String STATUS_FIRST_STEP = "Draw an unlock patter";
    public String STATUS_NEXT_STEP = "Draw a pattern to confirm";
    public String STATUS_PASSWORD_CORRECT = "Pattern Set";
    public String STATUS_PASSWORD_INCORRECT = "Try again";
    public String SEMMA_FAILED = "Connect atleast 4 dots";
    public boolean isFirstStep = true;
    private String PASSWORD_KEY = "PASSWORG_KEY";

    public Password(Context context) {
        Paper.init(context);
    }

    public String getPassword() {
        return Paper.book().read(PASSWORD_KEY);
    }

    public void setPassword(String pwd) {
        Paper.book().write(PASSWORD_KEY, pwd);
    }

    public boolean isFirstStep() {
        return isFirstStep;
    }

    public void setFirstStep(boolean firstStep) {
        isFirstStep = firstStep;
    }

    public boolean isCorrect(String pwd) {
        return pwd.equals(getPassword());
    }
}
