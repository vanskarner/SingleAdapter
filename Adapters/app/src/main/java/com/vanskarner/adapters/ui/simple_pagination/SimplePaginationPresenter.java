package com.vanskarner.adapters.ui.simple_pagination;

import com.vanskarner.adapters.models.MovieModel;
import com.vanskarner.adapters.ui.NoPages;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class SimplePaginationPresenter implements SimplePaginationContract.presenter {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final SimplePaginationContract.view view;
    private final SimplePaginationModel moviePaginationModel;

    public SimplePaginationPresenter(SimplePaginationContract.view view) {
        this.view = view;
        this.moviePaginationModel = new SimplePaginationModel();
    }

    @Override
    public void loadMore(int pageNumber) {
        if (pageNumber > 1) {
            view.showProgress();
        }
        moviePaginationModel.sampleData(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<MovieModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.clear();
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<MovieModel> movieModels) {
                        view.hideProgress();
                        if (pageNumber == 1) {
                            view.showNecessaryViews();
                        }
                        view.addList(movieModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.hideProgress();
                        if (e instanceof NoPages) {
                            view.showNoPages();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}
