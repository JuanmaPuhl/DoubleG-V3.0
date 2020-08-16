package com.doubleg.doubleg.communication;

import android.content.Context;
import android.net.NetworkRequest;

import com.doubleg.doubleg.data.UserData;

import java.util.HashMap;

public class NetworkFunctions {

    public static void login(Context context, String username,String password, NetworkRequestListener<UserData> listener)
    {
        HashMap hashmap = new HashMap();
        hashmap.put("username",username);
        hashmap.put("password",password);
        NetworkManager manager = NetworkManager.getInstance(context);
        manager.dbFunction(manager.GET_USER,hashmap,listener);
    }

    public static void createUser(Context context,String name, String lastname, String username, String mail, String password, NetworkRequestListener<Boolean> listener)
    {
        HashMap hashmap = new HashMap();
        hashmap.put("first_name",name);
        hashmap.put("last_name",lastname);
        hashmap.put("email",mail);
        hashmap.put("username",username);
        hashmap.put("password",password);
        NetworkManager manager = NetworkManager.getInstance(context);
        manager.dbFunction(manager.CREATE_USER,hashmap,listener);
    }

    public static void checkUsernameUsed(Context context, String username, NetworkRequestListener<Boolean> listener)
    {
        HashMap hashmap = new HashMap();
        hashmap.put("username",username);
        NetworkManager manager = NetworkManager.getInstance(context);
        manager.dbFunction(manager.CHECK_USERNAME,hashmap,listener);
    }


}
