package com.vanskarner.adapters.examples.progress;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.LoadAdapter;
import com.vanskarner.adapters.common.adaptersothers.SingleAdapter;
import com.vanskarner.adapters.examples.DataProvider;
import com.vanskarner.adapters.examples.EndlessScrollRecyclListener;

public class ProgressActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_activity);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        SingleAdapter singleAdapter = new SingleAdapter();
        singleAdapter.add(new WomanAdapter());
        singleAdapter.add(new LoadAdapter(R.layout.item_loading));
        singleAdapter.changeList(DataProvider.sampleData());
        recyclerView.setAdapter(singleAdapter);
        recyclerView.addOnScrollListener(new EndlessScrollRecyclListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                singleAdapter.showProgress();
                showMessage("page-> " + page + " / totalItemsCount-> " + totalItemsCount);
/*                DataProvider.sampleData(page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<WomanModel>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@NonNull List<WomanModel> womanModels) {
                                singleAdapter.hideProgress();
                                singleAdapter.setList(womanModels);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });*/
            }
        });
    }

    public void showMessage(String message) {
        Log.d("showMessage->", message);
    }

}
