package com.didispace.xueyp.util;

class Foo {
    int i = 1;

    Foo() {
        System.out.println("constructor Foo");
        System.out.println(i);
        int x = getValue();
        System.out.println(x);
    }
    Foo(int x) {
        System.out.println("constructor Foo with x");
        this.i = x;
    }

    {
        System.out.println("init Foo");
        i = 2;
    }

    protected int getValue() {
        return i;
    }
}

class Bar extends Foo {
    int j = 1;

    Bar() {
        System.out.println("constructor Bar");
        j = 5;
        System.out.println(getValue());
    }

    {
        System.out.println("init Bar");
        j = 3;
    }

    @Override
    protected int getValue() {
        return j;
    }
}

public class ConstructorExample {
    public static void main(String... args) {
        Bar bar = new Bar();
        System.out.println(bar.getValue());
    }
}
