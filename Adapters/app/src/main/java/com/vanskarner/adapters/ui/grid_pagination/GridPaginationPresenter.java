package com.vanskarner.adapters.ui.grid_pagination;

import androidx.annotation.NonNull;

import com.vanskarner.adapters.models.PersonModel;
import com.vanskarner.adapters.ui.NoPages;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class GridPaginationPresenter implements GridPaginationContract.presenter {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final GridPaginationContract.view view;
    private final GridPaginationModel model;

    public GridPaginationPresenter(GridPaginationContract.view view) {
        this.view = view;
        this.model = new GridPaginationModel();
    }

    @Override
    public void loadMore(int pageNumber) {
        model.sampleData(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PersonModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.clear();
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<PersonModel> personModels) {
                        view.hideProgress();
                        view.addList(personModels);
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
    public void unsubscribe() {
        compositeDisposable.clear();
    }
}
