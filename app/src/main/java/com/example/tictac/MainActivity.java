package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int active=0;
    int[][] winningpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] gameState={2,2,2,2,2,2,2,2,2};
    boolean gameActive=true;
    TextView winnertext;
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedcounter]==2 && gameActive) {
            gameState[tappedcounter] = active;
            counter.setTranslationY(-1500);
            if (active == 0) {
                counter.setImageResource(R.drawable.cross);
                counter.animate().translationYBy(1500).setDuration(300);
                active = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                counter.animate().translationYBy(1500).setDuration(300);
                active = 0;
            }
            for (int[] winingpos : winningpos) {
                if (gameState[winingpos[0]] == gameState[winingpos[1]] && gameState[winingpos[1]] == gameState[winingpos[2]] && gameState[winingpos[0]] != 2) {
                    gameActive=false;
                    String winner="";
                    if(active==0){
                        winner="Circle";
                    }
                    if(active==1){
                        winner="Cross";
                    }
                    Button playButton=(Button)findViewById(R.id.button);
                    winnertext=(TextView)findViewById(R.id.textView);
                    winnertext.setText(winner+" Won !");
                    playButton.setVisibility(View.VISIBLE);


                    Toast.makeText(this, "Someone has Won !!", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }
    public void playagain(View view){

        Button playButton=(Button)findViewById(R.id.button);

        playButton.setVisibility(view.INVISIBLE);
        winnertext.setVisibility(View.INVISIBLE);
        GridLayout grid=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<grid.getChildCount(); i++) {
            ImageView counter = (ImageView) grid.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        active=0;
        gameActive=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
