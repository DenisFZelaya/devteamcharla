package com.dfzdev.devteamcharla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dfzdev.devteamcharla.models.Charla;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv_charlaView;
    ArrayAdapter charlaArrayAdapter;
    FloatingActionButton fabNuevaCharla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        fabNuevaCharla = findViewById(R.id.fab_CrearCharla);

        DatabaseHelper db = new DatabaseHelper(MainActivity.this);

        listCharlas();
    }

    public void RecargarListaCharlas(View v){

        listCharlas();
    }

    public void listCharlas(){
        ListView lv_charlasView = findViewById(R.id.lv_charlasView);
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<Charla> arrayCharlas = (ArrayList<Charla>) db.getAllCharlas();
        ListViewAdapter adapter = new ListViewAdapter(this, arrayCharlas);
        lv_charlasView.setAdapter(adapter);
    }

    public  void irCrearCharla(View v){
        listCharlas();
        Intent intentCrearCharla = new Intent (MainActivity.this, crear_charla.class);
        startActivity(intentCrearCharla);
    }

}