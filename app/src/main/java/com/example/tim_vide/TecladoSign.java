package com.example.tim_vide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TecladoSign extends AppCompatActivity {
    private StringBuilder inputStringBuilder;
    private Button btnD1, btnD2, btnD3, btnD4, btnD5, btnD6, borrar;
    private EditText editText;
    private Vibrator vibrator;

    private boolean btnD1Pressed = false;
    private boolean btnD2Pressed = false;
    private boolean btnD3Pressed = false;
    private boolean btnD4Pressed = false;
    private boolean btnD5Pressed = false;
    private boolean btnD6Pressed = false;
    private boolean combinationPressed = false;

    private Handler handler;
    private Runnable runnable;
    private String letra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado_sign);


        inputStringBuilder = new StringBuilder();
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        btnD1 = findViewById(R.id.btn_d1);
        btnD2 = findViewById(R.id.btn_d2);
        btnD3 = findViewById(R.id.btn_d3);
        btnD4 = findViewById(R.id.btn_d4);
        btnD5 = findViewById(R.id.btn_d5);
        btnD6 = findViewById(R.id.btn_d6);

        editText = findViewById(R.id.escribirP);
        btnD1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnD1Pressed = true;
                    checkCombinationPressed();
                    startTimer();
                    vibrate();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnD1Pressed = false;
                    checkCombinationPressed();
                    stopTimer();
                }
                return true;
            }
        });

        btnD2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnD2Pressed = true;
                    checkCombinationPressed();
                    startTimer();
                    vibrate();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnD2Pressed = false;
                    checkCombinationPressed();
                    stopTimer();
                }
                return true;
            }
        });
        btnD3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnD3Pressed = true;
                    checkCombinationPressed();
                    startTimer();
                    vibrate();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnD3Pressed = false;
                    checkCombinationPressed();
                    stopTimer();
                }
                return true;
            }
        });
        btnD4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnD4Pressed = true;
                    checkCombinationPressed();
                    startTimer();
                    vibrate();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnD4Pressed = false;
                    checkCombinationPressed();
                    stopTimer();
                }
                return true;
            }
        });
        btnD5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnD5Pressed = true;
                    checkCombinationPressed();
                    startTimer();
                    vibrate();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnD5Pressed = false;
                    checkCombinationPressed();
                    stopTimer();
                }
                return true;
            }
        });
        btnD6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnD6Pressed = true;
                    checkCombinationPressed();
                    startTimer();
                    vibrate();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnD6Pressed = false;
                    checkCombinationPressed();
                    stopTimer();
                }
                return true;
            }
        });

    }
    private void vibrate() {
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(50);
        }
    }

    private void checkCombinationPressed() {
        if(!btnD1Pressed && btnD2Pressed  && !btnD3Pressed && !btnD4Pressed && btnD5Pressed && btnD6Pressed){
            combinationPressed = true;
            letra = ".";
        } else if(!btnD1Pressed && btnD2Pressed  && !btnD3Pressed && !btnD4Pressed && !btnD5Pressed && !btnD6Pressed){
            combinationPressed = true;
            letra = ",";
        } else if(!btnD1Pressed && btnD2Pressed  && btnD3Pressed && !btnD4Pressed && !btnD5Pressed && !btnD6Pressed){
            combinationPressed = true;
            letra = ";";
        }else if(!btnD1Pressed && btnD2Pressed  && !btnD3Pressed && !btnD4Pressed && btnD5Pressed && !btnD6Pressed){
            combinationPressed = true;
            letra = ":";
        }else if(!btnD1Pressed && !btnD2Pressed  && btnD3Pressed && btnD4Pressed && !btnD5Pressed && !btnD6Pressed){
            combinationPressed = true;
            letra = "/";
        }else if(!btnD1Pressed && btnD2Pressed  && btnD3Pressed && !btnD4Pressed && !btnD5Pressed && btnD6Pressed){
            combinationPressed = true;
            letra = "?";
        }else if(!btnD1Pressed && btnD2Pressed  && btnD3Pressed && !btnD4Pressed && btnD5Pressed && !btnD6Pressed){
            combinationPressed = true;
            letra = "!";
        }else if(!btnD1Pressed && !btnD2Pressed  && btnD3Pressed && btnD4Pressed && btnD5Pressed && !btnD6Pressed){
            combinationPressed = true;
            letra = "@";
        }else if(!btnD1Pressed && !btnD2Pressed  && btnD3Pressed && btnD4Pressed && btnD5Pressed && btnD6Pressed){
            combinationPressed = true;
            letra = "#";
        }else if(!btnD1Pressed && btnD2Pressed  && btnD3Pressed && !btnD4Pressed && btnD5Pressed && !btnD6Pressed){
            combinationPressed = true;
            letra = "+";
        }else if(!btnD1Pressed && btnD2Pressed  && !btnD3Pressed && !btnD4Pressed && btnD5Pressed && !btnD6Pressed){
            combinationPressed = true;
            letra = "-";
        }else if(!btnD1Pressed && !btnD2Pressed  && btnD3Pressed && !btnD4Pressed && btnD5Pressed && !btnD6Pressed){
            combinationPressed = true;
            letra = "*";
        }else if(!btnD1Pressed && btnD2Pressed  && btnD3Pressed && !btnD4Pressed && !btnD5Pressed && btnD6Pressed){
            combinationPressed = true;
            letra = "‟";
        }else if(!btnD1Pressed && !btnD2Pressed  && btnD3Pressed && !btnD4Pressed && btnD5Pressed && btnD6Pressed){
            combinationPressed = true;
            letra = "”";
        }else if(btnD1Pressed && btnD2Pressed  && !btnD3Pressed && !btnD4Pressed && !btnD5Pressed && btnD6Pressed){
            combinationPressed = true;
            letra = "(";
        }else if(!btnD1Pressed && !btnD2Pressed  && btnD3Pressed && btnD4Pressed && btnD5Pressed && !btnD6Pressed){
            combinationPressed = true;
            letra = ")";
        }else {
            combinationPressed = false;
        }
    }
    private void startTimer() {
        if (handler == null) {
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (combinationPressed) {
                        inputStringBuilder.append(letra);
                        updateEditText();
                        vibrate();
                    }
                    stopTimer();
                }
            };
            handler.postDelayed(runnable, 500);
        }
    }


    private void stopTimer() {
        if (handler != null) {
            handler.removeCallbacks(runnable);
            handler = null;
            runnable = null;
        }
    }

    private void updateEditText() {
        editText.setText(inputStringBuilder.toString());
        editText.setSelection(inputStringBuilder.length());
    }
}