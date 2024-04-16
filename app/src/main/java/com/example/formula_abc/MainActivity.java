package com.example.formula_abc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rnd = new Random();
    EditText aPar,bPar,cPar;
    String answers;
    TextView tV;
    final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aPar = findViewById(R.id.aDodge);
        bPar = findViewById(R.id.bDodge);
        cPar = findViewById(R.id.cDodge);

        tV = findViewById(R.id.answersDodge);

    }

    public void randomDodge(View view)
    {
        int a = rnd.nextInt(10) + 1;
        int b = rnd.nextInt(10) + 1;
        int c = rnd.nextInt(10) + 1;

        aPar.setText(a + "");
        bPar.setText(b + "");
        cPar.setText(c + "");
    }


    public boolean canAct(String st)
    {
        if (st.isEmpty() || st.equals("-") || st.equals(".") || st.equals("+") || st.equals("-.") || st.equals("+.") )
        {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    public void ansDodge(View view)
    {
        String firstPar = aPar.getText().toString();
        String secPar = bPar.getText().toString();
        String thirdPar = cPar.getText().toString();

        if (canAct(firstPar) && canAct(secPar) && canAct(thirdPar)) {
            int a = Integer.parseInt(firstPar);
            if (a == 0)
            {
                Toast.makeText(this, "cant put 0 there lil bro", Toast.LENGTH_LONG).show();
            }
            int b = Integer.parseInt(secPar);
            int c = Integer.parseInt(thirdPar);

            Intent intent = new Intent(this, AnswersDodge.class);
            intent.putExtra("first", a);
            intent.putExtra("second", b);
            intent.putExtra("third", c);

            startActivityForResult(intent,1234);
        }
    }

    protected void onActivityResult(int source, int result, @Nullable Intent data_back)
    {
        super.onActivityResult(source, result, data_back);
        if (data_back != null)
        {
            String answer = data_back.getStringExtra("answers");
            tV.setText(answer);
        }

    }

}