package com.dfzdev.devteamcharla;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.dfzdev.devteamcharla.models.Charla;
import com.google.android.material.snackbar.Snackbar;

public class crear_charla extends AppCompatActivity {

    // 1.1 Variables necesarias
    EditText nombreCharla, descCharla;
    DatePicker fechaCharla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_charla);

        //1.2 Igualar objetos con el elmento del mismo tipo en el XML
        nombreCharla = findViewById(R.id.inputCNombreCharla);
        descCharla = findViewById(R.id.inputCDescCharla);
        fechaCharla = findViewById(R.id.datePickerCFecha);

        //1.3 Ocultar la barra superior
        getSupportActionBar().hide();
    }

    //1.4 Método para crear charla
    public void crearCharla(View view){

        //1.4.1 Instanciar el objeto
        Charla nuevaCharla = new Charla();

        //1.4.2 Obtener los datos de la fecha
        String dia,mes, anio;

        //1.4.3 Validar y guardar campos
        if(!nombreCharla.getText().toString().equals("")){
            nuevaCharla.setTema(nombreCharla.getText().toString());
            System.out.println("setNombreCharla: " + nuevaCharla.getTema());
            nombreCharla.setBackgroundColor(Color.WHITE);
        }else{
            nombreCharla.setBackgroundColor(Color.parseColor("#ffb6c1"));
        }

        if(!descCharla.getText().toString().equals("")){
            nuevaCharla.setDescripcion(descCharla.getText().toString());
            System.out.println("setDeschipcion: " + nuevaCharla.getDescripcion());
            descCharla.setBackgroundColor(Color.WHITE);
        }else{
            descCharla.setBackgroundColor(Color.parseColor("#ffb6c1"));
        }

        //1.4.4 Obtener fecha
        dia = Integer.toString(fechaCharla.getDayOfMonth());
        mes = Integer.toString(fechaCharla.getMonth());
        anio = Integer.toString(fechaCharla.getYear());
        nuevaCharla.setFecha(dia + "-" + mes + "-" + anio);
        System.out.println("setFecha: " + dia + "-" + mes + "-" + anio);

        //1.4.5 Validar campos completos
        if(!nombreCharla.getText().toString().equals("") && !descCharla.getText().toString().equals("")){

            //Crear instancia de la base de datos
            DatabaseHelper db = new DatabaseHelper(crear_charla.this);
            boolean resultado = db.addCharla(nuevaCharla);

            if(resultado){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Charla agregada correctamente!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                onBackPressed();
                            }
                        });
                builder.show();
            }else {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "No se ha podido crear la charla", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Llene todos los campos", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }
}