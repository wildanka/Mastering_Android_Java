package com.wildanka.dagger2introduction;

import com.wildanka.dagger2introduction.entity.Block;
import com.wildanka.dagger2introduction.entity.Cylinders;

public class Engine {
    Cylinders cylinders;
    Block block;
    SparkPlugs sparkPlugs;

    public Engine(Cylinders cylinders, Block block, SparkPlugs sparkPlugs) {
        this.cylinders = cylinders;
        this.block = block;
        this.sparkPlugs = sparkPlugs;
    }
}
