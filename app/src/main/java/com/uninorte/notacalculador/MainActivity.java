package com.uninorte.notacalculador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText edtNota1;
    private EditText edtNota2;
    private EditText edtNota3;
    private EditText edtNota4;
    private TextView tvResultado;

    DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNota1 = (EditText) findViewById(R.id.edtNota1);
        edtNota2 = (EditText) findViewById(R.id.edtNota2);
        edtNota3 = (EditText) findViewById(R.id.edtNota3);
        edtNota4 = (EditText) findViewById(R.id.edtNota4);
        tvResultado = (TextView) findViewById(R.id.tvResultado);

        df = new DecimalFormat("#.##");
    }

    public void onClickCalcular(View view) {
        double nota1 = Double.parseDouble(edtNota1.getText().toString());
        double nota2 = Double.parseDouble(edtNota2.getText().toString());
        double nota3 = Double.parseDouble(edtNota3.getText().toString());
        double nota4 = Double.parseDouble(edtNota4.getText().toString());
        double resultado;

        if (nota4 == 0.0) {
            double suma = nota1 + nota2 + nota3;
            resultado = Math.ceil((suma) / 3);
            double missingNota = calculateMissingNota(suma, resultado);
            edtNota4.setText(String.valueOf(missingNota));
        } else {
            resultado = (nota1 + nota2 + nota3 + nota4) / 4;
        }
        tvResultado.setText(String.valueOf(df.format(resultado)));
    }

    private double calculateMissingNota(double suma, double average) {
        return 4 * average - suma;
    }

}


