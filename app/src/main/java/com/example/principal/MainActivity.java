package com.example.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lstItems;
    private ArrayList<Gisement> lstGis;
    private itemAdapter adapter;
    private Button btnAjouter;
    private Button btnFilter;

    private EditText txtLumMin;
    private EditText txtLumMax;


    private dbConn myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lstItems = findViewById(R.id.lstItems);
        this.btnAjouter = findViewById(R.id.btnAjouter);
        this.btnFilter = findViewById(R.id.btnFilter);
        this.txtLumMin = findViewById(R.id.txtLumMin);
        this.txtLumMax = findViewById(R.id.txtLumMax);

        this.myDb = new dbConn(this,"gisem",null,11);
        this.myDb.Open();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.lstGis = new ArrayList<Gisement>();
        this.lstGis =  this.myDb.SelectAllGisement();
        //this.lstGis.add(new Gisement(0,"sss","dos",23.2,"ok",0));
        //this.lstGis.add(new Gisement(0,"sss","dos",23.2,"ok",0));
        this.adapter = new itemAdapter(this,
                R.layout.list_gisement,this.lstGis);

        this.lstItems.setAdapter(this.adapter);
        ColorDrawable colorDrawable = new ColorDrawable(Color.rgb(192,188,255));

        txtLumMin.setText("");
        txtLumMax.setText("");

        this.btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Ajouter.class);
                i.putExtra("titre","Ajouter Produit");
                startActivity(i);
            }
        });

        lstItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = lstGis.get(i).getId();
                Intent in = new Intent(MainActivity.this,voirItem.class);
                in.putExtra("id",id);
                startActivity(in);
            }
        });

        lstItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = lstGis.get(i).getId();
                lstGis.remove(i);
                myDb.deleteGisementId(id);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Gisement enleve", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lstGis.clear();
                if (txtLumMin.getText().toString().equals("") || txtLumMax.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Manque donnes", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    lstGis.addAll(myDb.SelectGisementFilter(Float.parseFloat(txtLumMin.getText().toString()), Float.parseFloat(txtLumMax.getText().toString())));
                    adapter.notifyDataSetChanged();

                }
            }
        });


    }
}