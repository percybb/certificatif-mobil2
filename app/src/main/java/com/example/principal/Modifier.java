package com.example.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Modifier extends AppCompatActivity {
    private TextView lblTitre;
    private TextView txtNom;
    private TextView txtType;
    private TextView txtLuminosite;
    private TextView txtStatus;
    private dbConn myDb;
    private Gisement gis;
    private Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);

        this.lblTitre = (TextView) findViewById(R.id.lblTitreUpd);

        this.txtNom = (EditText)findViewById(R.id.txtNomUpd);
        this.txtType = (EditText)findViewById(R.id.txtTypeUpd);
        this.txtLuminosite = (EditText)findViewById(R.id.txtLumiUpd);
        this.txtStatus = (EditText)findViewById(R.id.txtStatusUpd);
        this.btnUpdate = (Button)findViewById(R.id.btnUpdate);


        this.myDb = new dbConn(this,"gisem",null,11);
        this.myDb.Open();

        int id = (int)getIntent().getExtras().getInt("id");
        this.lblTitre.setText(String.valueOf(id));

        gis = myDb.SelectGisementWithId(id);

        txtNom.setText(gis.getNom());
        txtType.setText(String.valueOf(gis.getType()));
        txtLuminosite.setText(String.valueOf(gis.getLuminosite()));
        txtStatus.setText(gis.getStatus());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNom.getText().toString().equals("") && !txtType.getText().toString().equals("") && !txtLuminosite.getText().toString().equals("") && !txtStatus.getText().toString().equals(""))
                {
                    //myDb.modifierGisementtWithId(gis.getId(),txtNom.getText().toString(),txtType.getText().toString(),Double.valueOf(txtLuminosite.getText().toString()),txtStatus.getText().toString(),1);
                    Toast.makeText(Modifier.this, "Produit modifie", Toast.LENGTH_SHORT).show();
                    txtNom.setText("");
                    txtStatus.setText("");
                    txtLuminosite.setText("");
                    txtStatus.setText("");
                    finish();
                }
                else{
                    Toast.makeText(Modifier.this, "Complete donnes", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}