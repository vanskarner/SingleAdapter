package com.vanskarner.adapters.ui.simple_search;

import android.widget.Filter;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.bases.BaseActivity;
import com.vanskarner.adapters.common.reactive_views.RxSearchObservable;
import com.vanskarner.adapters.models.PersonModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SimpleSearchActivity extends BaseActivity implements SimpleSearchContract.view {

    SearchView searchView;
    RecyclerView recyclerView;
    SimpleSearchAdapter simpleSearchAdapter;
    List<PersonModel> personModels = new ArrayList<>();
    SimpleSearchContract.presenter presenter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected int setLayout() {
        return R.layout.simple_search_activity;
    }

    @Override
    protected void setupView() {
        recyclerView = findViewById(R.id.recyclerMovies);
        searchView = findViewById(R.id.searchViewMovies);
        simpleSearchAdapter = new SimpleSearchAdapter(personModels);
        recyclerView.setAdapter(simpleSearchAdapter);
        simpleSearchAdapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            PersonModel model = personModels.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });
        Disposable disposable = RxSearchObservable.fromView(searchView)
                .debounce(350, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Filter filter = simpleSearchAdapter.getFilter();
                    filter.filter(s);
                });
        compositeDisposable.add(disposable);

        //presenter initialization
        presenter = new SimpleSearchPresenter(this);
        presenter.loadList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    //Contract Methods

    @Override
    public void loadList(List<PersonModel> list) {
        simpleSearchAdapter.addList(list);
    }

}
