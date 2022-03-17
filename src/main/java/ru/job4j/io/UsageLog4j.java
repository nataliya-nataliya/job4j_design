package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        byte v1 = 2;
        short v2 = 100;
        int v3 = 65000;
        long v4 = 650000;
        double v5 = 0.5;
        float v6 = 3.14f;
        boolean v7 = true;
        char v8 = 'a';

        LOG.debug("Variables: byte: {}, short : {}, int : {}, "
                + "long: {}, double : {}, float : {}, "
                + "boolean: {}, char : {}",  v1, v2, v3, v4, v5, v6, v7, v8);
    }
}
