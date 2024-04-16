package com.example.formula_abc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class AnswersDodge extends AppCompatActivity {
    int a = 0, b = 0, c = 0;
    int answers = 0;

    WebView wV;
    TextView tV;

   
    String finalAnswer;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers_dodge);

        intent = getIntent();

        wV = findViewById(R.id.wV);

        wV.getSettings().setJavaScriptEnabled(true);
        wV.setWebViewClient(new MyWebViewClient());
        wV.getSettings().setUseWideViewPort(true);
        wV.getSettings().setLoadWithOverviewMode(true);

        tV = findViewById(R.id.ansDodge1);

        a = intent.getIntExtra("first", 0);
        b = intent.getIntExtra("second", 0);
        c = intent.getIntExtra("third", 0);

        String url = "https://mathforyou.net/en/online/equation/arbitrary/?e0=" + a + "x%5E2%2B" + b + "*x%2B" + c + "&v0=x&o0=1&from=google";
        wV.loadUrl(url);


        double underShorsh = b * b - 4 * a * c;

        if (underShorsh < 0) 
        {
            finalAnswer = "no answers";
        } 
        else if (underShorsh == 0) 
        {
            double answer = -b / (2 * a);
            finalAnswer = "only one answer: " + answer;
        } 
        else 
        {
            double answer1 = (-b + Math.sqrt(underShorsh)) / (2 * a);
            double answer2 = (-b - Math.sqrt(underShorsh)) / (2 * a);
            finalAnswer = "first answer is: " + answer1 + "\nthe second answer is: " + answer2;
        }
        
        tV.setText(finalAnswer);
    }


    public void goback(View view) {

        intent.putExtra("answers", finalAnswer);
        setResult(RESULT_OK, intent);

        finish();
    }


    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }
}