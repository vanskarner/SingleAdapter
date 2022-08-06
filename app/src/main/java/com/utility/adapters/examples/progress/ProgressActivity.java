package com.utility.adapters.examples.progress;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.utility.adapters.R;

import com.utility.adapters.examples.DataProvider;
import com.utility.adapters.examples.WomanModel;
import com.vanskarner.singleadapter.SingleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProgressActivity extends AppCompatActivity implements Pagination.OnLoadMoreListener {
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
        singleAdapter.set(list);
        recyclerView.setAdapter(singleAdapter);
        recyclerView.addOnScrollListener(pagination);
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        Objects.requireNonNull(gridLayoutManager)
                .setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (singleAdapter.isLoad(position)) ? 2 : 1;
                    }
                });
        pagination.onLoadMore();
    }

    @Override
    public void onLoadMore(int page) {
        if (page <= PAGE_LIMIT) {
            if (page != 1) {
                singleAdapter.setVisibleProgress(true);
            }
            pagination.setLoading(true);
            compositeDisposable.add(Single.just(DataProvider.sampleDataMsg(" - " + page))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .delay(1, TimeUnit.SECONDS)
                    .subscribe(newItems -> {
                        list.addAll(newItems);
                        singleAdapter.set(list);
                        pagination.increment();
                    }));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }
}
