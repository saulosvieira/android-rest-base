package br.com.mydev.androidrestbase.Rpository;


import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.mydev.androidrestbase.Adapter.AdapterInterface;
import br.com.mydev.androidrestbase.Entity.RemoteEntityInterface;
import br.com.mydev.androidrestbase.Network.CustomRequest;
import br.com.mydev.androidrestbase.Network.VolleySingleton;

abstract public class RemoteRepository {

    protected RemoteEntityInterface entity;

    protected List<RemoteEntityInterface> entityList = new ArrayList<>();

    public RemoteRepository(RemoteEntityInterface entity) {
        this.entity = entity;
    }

    public RemoteEntityInterface getOne(String url, HashMap<String, String> params) {
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                proccessGetOne(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        return this.entity;
    }

    abstract public void proccessGetOne(JSONObject response);

    public List<RemoteEntityInterface> getAll(final String url, HashMap<String, String> params, final String key, final AdapterInterface adapter){
        RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray(key);
                    adapter.setList(proccessGetAll(jsonArray));
                }catch(Exception e){
                    Log.e("Repository Error: ", "Não foi possível converter o response em um JsonArray. "+ response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Repository Error: ", "Erro ao acessar "+ url + ": "+ error.getMessage() );
            }
        });
        queue.add(jsObjRequest);
        return this.entityList;
    }

    abstract public List<RemoteEntityInterface> proccessGetAll(JSONArray jsonArray);
}
