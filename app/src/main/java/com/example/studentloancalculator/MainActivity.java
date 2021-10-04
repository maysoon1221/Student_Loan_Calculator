package com.example.studentloancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mSubmitButton;
    private double studentloan;
    private double fees;
    private double years;
    private double rate;
    private EditText loaninput;
    private EditText feeinput;
    private EditText yearsinput;
    private EditText rateinput;
    private double remainingbalance;
    private double payment;

    private TextView mMonthlyAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loaninput = (EditText) findViewById(R.id.StudentLoanAnswer);
        feeinput = (EditText) findViewById(R.id.FeeAmountAnswer);
        yearsinput = (EditText) findViewById(R.id.YearsNeededAnswers);
        rateinput = (EditText) findViewById(R.id.RateAmountAnswer);


        //button area
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentloan = Integer.parseInt(loaninput.getText().toString());
                fees = Integer.parseInt(feeinput.getText().toString());
                years = Integer.parseInt(yearsinput.getText().toString());
                rate = (Integer.parseInt(rateinput.getText().toString())) * (years * 12);
                MonthlyPayment();
                totalinterest();

            }
        });

    }

    public void MonthlyPayment(){
        rate = rate / 100;
        int n = (int) (years*12);
        payment = (double)(studentloan * (((rate * Math.pow(1+rate ,n))/(Math.pow(1+rate, n) -1))));
    }

    public void totalinterest(){
        remainingbalance = studentloan;
        double interestpaidsofar = 0;
        double interestpaidthatmonth;
        for(int i = (int)(years*12); i>=0; i--){
            interestpaidthatmonth = ((rate / (years*12)) * remainingbalance);
            interestpaidsofar += interestpaidthatmonth;
            remainingbalance -= interestpaidthatmonth;
        }
        mMonthlyAnswer.setText(String.format("The Monthly Payment is: %s", payment
                + "The Total interest paid is: %s", interestpaidsofar));
    }

}