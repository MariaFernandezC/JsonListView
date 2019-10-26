package com.example.jsonlistview;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    ListView lstOpciones;
    public Noticias[] noticias = new Noticias[]{
            new Noticias("Noticia 1", "SubNoticia Contenido  Contenido Contenido Contenido 1"),
            new Noticias("Noticia 2", "SubNoticia Contenido  Contenido Contenido Contenido 2"),
            new Noticias("Noticia 3", "SubNoticia Contenido  Contenido Contenido Contenido 3"),
            new Noticias("Noticia 4", "SubNoticia Contenido  Contenido Contenido Contenido 4")};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdaptadorNoticias adaptadornoticias = new AdaptadorNoticias(this, noticias);
        ListView lstOpciones = (ListView)findViewById(R.id.lstOpciones);
        lstOpciones.setAdapter(adaptadornoticias);

        lstOpciones = (ListView) findViewById(R.id.lstOpciones);
        Bundle  bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://api.androidhive.info/contacts/", datos, MainActivity.this, MainActivity.this);
        ws.execute("");
    }


    @Override
    public void processFinish(String result) throws JSONException {

        Log.i("processFinish", result);
        //txtresultado.setText(result);
        JSONObject jsonObj = new JSONObject(result);
        JSONArray contacts = jsonObj.getJSONArray("contacts");
        String contactos = "<ul>";
        for (int i = 0; i<contacts.length(); i++ )
        {
            JSONObject c = contacts.getJSONObject(i);
            contactos += "<li> ID: " + c.getString("id")  +"\r\n Nombre: " + c.getString("name")+ "\r\n Email: "+ c.getString("email")+ "\r\n Direccion: "+ c.getString("address")+ "\r\n Genero: "+ c.getString("gender")+"</li>";

            JSONObject phone = c.getJSONObject("phone");
            contactos += "<li>Mobile: " + phone.getString("mobile")+ "\r\n Home: "+ phone.getString("home")+ "\r\n Office: " +
                    ""+ phone.getString("office")+"</li>";
        }
        //txtresultado.setText(contactos);
        contactos += "</ul>";
        lstOpciones.getAdapter();
    }
}
