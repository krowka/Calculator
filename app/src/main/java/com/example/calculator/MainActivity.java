package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO Add buttons: modulo, power and ANS
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnAdd, btnSub, btnMul, btnDiv, btnEq, btnDot,
            btnLeftBracket, btnRightBracket, btnClear, btnDel;
//    EditText edittText;
    TextView textView;
    Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        btnAdd = findViewById(R.id.add);
        btnSub = findViewById(R.id.sub);
        btnMul = findViewById(R.id.mul);
        btnDiv = findViewById(R.id.div);
        btnEq = findViewById(R.id.equal);
        btnDot = findViewById(R.id.dot);
        btnLeftBracket = findViewById(R.id.leftBracket);
        btnRightBracket = findViewById(R.id.rightBracket);
        btnClear = findViewById(R.id.clearAll);
        btnDel = findViewById(R.id.clearOne);
//        edittText = findViewById(R.id.editTextt);
        textView = findViewById(R.id.textView);

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
        btnEq.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnLeftBracket.setOnClickListener(this);
        btnRightBracket.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnDel.setOnClickListener(this);

    }

    boolean afterEqual = false;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.equal:
                String expression = textView.getText().toString();
                String result = "";
                try {
                    result = calculator.evaluate(expression);
                } catch (Exception e) {
                    result = "BAD EXPRESSION";
                }
                textView.setText(result);
                afterEqual = true;
                break;
            case R.id.clearAll:
                textView.setText("");
                break;
            case R.id.clearOne:
                String text = textView.getText().toString();
                int length = text.length();
                if(length > 0)
                    textView.setText(text.substring(0, length - 1));
                break;
                default:
                    if(afterEqual){
                        textView.setText("");
                        afterEqual = false;
                    }
                    textView.append(((Button) view).getText().toString());

        }
    }
}
