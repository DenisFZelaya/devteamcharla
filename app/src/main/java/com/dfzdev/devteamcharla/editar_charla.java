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

public class editar_charla extends AppCompatActivity {
    // 1.1 Variables necesarias
    int idCharla;
    EditText nombreCharla, descCharla;
    DatePicker fechaCharla;
    Charla editarCharla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_charla);

        //1.2 Obtener el valor pasado en el insert
        Bundle recibirIdCharla= this.getIntent().getExtras();
        if (recibirIdCharla !=null) {
            idCharla = recibirIdCharla.getInt("Id");
        }

        getSupportActionBar().hide();

        //1.3 Igualar variables con xml
        nombreCharla = findViewById(R.id.inputENombreCharla);
        descCharla = findViewById(R.id.inputEDescCharla);
        fechaCharla = findViewById(R.id.datePickerEFecha);

        //1.4 Instanciar DB
        DatabaseHelper db = new DatabaseHelper(editar_charla.this);

        //1.5 Obtener un modelo lleno con la charla
        editarCharla = db.getCharlaById(idCharla);

        nombreCharla.setText(editarCharla.getTema());
        descCharla.setText(editarCharla.getDescripcion());
        String[] parts = editarCharla.getFecha().split("-");
        fechaCharla.init( Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]),null);

    }

    //1.6 Método para editar la charla
    public void editCharla(View view){
        try {
            Charla updateCharla = new Charla();
            String dia,mes, anio;

            if(!nombreCharla.getText().toString().equals("")){
                updateCharla.setTema(nombreCharla.getText().toString());
                System.out.println("setNombreCharla: " + updateCharla.getTema());
                nombreCharla.setBackgroundColor(Color.WHITE);
            }else{
                nombreCharla.setBackgroundColor(Color.parseColor("#ffb6c1"));
            }

            if(!descCharla.getText().toString().equals("")){
                updateCharla.setDescripcion(descCharla.getText().toString());
                System.out.println("setDeschipcion: " + updateCharla.getDescripcion());
                descCharla.setBackgroundColor(Color.WHITE);
            }else{
                descCharla.setBackgroundColor(Color.parseColor("#ffb6c1"));
            }

            dia = Integer.toString(fechaCharla.getDayOfMonth());
            mes = Integer.toString(fechaCharla.getMonth());
            anio = Integer.toString(fechaCharla.getYear());

            updateCharla.setFecha(dia + "-" + mes + "-" + anio);
            System.out.println("setFecha: " + dia + "-" + mes + "-" + anio);

            if(!nombreCharla.getText().toString().equals("") && !descCharla.getText().toString().equals("")){
                DatabaseHelper db = new DatabaseHelper(editar_charla.this);
                updateCharla.setId(idCharla);
                boolean resultado = db.updateCharla(updateCharla);

                if(resultado){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Charla actualizada correctamente!")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    onBackPressed();
                                }
                            });
                    builder.show();
                }else {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "No se ha actualizado la charla", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }else {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Llene todos los campos", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //1.7 Método para eliminar la charla
    public void eliminarCharla(View v){
        DatabaseHelper db = new DatabaseHelper(editar_charla.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Se ha eliminado la charla!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        boolean resultado = db.deleteCharlaById(editarCharla.getId());

                        if(resultado){
                            Snackbar snackbarSuc= Snackbar.make(findViewById(android.R.id.content), "Se ha eliminado la charla", Snackbar.LENGTH_LONG);
                            snackbarSuc.show();
                            onBackPressed();
                        }else {
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "No se ha podido eliminar la charla!", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                });
        builder.show();
    }
}