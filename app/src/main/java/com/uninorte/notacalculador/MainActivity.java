package com.uninorte.notacalculador;

import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

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
        ArrayList<EditText> lista = new ArrayList<EditText>();
        ArrayList<EditText> listToRemove = new ArrayList<EditText>();
        boolean notaCalculated = false;
        EditText emptyNota;

        lista.add(edtNota1);
        lista.add(edtNota2);
        lista.add(edtNota3);
        lista.add(edtNota4);

        for (int i=0; i < lista.size(); i++) {
            if (TextUtils.isEmpty(lista.get(i).getText())) {
                emptyNota = lista.get(i);
                lista.remove(i);
                double remainingSum = calculatSum(lista);
                double expectedAverage = Math.ceil((remainingSum) / 3);
                double missingNota = calculateMissingNota(remainingSum, expectedAverage);
                emptyNota.setText(String.valueOf(missingNota));
                notaCalculated = true;
                tvResultado.setText(String.valueOf(df.format(expectedAverage)));
            }
        }

        if (!notaCalculated) {
            double resultado = calculatSum(lista) / 4;
            tvResultado.setText(String.valueOf(df.format(resultado)));
        }
    }

    /**
     * calculates the missing nota
     * @param suma the suma of the first three grades
     * @param average the expected average
     * @return the missing nota
     */
    private double calculateMissingNota(double suma, double average) {
        return 4 * average - suma;
    }

    /**
     * calculates the sum of a given Arraylist
     */
    private double calculatSum(ArrayList<EditText> lista) {
        double resultado = 0.0;
        for (EditText edit : lista) {
            resultado += Double.parseDouble(edit.getText().toString());
        }
        return resultado;
    }

}


