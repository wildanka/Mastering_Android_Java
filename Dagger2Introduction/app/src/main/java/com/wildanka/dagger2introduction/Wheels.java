package com.wildanka.dagger2introduction;

import com.wildanka.dagger2introduction.entity.Rims;
import com.wildanka.dagger2introduction.entity.Tires;

public class Wheels {
    Tires tires;
    Rims rims;

    public Wheels(Tires tires, Rims rims) {
        this.tires = tires;
        this.rims = rims;
    }
}
