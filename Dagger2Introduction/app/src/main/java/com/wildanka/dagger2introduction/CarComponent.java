package com.wildanka.dagger2introduction;

import dagger.Component;

/** TODO 1 :
 * The Component is the most important piece of dagger, basically it is the backbone.
 * this is where the classes like activity(in our case MainActivity) get the Object they want to use
 * and for this component create a dependency graph
 * car :
 *   1. Engine
 *      1.a. Block
 *      1.b. Cylinders
 *      1.c. SparkPlugs
 *   2. Wheels
 *      2.a. Tires
 *      2.b. Rims
 * what we see above is Directed Acyclic Graph
 * Directed = because it direction is clearly defined in one direction (for example: car -> 1 Engine -> 1.1 Block)
 * Acyclic = because it is not cycled in here (nothing is child of itself)
 * Graph
 * the abbreviation is DAG, thats where the DAGger name come from
 *
 * so the component create and stores our object and then provides them to us, you can also call it the injector.
 * there are two ways which the component can provide our dependencies
 * 1. it can either injected into the member variable of our activity directly (this is what we're gonna look at the next example
 * 2. the other way is te so-called permission method, which is just a simple getter method
 *
 *
 * */
@Component
public interface CarComponent {
    Car getCar();
}
