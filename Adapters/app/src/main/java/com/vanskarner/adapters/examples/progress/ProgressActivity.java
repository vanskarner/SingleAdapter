package com.vanskarner.adapters.examples.progress;

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
import com.vanskarner.adapters.ui.NoPages;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ProgressActivity extends AppCompatActivity implements Pagination.OnLoadMoreListener {

    Pagination pagination = new Pagination(this, Pagination.LAST_POSITION);
    List<WomanModel> list = new ArrayList<>();
    SingleAdapter singleAdapter = new SingleAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_activity);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        singleAdapter.add(new WomanAdapter());
        singleAdapter.add(R.layout.item_loading);
        //singleAdapter.changeList(DataProvider.sampleDataMsg("- 1 -"));
        recyclerView.setAdapter(singleAdapter);
        recyclerView.addOnScrollListener(pagination);
    }

    public void showNoPages() {
        Snackbar.make(findViewById(R.id.contentProgress),
                getString(R.string.exception_no_items), Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onLoadMore(int page) {
        singleAdapter.showProgress();
        DataProvider
                .sampleDataPaging(page)
                .subscribe(new SingleObserver<List<WomanModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<WomanModel> personModels) {
                        pagination.isLoading = false;
                        //singleAdapter.changeList(personModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        pagination.isLoading = false;
                        singleAdapter.hideProgress();
                        if (e instanceof NoPages) {
                            showNoPages();
                        }
                    }
                });
    }
}
