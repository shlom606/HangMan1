package com.example.hangman1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hangman1.R;


public class MainActivity extends AppCompatActivity {
    ImageView iv; LinearLayout main;int n=0,lengh=0;
    EditText ans;TextView words,error;
    String txt="",input,showtext="",firsttxt,Theword="robert",showWrong="",empty="------",allwrong="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=new ImageView(this);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(100,100);
        iv.setLayoutParams(layoutParams);
        main= findViewById(R.id.l1);
        main.addView(iv);
        Loadpics1(n);
        ans=findViewById(R.id.answer);
        words=findViewById(R.id.words);
        error=findViewById(R.id.wrong);
        ans.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                input=ans.getText().toString();
                firsttxt=txt;
                txt=txt+input;
                if(n<6&&TheCorrectLetter(txt)==false&&NotTwice(showWrong)==true){
                    n++;
                    int imageKey=getResources().getIdentifier("hangman"+n,"drawable",getPackageName());
                    iv.setImageResource(imageKey);
                    showWrong+=input;
                    error.setText(showWrong);
                    ans.getText().clear();

                }
                if(n<6&&TheCorrectLetter(txt)==false&&NotTwice(showWrong)==false){
                    ans.getText().clear();
                }
                if(TheCorrectLetter(txt)){
                    showtext+=input;
                    words.setText(Print(Theword,showtext.charAt(showtext.length()-1),empty));
                    empty=Print(Theword,showtext.charAt(showtext.length()-1),empty);
                    if(empty.equals(Theword)){
                        words.setText(empty+"  good job this is the word");
                        n=7;//if he was right the game ends
                    }
                    ans.getText().clear();
                }

            }
        });

    }
    public boolean TheCorrectLetter(String Letters){
        String answer=Theword;
        if(Letters.length()>0){
            for (int i = 0; i < answer.length(); i++) {
                if(Letters.charAt(Letters.length()-1)==answer.charAt(i))
                    return true;
            }
            return false;
        }
        return false;
    }
    public boolean NotTwice(String b){
        String a=txt;
        if(a.length()>0){
            for (int i = 0; i < b.length(); i++) {
                if(a.charAt(a.length()-1)==b.charAt(i))
                    return false;
            }
            return true;
        }
        return true;

    }
    public String Print(String a,char b,String c){

            for (int j = 0; j < a.length(); j++) {
                if(b==a.charAt(j)){
                    c=c.substring(0,j)+a.charAt(j)+c.substring(j+1);
                }
            }
        return c;
    }


    public void Loadpics1(int n){

        iv=new ImageView(this);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(1000,1000);
        layoutParams.setMargins(20,20,20,20);
        iv.setLayoutParams(layoutParams);
        int imageKey=getResources().getIdentifier("hangman"+n,"drawable",getPackageName());
        iv.setImageResource(imageKey);
        main.addView(iv);

    }

}