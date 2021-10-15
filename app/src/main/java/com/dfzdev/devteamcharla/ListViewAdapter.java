package com.dfzdev.devteamcharla;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dfzdev.devteamcharla.models.Charla;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Charla> {
    //1.1 Constructor
    public ListViewAdapter(Context context, ArrayList<Charla> charlas) {
        super(context, 0, charlas);
    }

    //1.2 Metodo para obtener el layout de la lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtener xml de la vista
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_list, parent, false);
        }

        // Get the data item for this position
        Charla charla = getItem(position);

        // Declarar variables y igualarlas al xml
        TextView textViewTemaItem = (TextView) convertView.findViewById(R.id.tv_list_tema);
        TextView textViewFechaItem = (TextView) convertView.findViewById(R.id.tv_list_fecha);
        Button btnItem = (Button) convertView.findViewById(R.id.btnList);

        // Setear textview con datos de la charla
        textViewTemaItem.setText(charla.getTema());
        textViewFechaItem.setText(charla.getFecha());
        // Return the completed view to render on screen

        // Asignar evento a boton
        btnItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                cargarPageEditar(charla.getId());
            }
        });

        //Retornar la vista
        return convertView;
    }

    //1.3 Método para cargar una nueva actividad editar
    protected void cargarPageEditar(int idCharla){
        try{
            System.out.println("Funcion editar charla: " + idCharla);
            Bundle datoenvia = new Bundle();
            datoenvia.putInt ("Id",idCharla);

            Intent intentEditarCharla = new Intent (getContext(), editar_charla.class);
            intentEditarCharla.putExtras(datoenvia);
            getContext().startActivity(intentEditarCharla);
        }catch (Exception ex){
            System.out.println("Error cargando EditarCharla: " + ex.getMessage());
        }

    }
}
