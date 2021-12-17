package com.vanskarner.adapters.examples.diffutil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.SingleAdapter;
import com.vanskarner.adapters.examples.DataProvider;
import com.vanskarner.adapters.examples.WomanModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DiffUtilActivity extends AppCompatActivity {

    SingleAdapter singleAdapter = new SingleAdapter();
    List<WomanModel> otherList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diff_activity);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        singleAdapter.add(new DiffAdapter());
        recyclerView.setAdapter(singleAdapter);
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

    private void showChangeItemExample() {
        otherList = DataProvider.sampleData();
        singleAdapter.setList(otherList);
        scrollListWithDelay(DataProvider.sampleData(), item -> {
            otherList = new ArrayList<>(otherList);
            item.firstNameToUpperCase();
            otherList.set(item.getId() - 1, item);
            singleAdapter.setList(otherList);
        });
    }

    private void showRemoveItemExample() {
        otherList = DataProvider.sampleData();
        singleAdapter.setList(otherList);
        scrollListWithDelay(DataProvider.sampleData(), item -> {
            otherList = new ArrayList<>(otherList);
            otherList.remove(0);
            singleAdapter.setList(otherList);
        });
    }

    private void showAddItemExample() {
        List<WomanModel> reverseList = DataProvider.sampleData();
        Collections.reverse(reverseList);
        scrollListWithDelay(reverseList, item -> {
            otherList = new ArrayList<>(otherList);
            otherList.add(0, item);
            singleAdapter.setList(otherList);
        });
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void scrollListWithDelay(List<WomanModel> womanModels, Consumer<WomanModel> consumer) {
        Observable.fromIterable(womanModels)
                .concatMap(i -> Observable.just(i).delay(1, TimeUnit.SECONDS))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }
}
