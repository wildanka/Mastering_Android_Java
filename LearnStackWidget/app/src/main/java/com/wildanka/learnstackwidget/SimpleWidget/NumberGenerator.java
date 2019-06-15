package com.wildanka.learnstackwidget.SimpleWidget;

import java.util.Random;

public class NumberGenerator {
    public static int Generate(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
