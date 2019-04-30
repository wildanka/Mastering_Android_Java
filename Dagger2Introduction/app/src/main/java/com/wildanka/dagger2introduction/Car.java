package com.wildanka.dagger2introduction;

import android.util.Log;

import javax.inject.Inject;

public class Car {
    private static final String TAG = "Car";
    private Engine engine;
    private Wheels wheels;

    // TODO 1 :
    /**
     * in previous example we created the Car Class, Engine, and Wheels. (in other word Engine and Wheels are the dependency of the Car)
     * and we said that instead of creating this activities instead the car class (with provision method),
     * we should pass them over the constructor or in other words INJECT them and by annotating the constructor with @Inject
     * we asked dagger to provide these dependencies, so we don't have to pass to constructor ourselves and save a lot of boilerplate code
     * that we would otherwise have to write
     *
     * this process is what we refer as CONSTRUCTOR INJECTION
     * and this way we also tell dagger to create instances of this class, so dagger can provide an object of this whenever it is needed
     *
     * */
    @Inject
    public Car(Engine engine, Wheels wheels) {
        this.engine = engine;
        this.wheels = wheels;
    }

    void drive(){
        Log.d(TAG, "driving...");
    }
}
