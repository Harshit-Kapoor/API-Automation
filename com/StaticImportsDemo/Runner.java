package com.StaticImportsDemo;

import static com.StaticImportsDemo.A.*;

public class Runner {

    public static void main(String[] args) {

        show();   // will run since we have statically imported, so no need to call method using class name

        A.add();

    }
}
