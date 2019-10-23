package com.example.json_parsing;



import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;


public class MainActivity extends AppCompatActivity implements Asynchtask {

    TextView txtresultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Webservices con el URL de JSON contancts
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://api.androidhive.info/contacts/", datos,this, this);
        ws.execute("");

        //Devuelve un listado del JSON contancts
        ArrayList<HashMap<String, String>> contactList;
        txtresultado = (TextView)findViewById(R.id.txtContactos);//TextView
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject jsonObj = new JSONObject(result); // Declara un objeto a partir de la lista del JSON
        JSONArray contacts= jsonObj.getJSONArray("contacts"); //Array de la matriz
        String contactos="<ul>";
        for (int i=0; i<contacts.length(); i++){
            JSONObject c = contacts.getJSONObject(i); //Declara un objeto del JSON del detalle de nombre y correos
            JSONObject phone = c.getJSONObject("phone"); // Declara los datos del objeto de detalle del telefono
            contactos +=  "<li>Nombre: " + c.getString("name")+"    Movil: "+phone.getString("mobile") +"</li>";
            //Presentamos los nombres y los números telefonicos
        }
        contactos+="</ul>";
        txtresultado.setText(Html.fromHtml(contactos));//devolvemos esa cadena a presentar en el textview
    }
}
