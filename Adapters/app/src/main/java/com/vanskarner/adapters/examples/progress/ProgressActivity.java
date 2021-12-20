package com.vanskarner.adapters.examples.progress;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adapters.Pagination;
import com.vanskarner.adapters.common.adaptersothers.SingleAdapter;
import com.vanskarner.adapters.examples.DataProvider;
import com.vanskarner.adapters.examples.WomanModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProgressActivity extends AppCompatActivity implements Pagination.OnLoadMoreListener {
    private final static String TAG = "DEBUGPROGRES";
    private final static int PAGE_LIMIT = 4;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    Pagination pagination = new Pagination(this, Pagination.LAST_POSITION);
    RecyclerView recyclerView;
    List<WomanModel> list = new ArrayList<>();
    SingleAdapter singleAdapter = new SingleAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_activity);
        recyclerView = findViewById(R.id.recycler);
        singleAdapter.add(new WomanAdapter());
        singleAdapter.add(R.layout.item_loading);
        singleAdapter.setList(list);
        recyclerView.setAdapter(singleAdapter);
        recyclerView.addOnScrollListener(pagination);
        /*GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        Objects.requireNonNull(gridLayoutManager)
                .setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (singleAdapter.isVisibleProgress()) ? 1 : 2;
                    }
                });*/
        pagination.onLoadMore();
    }


    @Override
    public void onLoadMore(int page) {
        if (page <= PAGE_LIMIT) {
            if (page != 1) {
                singleAdapter.showProgress();
            }
            /*compositeDisposable.add(Single.just(DataProvider.sampleDataMsg(" - " + page))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .delay(1, TimeUnit.SECONDS)
                    .subscribe(newItems -> {
                        pagination.isLoading = false;
                        list.addAll(newItems);
                        singleAdapter.setList(list);
                        *//*singleAdapter.hideProgress();*//*
                    }));*/
            Single.just(DataProvider.sampleDataMsg(" - " + page))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .delay(1, TimeUnit.SECONDS)
                    .subscribe(new SingleObserver<List<WomanModel>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(@NonNull List<WomanModel> newItems) {
                            pagination.isLoading = false;
                            list.addAll(newItems);
                            singleAdapter.setList(list);
                            /*singleAdapter.hideProgress();*/
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d(TAG, e.getMessage());
                        }
                    });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }
}
