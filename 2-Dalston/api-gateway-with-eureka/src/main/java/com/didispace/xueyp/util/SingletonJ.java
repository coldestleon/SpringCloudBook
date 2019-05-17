package com.didispace.xueyp.util;

public class SingletonJ {
    private static class SingletonHolder {
        private static final SingletonJ INSTANCE = new SingletonJ();
    }
    private SingletonJ (){}
    public static final SingletonJ getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
