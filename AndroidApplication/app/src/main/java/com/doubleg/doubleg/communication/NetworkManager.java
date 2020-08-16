package com.doubleg.doubleg.communication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.doubleg.doubleg.data.UserData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class NetworkManager {
    private static final String TAG = "NetworkManager";
    private static NetworkManager instance = null;
    private static final String prefixURL = "http://192.168.1.108/doubleg/Api.php?apicall=";
    //private static final String prefixURLMP = "https://foodstamps.wnpower.host/v1/preference.php";
    public  final int GET_USER = 0;
    public  final int CREATE_USER = 1;
    public final int CHECK_USERNAME = 2;

    //for Volley API
    public RequestQueue requestQueue;

    private NetworkManager(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        //other stuf if you need
    }

    public static synchronized NetworkManager getInstance(Context context)
    {
        if (null == instance)
            instance = new NetworkManager(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized NetworkManager getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(NetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public synchronized void dbFunction(int function, HashMap parameters, NetworkRequestListener listener)
    {
        switch(function)
        {
            case GET_USER:
                getUserData(parameters,listener);
                break;
            case CREATE_USER:
                createUser(parameters,listener);
                break;
            case CHECK_USERNAME:
                checkUsername(parameters,listener);
                break;
        }
    }

    private synchronized void getUserData(HashMap parameters, NetworkRequestListener listener)
    {
        String url = prefixURL+"getUser";
        JSONObject parametros = new JSONObject(parameters);
        Log.d("SQL","Lo que envio en el json es: "+parametros.toString());
        Log.d("SQL","URL enviada: "+url);
        JsonObjectRequest jsObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parametros, response -> {
            try {
                boolean error = response.getBoolean("error");
                if(!error)
                {
                    Object item = response.get("message");
                    if(item instanceof JSONArray) //Si es un arreglo significa que tengo el usuario
                    {
                        JSONArray jsonArray = response.getJSONArray("message");
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String name = jsonObject.getString("name");
                        String lastName = jsonObject.getString("last_name");
                        String email = jsonObject.getString("email");
                        String username = jsonObject.getString("username");
                        UserData user = new UserData(name,lastName, email,username);
                        listener.getResult(user);
                    }
                    else //No tengo un arreglo sino un solo mensaje.
                    {
                        listener.getError("No se encontro al usuario");
                    }
                }
                else{
                    Log.d("SQL",response.getString("message"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },
                error -> {
                    if (error == null || error.networkResponse == null) {
                        listener.getError("Error desconocido");
                        return;
                    }
                    String body;
                    //get status code here
                    final String statusCode = String.valueOf(error.networkResponse.statusCode);
                    //get response body and parse with appropriate encoding
                    try {
                        body = new String(error.networkResponse.data,"UTF-8");
                        listener.getError(body);
                    } catch (UnsupportedEncodingException e) {
                        // exception
                    }
                });

        requestQueue.add(jsObjectRequest);
    }

    private synchronized void createUser(HashMap parametros, NetworkRequestListener listener)
    {
        JSONObject parameters = new JSONObject(parametros);
        Log.d("SQL","Lo que envio en el json es: "+parameters.toString());
        String url = prefixURL+"createUser";
        Log.d("SQL","URL enviada: "+url);
        JsonObjectRequest jsObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, response -> {
            try {
                boolean error = response.getBoolean("error");
                if(!error)
                {
                    boolean jsonObject = response.getBoolean("message");
                    listener.getResult(jsonObject);
                }
                else{
                    listener.getError(response.getString("message"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },
                error -> listener.getError(error.getMessage()));
        requestQueue.add(jsObjectRequest);
    }

    private synchronized void checkUsername(HashMap parameters, NetworkRequestListener listener)
    {
        String busqueda = "&username="+parameters.get("username");
        String url = prefixURL+"checkUsernameUsed"+busqueda;
        JsonObjectRequest jsObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                boolean error = response.getBoolean("error");
                if(!error)
                {
                    Object item = response.get("message");
                    if(item instanceof JSONArray) //Si es un arreglo significa que tengo el usuario
                    {
                        boolean jsonArray = response.getBoolean("message");
                        listener.getResult(jsonArray);
                    }
                    else //No tengo un arreglo sino un solo mensaje.
                    {
                        listener.getResult(response.getString("message"));
                    }
                }
                else{
                    Log.d("SQL",response.getString("message"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },
                error -> {
                    if (error == null || error.networkResponse == null) {
                        listener.getError("Error desconocido");
                        return;
                    }
                    String body;
                    //get status code here
                    final String statusCode = String.valueOf(error.networkResponse.statusCode);
                    //get response body and parse with appropriate encoding
                    try {
                        body = new String(error.networkResponse.data,"UTF-8");
                        listener.getError(body);
                    } catch (UnsupportedEncodingException e) {
                        // exception
                    }
                });

        requestQueue.add(jsObjectRequest);
    }




}
