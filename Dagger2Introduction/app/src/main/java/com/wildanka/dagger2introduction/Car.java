package com.wildanka.dagger2introduction;

import android.util.Log;

import javax.inject.Inject;

public class Car {
    private static final String TAG = "Car";
    private Engine engine;
    private Wheels wheels;

    // TODO 2 :
    // every object that wanted to be used by dagger should be annotated by @Inject annotation
    @Inject
    public Car(Engine engine, Wheels wheels) {
        this.engine = engine;
        this.wheels = wheels;
    }

    void drive(){
        Log.d(TAG, "driving...");
    }
}
