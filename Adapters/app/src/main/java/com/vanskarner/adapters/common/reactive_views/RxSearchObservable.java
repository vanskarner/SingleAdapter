package com.vanskarner.adapters.common.reactive_views;

import androidx.appcompat.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxSearchObservable {
    private RxSearchObservable() {
        // no instance
    }

    public static Observable<String> fromView(SearchView searchView) {

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                subject.onNext(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                subject.onNext(text);
                return false;
            }
        });

        return subject;
    }
}
