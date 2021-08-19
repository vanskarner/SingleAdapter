package com.vanskarner.adapters.error_handling;

public class Result<T> {

    public static class Success<T> extends Result<T> {
        private final T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    public static class Error<T> extends Result<T> {
        private final ErrorEntity errorEntity;

        public Error(ErrorEntity errorEntity) {
            this.errorEntity = errorEntity;
        }

        public ErrorEntity getErrorEntity() {
            return errorEntity;
        }
    }

}
