package com.wildanka.dagger2introduction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Engine engine = new Engine();
        Wheels wheels = new Wheels();

        /**
         * Before using dagger we need to do quite a lot of initialization.
         * Whereas before, when our car is created its own dependencies,
         * we didn't have to do this stuff. because then we didn't have to pass them as arguments
         * */
        Car car = new Car(engine, wheels);

        //then we can call car.drive
        car.drive();
    }
}
