package com.example.reto5grupo35.Vista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.reto5grupo35.Modelo.Adaptador;
import com.example.reto5grupo35.Modelo.Entidad;
import com.example.reto5grupo35.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FragmentProductos extends Fragment {

    View v;
   // Cursor cursor;
    ListView listaProductos;
    Adaptador adaptador;
    int [] imagen = {R.drawable.chaquetamujer, R.drawable.chaquetas, R.drawable.nino };
    String TAG = "FragmentProductos";
    TextView prueba;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.fragment_productos, container, false);
        listaProductos = (ListView) v.findViewById(R.id.lista_productos);
        adaptador = new Adaptador(getTablaProductos(), getActivity());

        listaProductos.setAdapter(adaptador);
        prueba = (TextView) v.findViewById(R.id.prueba);

        //-----------------------------------------------------------------------------
        return v;
    }
    private ArrayList<Entidad> getTablaProductos(){
        ArrayList<Entidad> listaProductos = new ArrayList<>();

       String url = "https://ge66797f0b4653f-modulo4reto4.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/productos/productos";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //***********************************************************
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        String titulo = jsonObject.getString("titulo");
                        String descripcion = jsonObject.getString("descripcion");

                        listaProductos.add(new Entidad(imagen[i], titulo, descripcion));
                        prueba.setText(" " + '\n');

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //***********************************************************
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
        /* ================================================================================================== */

        return listaProductos;
    }

}
