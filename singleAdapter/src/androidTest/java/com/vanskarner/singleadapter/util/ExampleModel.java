package com.vanskarner.singleadapter.util;

import com.vanskarner.singleadapter.BindItem;

public class ExampleModel implements BindItem {
    private final String name;

    public ExampleModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}