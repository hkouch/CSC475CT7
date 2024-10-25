package com.example.ct7o1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input;
    Spinner unit;
    TextView km, m, cm, mm, microm, nm, mile, yard, foot, inch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        unit = findViewById(R.id.unit);
        km = findViewById(R.id.km);
        m = findViewById(R.id.m);
        cm = findViewById(R.id.cm);
        mm = findViewById(R.id.mm);
        microm = findViewById(R.id.microm);
        nm = findViewById(R.id.nm);
        mile = findViewById(R.id.mile);
        yard = findViewById(R.id.yard);
        foot = findViewById(R.id.foot);
        inch = findViewById(R.id.inch);

        // Filter to allow only 0-9, ".", and "-"
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);
                    if (!Character.isDigit(currentChar) && currentChar != '.' && currentChar != '-') {
                        return "";
                    }
                }
                return null;
            }
        };

        input.setFilters(new InputFilter[]{filter});

        String[] arr = {"km", "m", "cm", "mm", "microm", "nm", "mile", "yard", "foot", "inch"};
        unit.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arr));

        unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                update();
            }
        });
    }

    private void update() {
        String inputText = input.getText().toString();
        double in = 0;

        // If the input is invalid, treat it as 0
        try {
            in = Double.parseDouble(inputText);
        } catch (NumberFormatException e) {
            // Invalid input, in remains 0
        }

        if (!unit.getSelectedItem().toString().equals("")) {
            double kmValue = convertToKm(in, unit.getSelectedItem().toString());
            setKm(kmValue);
        }
    }

    public static double convertToKm(double value, String fromUnit) {
        switch (fromUnit) {
            case "km":
                return value;
            case "m":
                return value / 1000;
            case "cm":
                return value / 100000;
            case "mm":
                return value / 1000000;
            case "microm":
                return value / 1000000000;
            case "nm":
                return value / 1e12; // Correct conversion from nanometers to kilometers
            case "mile":
                return value * 1.60934; // miles to kilometers
            case "yard":
                return value * 0.0009144; // yards to kilometers
            case "foot":
                return value * 0.0003048; // feet to kilometers
            case "inch":
                return value * 0.0000254; // inches to kilometers
            default:
                return value; // fallback
        }
    }

    private void setKm(double km_in) {
        km.setText(String.valueOf(km_in));
        m.setText(String.valueOf(km_in * 1000));
        cm.setText(String.valueOf(km_in * 100000));
        mm.setText(String.valueOf(km_in * 1000000));
        microm.setText(String.valueOf(km_in * 1e9)); // kilometers to micrometers
        nm.setText(String.valueOf(km_in * 1e12)); // kilometers to nanometers
        mile.setText(String.valueOf(km_in / 1.60934)); // kilometers to miles
        yard.setText(String.valueOf(km_in / 0.0009144)); // kilometers to yards
        foot.setText(String.valueOf(km_in / 0.0003048)); // kilometers to feet
        inch.setText(String.valueOf(km_in / 0.0000254)); // kilometers to inches
    }
}
