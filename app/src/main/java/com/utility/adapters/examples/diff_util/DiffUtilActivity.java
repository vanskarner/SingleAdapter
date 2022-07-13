package com.utility.adapters.examples.diff_util;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.utility.adapters.R;

import com.utility.adapters.examples.DataProvider;
import com.utility.adapters.examples.WomanModel;
import com.vanskarner.singleadapter.SingleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DiffUtilActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<WomanModel> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diff_activity);
        recyclerView = findViewById(R.id.recycler);
        showAddItemExample();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.diffutil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addItem) {
            showAddItemExample();
        } else if (item.getItemId() == R.id.removeItem) {
            showRemoveItemExample();
        } else {
            showChangeItemExample();
        }
        return false;
    }

    private void showAddItemExample() {
        compositeDisposable.clear();
        list = new ArrayList<>();
        SingleAdapter singleAdapter = new SingleAdapter();
        singleAdapter.add(new DiffAdapter());
        singleAdapter.set(list);
        recyclerView.setAdapter(singleAdapter);
        scrollListWithDelay(reverseList(), item -> {
            list.add(0, item);
            singleAdapter.set(list);
        });
    }

    private void showRemoveItemExample() {
        compositeDisposable.clear();
        list = DataProvider.sampleData();
        SingleAdapter singleAdapter = new SingleAdapter();
        singleAdapter.add(new DiffAdapter());
        singleAdapter.set(list);
        recyclerView.setAdapter(singleAdapter);
        scrollListWithDelay(DataProvider.sampleData(), item -> {
            list = new ArrayList<>(list);
            list.remove(0);
            singleAdapter.set(list);
        });
    }

    private void showChangeItemExample() {
        compositeDisposable.clear();
        list = DataProvider.sampleData();
        SingleAdapter singleAdapter = new SingleAdapter();
        singleAdapter.add(new DiffAdapter());
        singleAdapter.set(list);
        recyclerView.setAdapter(singleAdapter);
        scrollListWithDelay(DataProvider.sampleData(), item -> {
            item.firstNameToUpperCase();
            list.set(item.getId() - 1, item);
            singleAdapter.set(list);
        });
    }

    private void scrollListWithDelay(List<WomanModel> womanModels, Consumer<WomanModel> consumer) {
        compositeDisposable.add(Observable.fromIterable(womanModels)
                .concatMap(i -> Observable.just(i).delay(1, TimeUnit.SECONDS))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer));
    }

    private List<WomanModel> reverseList() {
        List<WomanModel> reverseList = DataProvider.sampleData();
        Collections.reverse(reverseList);
        return reverseList;
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }
}
