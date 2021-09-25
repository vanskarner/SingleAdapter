package com.vanskarner.adapters.common.adapterv2;

import androidx.annotation.NonNull;

import java.util.List;

public interface AdapterOperations {

    interface Endless {

        boolean isVisibleProgress();

        void showProgress();

        void hideProgress();

    }

    interface Add<T> {

        void addList(@NonNull List<T> listAdd);

    }

    interface Change<T> {

        void changeList(@NonNull List<T> newList);

    }

}