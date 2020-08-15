package com.doubleg.doubleg.communication;

public interface NetworkRequestListener<T> {
    void getResult(T object);
    void getError(String s);
}
