package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //object declaration
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnAdd, btnSub, btnMul, btnDiv, btnDec, btnCle, btnEqu, btnNeg;
    TextView display;
    StringBuilder a = new StringBuilder();
    StringBuilder b = new StringBuilder();
    int operation = 0;
    StringBuilder answer = new StringBuilder();
    boolean isPosA = true;
    boolean isPosB = true;
    boolean isPosAns = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //object definitions
        btn0 = findViewById(R.id.zero);
        btn1 = findViewById(R.id.one);
        btn2 = findViewById(R.id.two);
        btn3 = findViewById(R.id.three);
        btn4 = findViewById(R.id.four);
        btn5 = findViewById(R.id.five);
        btn6 = findViewById(R.id.six);
        btn7 = findViewById(R.id.seven);
        btn8 = findViewById(R.id.eight);
        btn9 = findViewById(R.id.nine);
        btnAdd = findViewById(R.id.addition);
        btnSub = findViewById(R.id.subtract);
        btnMul = findViewById(R.id.multiply);
        btnDiv = findViewById(R.id.divide);
        btnDec = findViewById(R.id.decimal);
        btnCle = findViewById(R.id.clear);
        btnEqu = findViewById(R.id.equals);
        btnNeg = findViewById(R.id.negative);
        display = findViewById(R.id.display);

        //on click listeners
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnDec.setOnClickListener(this);
        btnEqu.setOnClickListener(this);
        btnCle.setOnClickListener(this);
        btnNeg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //get ready for next operation
        if (operation != 0 && (v.getId() == R.id.addition || v.getId() == R.id.subtract
                || v.getId() == R.id.multiply || v.getId() == R.id.divide || v.getId() == R.id.subtract)) {
            a.setLength(0);
            a.append(answer);
            b.setLength(0);
            answer.setLength(0);
            isPosA = true;
            isPosB = true;
            isPosAns = true;
        }

        else if (answer.length() != 0 && (v.getId() == R.id.zero || v.getId() == R.id.one ||
                v.getId() == R.id.two || v.getId() == R.id.three || v.getId() == R.id.four ||
                v.getId() == R.id.five || v.getId() == R.id.six || v.getId() == R.id.seven ||
                v.getId() == R.id.eight || v.getId() == R.id.nine || v.getId() == R.id.decimal)) {
            operation = 0;
            answer.setLength(0);
            a.setLength(0);
            b.setLength(0);
        }
        //determine the two numbers needed for the arithmetic
        if (operation == 0) {
            a.append(getNum(v));
            display.setText(a);
            if (v.getId() == R.id.negative && display.getText().length() != 0) {
                if (isPosA) {
                    a.insert(0, "-");
                    isPosA = false;
                } else {
                    a.delete(0, 1);
                    isPosA = true;
                }
                display.setText(a);
            }
        } else {
            b.append(getNum(v));
            display.setText(b);
            if (v.getId() == R.id.negative && display.getText().length() != 0) {
                if (isPosB) {
                    b.insert(0, "-");
                    isPosB = false;
                } else {
                    b.delete(0, 1);
                    isPosB = true;
                }
                display.setText(b);
            }
        }

        //determine operation and clear display
        getOperation(v);

        //calculate and display
        if (v.getId() == R.id.equals) {
            answer.append(calculate(operation));
            display.setText(answer);
            a.setLength(0);
            b.setLength(0);
            try {
                isPositive(Double.parseDouble(answer.toString()));
            } catch (NumberFormatException e) {
                display.setText("Error");
            }
        }
    //clear
    clear(v);

    }
    public void isPositive (double ans){
        isPosAns = (ans >= 0);
    }
    public String calculate (int operationType) {
        double answerD = 0.0;
        try {
            if (operationType == 1)
                answerD = (Double.parseDouble(a.toString())) + (Double.parseDouble(b.toString()));
            if (operationType == 2)
                answerD = (Double.parseDouble(a.toString())) - (Double.parseDouble(b.toString()));
            if (operationType == 3)
                answerD = (Double.parseDouble(a.toString())) * (Double.parseDouble(b.toString()));
            if (operationType == 4)
                answerD = (Double.parseDouble(a.toString())) / (Double.parseDouble(b.toString()));

            //format answer
            if (answerD % 1 == 0)
                return Integer.toString((int) answerD);
            else
                return Double.toString(answerD);
        } catch (NumberFormatException e) {
            return "Error";
        }
    }
    public void clear (View v) {
        if (v.getId() == R.id.clear) {
            display.setText("");
            answer.setLength(0);
            operation = 0;
            a.setLength(0);
            b.setLength(0);
            isPosA = true;
            isPosB = true;
            isPosAns = true;
        }
    }

    public StringBuilder getNum(View v){
        StringBuilder filler = new StringBuilder();
        if (v.getId() == R.id.zero)
            filler.append("0");
        if (v.getId() == R.id.one)
            filler.append("1");
        if (v.getId() == R.id.two)
            filler.append("2");
        if (v.getId() == R.id.three)
            filler.append("3");
        if (v.getId() == R.id.four)
            filler.append("4");
        if (v.getId() == R.id.five)
            filler.append("5");
        if (v.getId() == R.id.six)
            filler.append("6");
        if (v.getId() == R.id.seven)
            filler.append("7");
        if (v.getId() == R.id.eight)
            filler.append("8");
        if (v.getId() == R.id.nine)
            filler.append("9");
        if (v.getId() == R.id.decimal)
            filler.append(".");
        return filler;
    }

    public void getOperation (View v){
        if (v.getId() == R.id.addition) {
            operation = 1;
            display.setText("");
        }
        else if (v.getId() == R.id.subtract) {
            operation = 2;
            display.setText("");
        }
        else if (v.getId() == R.id.multiply) {
            operation = 3;
            display.setText("");
        }
        else if (v.getId() == R.id.divide) {
            operation = 4;
            display.setText("");
        }
    }
}