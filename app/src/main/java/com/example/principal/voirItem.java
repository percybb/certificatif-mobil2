package com.example.principal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class voirItem extends AppCompatActivity {

    private TextView lblTitre;
    private TextView txtNom;
    private TextView txtPrix;
    private TextView txtDate;
    private TextView txtImg;
    private dbConn myDb;
    private Gisement gis;

    //private RadioButton radPetroMsg;
    //private RadioButton radMinMsg;

    private Button btnEdition;
    private int id=0;

    private RadioGroup radGru;
    private String radOpc;

    private Button btnDelMsg;
    //private Button btnUpdMsg;


    private RadioGroup radGrp2;

    protected void onResume() {
        super.onResume();

        gis = myDb.SelectGisementWithId(id);

        txtNom.setText(gis.getNom());
        txtPrix.setText(gis.getType());
        txtDate.setText(String.valueOf(gis.getLuminosite()));
        txtImg.setText(gis.getStatus());

        //myDb.modifierStatus(id,1);
        btnEdition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(voirItem.this,Modifier.class);
                //i.putExtra("id", Integer.valueOf(id));
                //startActivity(i);



                AlertDialog.Builder builder = new AlertDialog.Builder(voirItem.this);
                builder.setTitle("Gisement:"+ txtNom.getText());
                View view2 = getLayoutInflater().inflate(R.layout.delete, null);
                builder.setView(view2);

                RadioButton radPetroMsg = (RadioButton)view2.findViewById(R.id.radPetroMsg);
                RadioButton radMinMsg = (RadioButton)view2.findViewById(R.id.radMinMsg);

                RadioButton radOkMsg = (RadioButton)view2.findViewById(R.id.radOkMsg);
                RadioButton radNokMsg = (RadioButton)view2.findViewById(R.id.radNokMsg);

                Button btnUpdMsg = (Button)view2.findViewById(R.id.btnUpdMsg);


                if(gis.getType().equals("Petroliers"))
                {
                    radPetroMsg.setChecked(true);

                }
                else{
                    radMinMsg.setChecked(true);
                }

                if(gis.getStatus().equals("ok"))
                {
                    radOkMsg.setChecked(true);

                }
                else{
                    radNokMsg.setChecked(true);
                }



                btnUpdMsg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String opcUpd = "";
                        String opcUpd2 = "";
                        if(radPetroMsg.isChecked())
                        {
                            opcUpd="Petroliers";
                        }
                        else{
                            opcUpd="Miniurs";
                        }

                        if(radOkMsg.isChecked())
                        {
                            opcUpd2="ok";
                        }
                        else{
                            opcUpd2="nok";
                        }

                        myDb.modifierGisementtWithId(id,opcUpd,opcUpd2);
                        finish();
                    }

                });

                AlertDialog ad = builder.create();
                ad.show();







            }
        });

        btnDelMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb.deleteGisementId(id);
                finish();
            }
        });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_item);


        this.btnDelMsg = (Button)findViewById(R.id.btnDelMsg);
        this.lblTitre = (TextView) findViewById(R.id.lblTitreVoir);


        this.txtNom = (TextView)findViewById(R.id.lblNomVoir);
        this.txtPrix = (TextView)findViewById(R.id.lblTypeVoir);
        this.txtDate = (TextView)findViewById(R.id.lblLumiVoir);
        this.txtImg = (TextView)findViewById(R.id.lblStatusVoir);

        this.btnEdition = (Button)findViewById(R.id.btnEdition);




        this.myDb = new dbConn(this,"gisem",null,11);
        this.myDb.Open();

        id = (int)getIntent().getExtras().getInt("id");
        this.lblTitre.setText(String.valueOf(id));

/*        this.radGrp2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);
            }
        });*/








    }
}