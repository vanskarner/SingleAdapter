package com.vanskarner.adapters.ui.search_pagination_custom;

import com.vanskarner.adapters.models.MovieModel;
import com.vanskarner.adapters.ui.NoPages;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class SearchPaginationPresenter implements SearchPaginationContract.presenter {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final SearchPaginationContract.view view;
    private final SearchPaginationModel searchPaginationModel;

    public SearchPaginationPresenter(SearchPaginationContract.view view) {
        this.view = view;
        this.searchPaginationModel = new SearchPaginationModel();
    }

    @Override
    public void loadMore(int pageNumber) {
        if (pageNumber > 1) {
            view.showProgress();
        }
        searchPaginationModel.sampleData(pageNumber)
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
                            view.initializeView();
                        }
                        view.addList(movieModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.hideProgress();
                        if (e instanceof NoPages){
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
