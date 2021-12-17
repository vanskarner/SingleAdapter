package com.vanskarner.adapters.examples.progress;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.SingleAdapter;
import com.vanskarner.adapters.examples.DataProvider;
import com.vanskarner.adapters.examples.WomanModel;


import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProgressActivity extends AppCompatActivity implements Pagination.OnLoadMoreListener {
    private static String TAG = "DEBUGPROGRES";

    Pagination pagination = new Pagination(this, Pagination.LAST_POSITION);
    RecyclerView recyclerView;
    List<WomanModel> list = DataProvider.sampleDataMsg("- 1 -");
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
    }

    public void showNoPages() {
        Snackbar.make(findViewById(R.id.contentProgress),
                getString(R.string.exception_no_items), Snackbar.LENGTH_SHORT)
                .show();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    @Override
    public void onLoadMore(int page) {
        singleAdapter.showProgress();
        Single.just(DataProvider.sampleData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(1, TimeUnit.SECONDS)
                .subscribe(new SingleObserver<List<WomanModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<WomanModel> list) {
                        //singleAdapter.hideProgress();
                        singleAdapter.setList(list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, e.getMessage());
                    }
                });

    }
}
