package com.doubleg.doubleg.utilities;

import android.util.Log;

public class Utilities {

    public static boolean checkEmptyString(String[] arr)
    {
        boolean finished = false;
        int i = 0;
        while(i<arr.length && !finished)
        {
            if(arr[i].equals("")) {
                finished = true;
                Log.d("TAG","El vacio es: "+arr[i] );
            }
            else
                i++;
        }
        Log.d("TAG","RESULTADO: "+finished);
        return finished;
    }
}
