package com.vanskarner.adapters.error_handling;

public class NoPages extends RuntimeException {

    public NoPages() {
        super("There are no more pages");
    }

}
