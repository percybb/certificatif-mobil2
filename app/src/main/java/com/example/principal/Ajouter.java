package com.example.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Ajouter extends AppCompatActivity   implements SensorEventListener {

    private TextView lblTitre;
    private EditText txtNom;
    private EditText txtType;
    private EditText txtLumi;
    private EditText txtStatus;
    private dbConn myDb;
    private Button btnAddProduit;

    private SensorManager sensorManager;
    private Sensor sensor;

    private RadioGroup grpRadio;
    private RadioGroup grpRadio2;
    private String radOpc;
    private String radOpc2;
    private RadioButton radPetro;

    private RadioButton radOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        this.myDb = new dbConn(this,"gisem",null,11);
        this.myDb.Open();

        this.lblTitre = (TextView) findViewById(R.id.lblTitre);
        this.txtNom = (EditText)findViewById(R.id.txtNom);
       // this.txtType = (EditText)findViewById(R.id.txtType);
        //this.txtStatus = (EditText)findViewById(R.id.txtStatus);
        this.txtLumi = (EditText)findViewById(R.id.txtLumi);
        this.btnAddProduit = (Button)findViewById(R.id.btnAddProduit);
        this.grpRadio = (RadioGroup)findViewById(R.id.grpRadio);
        this.radPetro = (RadioButton)findViewById(R.id.radPetro) ;

        this.grpRadio2 = (RadioGroup)findViewById(R.id.grpRadio2);
        this.radOk = (RadioButton)findViewById(R.id.radOk) ;

        this.radPetro.setChecked(true);
        this.radOk.setChecked(true);
        radOpc = this.radPetro.getText().toString();

        String msg = (String)getIntent().getExtras().getString("titre");
        this.lblTitre.setText(msg);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);

        grpRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);
                radOpc = radioButton.getText().toString();
            }
        });

        grpRadio2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);
                radOpc2 = radioButton.getText().toString();
            }
        });

        btnAddProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNom.getText().toString().equals("") || !txtLumi.getText().toString().equals(""))
                {
                    myDb.InsertItem(txtNom.getText().toString(),radOpc,Double.valueOf(txtLumi.getText().toString()),radOpc2.toLowerCase(),0);
                    Toast.makeText(Ajouter.this, "Produit ajoute", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(Ajouter.this, "Complete donnes", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        txtLumi.setText(String.valueOf(sensorEvent.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}