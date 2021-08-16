package com.vanskarner.adapters.ui;

import android.util.Log;
import android.widget.Toast;

import com.vanskarner.adapters.MovieModel;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviePaginationPresenter implements MoviePaginationContract.presenter {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MoviePaginationContract.view view;
    private MoviePaginationInteractor moviePaginationInteractor;

    public MoviePaginationPresenter(MoviePaginationContract.view view, MoviePaginationInteractor moviePaginationInteractor) {
        this.view = view;
        this.moviePaginationInteractor = moviePaginationInteractor;
    }

    @Override
    public void loadMore(int pageNumber) {
        view.showProgress();
        moviePaginationInteractor.dataFromNetwork(pageNumber)
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
