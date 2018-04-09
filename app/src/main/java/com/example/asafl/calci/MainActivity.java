package com.example.asafl.calci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected String calculateForm;
    TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = (TextView) findViewById(R.id.textView);
        calculateForm = new String();
    }
    public void butNum(android.view.View v)
    {
        if((calculateForm==null||calculateForm=="")&&num1sing2dot3(((Button)v).getText().toString())!=1)return;
        else if(num1sing2dot3(((Button)v).getText().toString())== 1){
            calculateForm += ((Button)v).getText().toString();
            txtView.setText(calculateForm);
        }
        else if(num1sing2dot3(((Button)v).getText().toString())== 2){
            if(num1sing2dot3(calculateForm.substring(calculateForm.length() - 1))!=1)return;
             else{
                 calculateForm+=((Button)v).getText().toString();
                txtView.setText(calculateForm);
            }

        }
        else if(num1sing2dot3(((Button)v).getText().toString())== 3){
            if(num1sing2dot3(calculateForm.substring(calculateForm.length() - 1))!=1&&!calculateForm.contains("."))return;
            calculateForm+=((Button)v).getText().toString();
            txtView.setText(calculateForm);
        }




    }
    private int num1sing2dot3(String s){
        switch(s){
            case "+": case "-": case "*": case "/":
               return 2;
            case ".":
                return 3;

        }
        return 1;
    }
    public void cal(android.view.View v){

        if(num1sing2dot3(calculateForm.substring(calculateForm.length() - 1))!=1)return;
        txtView.setText(String.valueOf(eval(txtView.getText().toString())));
    }
    public void del(android.view.View v){
        calculateForm = calculateForm.substring(0,calculateForm.length()-2);
        txtView.setText(calculateForm);
    }
    public void cln(android.view.View v){
        txtView.setText("");
    }
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }




}
