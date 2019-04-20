package com.wildanka.dagger2introduction;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** TODO 3 :
         * CarComponent component is just an interface, so we can't instantiate a new Car Component
         * but since we anotated the CarComponent and then compiled our project sucessfully the dagger will created the "DaggerCarComponent"
         * this (DaggerCarComponent) is the implementation for our interface, this (DaggerCarComponent) contains all the necessary code that can create our Car
         * we simply have to call .create()
         *
         * */
        CarComponent carComponent = DaggerCarComponent.create();

        //then we can simply assign our car. if this success then we will see drive() method called, and display logs "driving..."
        car = carComponent.getCar();
        //then we can call car.drive
        car.drive();
    }
}
