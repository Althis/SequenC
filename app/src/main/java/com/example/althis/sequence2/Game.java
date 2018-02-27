package com.example.althis.sequence2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.althis.sequence2.R.id.board;
import static com.example.althis.sequence2.R.id.nextButton;

public class Game extends AppCompatActivity {
    @link #AUTO_HIDE_DELAY_MILLIS
    private static final boolean AUTO_HIDE = true;

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    final World gameBoard = new World();
    boolean tileSelected = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        final Button a1 = (Button) findViewById(R.id.a1);
        final Button a2 = (Button) findViewById(R.id.a2);
        final Button a3 = (Button) findViewById(R.id.a3);
        final Button a4 = (Button) findViewById(R.id.a4);
  
        final Button b1 = (Button) findViewById(R.id.b1);
        final Button b2 = (Button) findViewById(R.id.b2);
        final Button b3 = (Button) findViewById(R.id.b3);
        final Button b4 = (Button) findViewById(R.id.b4);
  
        final Button c1 = (Button) findViewById(R.id.c1);
        final Button c2 = (Button) findViewById(R.id.c2);
        final Button c3 = (Button) findViewById(R.id.c3);
        final Button c4 = (Button) findViewById(R.id.c4);
  
        final Button d1 = (Button) findViewById(R.id.d1);
        final Button d2 = (Button) findViewById(R.id.d2);
        final Button d3 = (Button) findViewById(R.id.d3);
        final Button d4 = (Button) findViewById(R.id.d4);
  
        createOnClickListeners(a1, a2, a3, a4,
                b1, b2, b3, b4,
                c1, c2, c3, c4,
                d1, d2, d3, d4
                );

        updateButton(a1);
        updateButton(a2);
        updateButton(a3);
        updateButton(a4);
  
        updateButton(b1);
        updateButton(b2);
        updateButton(b3);
        updateButton(b4);
  
        updateButton(c1);
        updateButton(c2);
        updateButton(c3);
        updateButton(c4);

        updateButton(d1);
        updateButton(d2);
        updateButton(d3);
        updateButton(d4);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        delayedHide(100);
        updateNext();
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private void createOnClickListeners(final Button a1, final Button a2, final Button a3, final Button a4,
                                        final Button b1, final Button b2, final Button b3, final Button b4,
                                        final Button c1, final Button c2, final Button c3, final Button c4,
                                        final Button d1, final Button d2, final Button d3, final Button d4
                                        ) {
        a1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(0, 0);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);
                        
                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(0,0);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        a2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(0, 1);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(0,1);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        a3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(0, 2);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);
  
                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(0,2);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        a4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(0, 3);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);
  
                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);
  
                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);
  
                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(0,3);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });


        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(1, 0);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(1,0);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(1, 1);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(1,1);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(1, 2);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(1,2);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(1, 3);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(1,3);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });


        c1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(2, 0);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(2,0);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        c2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(2, 1);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(2,1);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        c3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(2, 2);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(2,2);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        c4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(2, 3);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(2,3);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });


        d1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(3, 0);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(3,0);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        d2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(3, 1);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(3,1);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        d3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(3, 2);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(3,2);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

        d4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tileSelected) {
                    int move = gameBoard.move(3, 3);
                    if (move != -1) {
                        tileSelected = false;
                        updateButton(a1);
                        updateButton(a2);
                        updateButton(a3);
                        updateButton(a4);

                        updateButton(b1);
                        updateButton(b2);
                        updateButton(b3);
                        updateButton(b4);

                        updateButton(c1);
                        updateButton(c2);
                        updateButton(c3);
                        updateButton(c4);

                        updateButton(d1);
                        updateButton(d2);
                        updateButton(d3);
                        updateButton(d4);

                        updateNext();
                    }
                }else{
                    Boolean response = gameBoard.select(3,3);
                    if (response){
                        tileSelected = true;
                    }
                }
                if (gameBoard.checkEndGame()) {
                    Button endGame = (Button) findViewById(R.id.end);
                    endGame.setAlpha(1);
                }
            }

        });

    }

    public void updateButton(Button button){
        int value = 0;
        switch (button.getId()){
            case R.id.a1:
                value = gameBoard.getTileAt(0,0);
                break;
            case R.id.a2:
                value = gameBoard.getTileAt(0,1);
                break;
            case R.id.a3:
                value = gameBoard.getTileAt(0,2);
                break;
            case R.id.a4:
                value = gameBoard.getTileAt(0,3);
                break;

            case R.id.b1:
                value = gameBoard.getTileAt(1,0);
                break;
            case R.id.b2:
                value = gameBoard.getTileAt(1,1);
                break;
            case R.id.b3:
                value = gameBoard.getTileAt(1,2);
                break;
            case R.id.b4:
                value = gameBoard.getTileAt(1,3);
                break;

            case R.id.c1:
                value = gameBoard.getTileAt(2,0);
                break;
            case R.id.c2:
                value = gameBoard.getTileAt(2,1);
                break;
            case R.id.c3:
                value = gameBoard.getTileAt(2,2);
                break;
            case R.id.c4:
                value = gameBoard.getTileAt(2,3);
                break;

            case R.id.d1:
                value = gameBoard.getTileAt(3,0);
                break;
            case R.id.d2:
                value = gameBoard.getTileAt(3,1);
                break;
            case R.id.d3:
                value = gameBoard.getTileAt(3,2);
                break;
            case R.id.d4:
                value = gameBoard.getTileAt(3,3);
                break;
        }


        if(value == 0){
            button.setBackgroundTintList(this.getResources().getColorStateList(R.color.mycolors));
            button.setText("");
        }else{
            button.setBackgroundTintList(this.getResources().getColorStateList(R.color.buttoncolor));
            button.setText(""+value);
            button.setTextColor(this.getResources().getColorStateList(R.color.whites));
            button.setTextSize(32);
        }
    }

    public void updateNext(){
        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setText(""+gameBoard.getNext());
    }

}
