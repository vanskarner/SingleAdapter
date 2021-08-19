package com.vanskarner.adapters.error_handling;

public interface ErrorHandler {
    ErrorEntity getError(Throwable throwable);
}
