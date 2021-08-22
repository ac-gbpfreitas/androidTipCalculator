package com.example.tipcalculatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener,
        View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    EditText userAmount;
    Button btnPlus, btnMinus;
    TextView tipText, tipResult, totalText;
    Double tipPercent[] = {
            0.0,
            10.0,
            15.0,
            18.0,
            20.0,
            25.0
    };
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    NumberFormat percent = NumberFormat.getPercentInstance();
    int defaultTip;
    private final String BILL_AMOUNT = "billamount";
    private final String TIP = "tip";
    private final String TOTAL = "total";
    private final String TIPRESULT = "tipResult";
    private final String DEFAULTTIP = "defaultTip";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipText = findViewById(R.id.textPercentResult);
        tipResult = findViewById(R.id.textTipResult);
        totalText = findViewById(R.id.textTotalResult);
        defaultTip = 2;

        tipText.setText(percent.format(tipPercent[defaultTip]/100));

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        userAmount = findViewById(R.id.editTextAmount);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultTip++;

                tipText.setText(percent.format(tipPercent[defaultTip]/100));

                if(!userAmount.getText().toString().isEmpty()){

                    tipText.setText(percent.format(tipPercent[defaultTip]/100));
                    tipResult.setText(
                            currency.format(calculateTip(
                                    Double.parseDouble(
                                            userAmount.getText().toString()
                                    )
                            )).toString()
                    );

                    totalText.setText(
                            currency.format(calculateTotal(
                                    Double.parseDouble(
                                            userAmount.getText().toString()
                                    )
                            )).toString()
                    );
                } else {
                    tipText.setText(percent.format(tipPercent[defaultTip]/100));

                    tipResult.setText(
                            currency.format(calculateTip(
                                    0.0
                            )).toString()
                    );

                    totalText.setText(
                            currency.format(calculateTotal(0.0)).toString()
                    );
                }
                toggleButtons();

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultTip--;
                tipText.setText(tipPercent[defaultTip].toString());

                if(!userAmount.getText().toString().isEmpty()){

                    tipText.setText(percent.format(tipPercent[defaultTip]/100));
                    tipResult.setText(
                            currency.format(calculateTip(
                                    Double.parseDouble(
                                            userAmount.getText().toString()
                                    )
                            )).toString()
                    );

                    totalText.setText(
                            currency.format(calculateTotal(
                                    Double.parseDouble(
                                            userAmount.getText().toString()
                                    )
                            )).toString()
                    );
                } else {
                    tipText.setText(percent.format(tipPercent[defaultTip]/100));

                    tipResult.setText(
                            currency.format(calculateTip(
                                    0.0
                            )).toString()
                    );

                    totalText.setText(
                            currency.format(calculateTotal(0.0)).toString()
                    );
                }

                toggleButtons();

            }
        });

        userAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                tipText.setText(percent.format(tipPercent[defaultTip]/100));
                if(!userAmount.getText().toString().isEmpty()){

                    tipText.setText(percent.format(tipPercent[defaultTip]/100));
                    tipResult.setText(
                            currency.format(calculateTip(
                                    Double.parseDouble(
                                            userAmount.getText().toString()
                                    )
                            )).toString()
                    );

                    totalText.setText(
                            currency.format(calculateTotal(
                                    Double.parseDouble(
                                            userAmount.getText().toString()
                                    )
                            )).toString()
                    );
                } else {
                    tipText.setText(percent.format(tipPercent[defaultTip]/100));

                    tipResult.setText(
                            currency.format(calculateTip(
                                    0.0
                            )).toString()
                    );

                    totalText.setText(
                            currency.format(calculateTotal(0.0)).toString()
                    );
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void toggleButtons() {
        btnMinus.setEnabled(defaultTip <= 5 && defaultTip > 0);
        btnPlus.setEnabled(defaultTip >= 0 && defaultTip < 5);
    }

    private Double calculateTip(Double amount){
        Double tip = 0.0;

        tip = amount * (tipPercent[defaultTip]/100);

        return tip;
    }
    private Double calculateTotal(Double amount){
        Double total = 0.0, tip = 0.0;
        tip = calculateTip(
                amount
        );
        total = amount + tip;

        return total;

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BILL_AMOUNT, userAmount.getText().toString());

        outState.putString(TIP, tipText.getText().toString());
        outState.putString(TIPRESULT,tipResult.getText().toString());
        outState.putString(TOTAL,totalText.getText().toString());
        outState.putInt(DEFAULTTIP,defaultTip);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        userAmount.setText(savedInstanceState.getString(BILL_AMOUNT));

        tipText.setText(savedInstanceState.getString(TIP));
        tipResult.setText(savedInstanceState.getString(TIPRESULT));
        totalText.setText(savedInstanceState.getString(TOTAL));
        defaultTip = savedInstanceState.getInt(DEFAULTTIP);
    }

}