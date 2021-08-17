package com.vanskarner.adapters.ui;

import android.util.Log;

import com.vanskarner.adapters.MovieModel;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviePaginationPresenter implements MoviePaginationContract.presenter {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MoviePaginationContract.view view;
    private final MoviePaginationModel moviePaginationModel;

    public MoviePaginationPresenter(MoviePaginationContract.view view) {
        this.view = view;
        this.moviePaginationModel = new MoviePaginationModel();
    }

    @Override
    public void loadMore(int pageNumber, boolean isInitialData) {
        if (!isInitialData) {
            view.showProgress();
        }
        moviePaginationModel.sampleData(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<MovieModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<MovieModel> movieModels) {
                        view.hideProgress();
                        view.addList(movieModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.hideProgress();
                        Log.d("Throwable->", e.getMessage());
                    }
                });
    }


    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}