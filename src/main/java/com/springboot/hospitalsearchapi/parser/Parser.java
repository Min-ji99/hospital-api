package com.springboot.hospitalsearchapi.parser;

public interface Parser<T> {
    T parse(String str);
}
