package com.vanskarner.adapters.ui.staggered_pagination;

import androidx.annotation.NonNull;

import com.vanskarner.adapters.models.PersonModel;
import com.vanskarner.adapters.ui.NoPages;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class StaggeredPaginationPresenter implements StaggeredPaginationContract.presenter {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final StaggeredPaginationContract.view view;
    private final StaggeredPaginationModel model;

    public StaggeredPaginationPresenter(StaggeredPaginationContract.view view) {
        this.view = view;
        this.model = new StaggeredPaginationModel();
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
    public void onDestroy() {
        compositeDisposable.clear();
    }

}
