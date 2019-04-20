package com.wildanka.dagger2introduction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wildanka.dagger2introduction.entity.Block;
import com.wildanka.dagger2introduction.entity.Cylinders;
import com.wildanka.dagger2introduction.entity.Rims;
import com.wildanka.dagger2introduction.entity.Tires;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** TODO : 2
         * now, let's say our engine and wheels have their very own dependencies.
         * for example, our engine could consist of a motor block, cylinders and spark plugs.
         * */
        Block block = new Block();
        Cylinders cylinders = new Cylinders();
        SparkPlugs sparkPlugs = new SparkPlugs();

        Tires tires = new Tires();
        Rims rims = new Rims();
        /**
         * as you can see this manual dependencies (above) really pollutes all our calling sites, and this is really the downsides here
         * and now, whenever we change a construcor or how we have to configure one of those classes we have to update these changes everywhere it used
         * which can be a really tedious process.
         * This is where DI framework like Dagger comes in.
         *
         * Their main responsibility is to get rid all of this boilerplate code,
         * so we basically teach Daggor how to construct objects and then it creates them at the right time and in the right order.
         * */
        Engine engine = new Engine(cylinders, block, sparkPlugs);
        Wheels wheels = new Wheels(tires, rims);

        /** TODO : 1
         * Before using dagger we need to do quite a lot of initialization.
         * Whereas before, when our car is created its own dependencies,
         * we didn't have to do this stuff. because then we didn't have to pass them as arguments
         * */
        Car car = new Car(engine, wheels);

        //then we can call car.drive
        car.drive();
    }
}
