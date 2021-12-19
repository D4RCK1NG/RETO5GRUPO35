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


public class FragmentServicios extends Fragment {


    View v;
    String TAG = "FragmentServicios";
    int[] imagen = {R.drawable.domicilio, R.drawable.arreglos, R.drawable.estampado};
    ListView listaServicios;
    Adaptador adaptador;
    TextView pruebas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_servicios, container, false);
        //-----------------------------------------------------------------------------
        listaServicios = (ListView) v.findViewById(R.id.lista_servicios);
        adaptador = new Adaptador(getTablaProductos(), getContext());

        listaServicios.setAdapter(adaptador);

        pruebas = (TextView) v.findViewById(R.id.pruebas);
        //-----------------------------------------------------------------------------
        return v;
    }

    private ArrayList<Entidad> getTablaProductos() {
        ArrayList<Entidad> listaServicios = new ArrayList<>();

        /* ================================================================================================== */
        String url = "https://ge66797f0b4653f-modulo4reto4.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/servicios/servicios";


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //***********************************************************
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                       // String imagen = jsonObject.getString("imagen");
                        String titulo = jsonObject.getString("titulo");
                        String descripcion = jsonObject.getString("descripcion");

                        listaServicios.add(new Entidad(imagen[i], titulo, descripcion));
                        pruebas.setText(" " + '\n');

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

        return listaServicios;
    }
}