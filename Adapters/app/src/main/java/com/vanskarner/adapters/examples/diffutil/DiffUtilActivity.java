package com.vanskarner.adapters.examples.diffutil;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.SingleAdapter;
import com.vanskarner.adapters.examples.DataProvider;
import com.vanskarner.adapters.examples.WomanModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DiffUtilActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        SingleAdapter singleAdapter = new SingleAdapter();
        singleAdapter.add(new DiffAdapter());
        recyclerView.setAdapter(singleAdapter);
        Single.just(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(2, TimeUnit.SECONDS)
                .map(value -> DataProvider.sampleData())
                .subscribe(new SingleObserver<List<WomanModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("DiffActivty","onSubscribe");
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<WomanModel> womanModels) {
                        Log.d("DiffActivty","onSuccess"+womanModels.size());
                        singleAdapter.setList(womanModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DiffActivty","onError");
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }
}
