package com.vanskarner.singleadapter;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.vanskarner.singleadapter.util.ExampleBindAdapter;
import com.vanskarner.singleadapter.util.ExampleDiffCallback;
import com.vanskarner.singleadapter.util.ExampleModel;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SingleAdapterTest {
    SingleAdapter singleAdapter;

    public List<ExampleModel> getData() {
        List<ExampleModel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) list.add(new ExampleModel("Element " + i));
        return list;
    }

    @Before
    public void setUp() {
        singleAdapter = new SingleAdapter();
    }

    @Test
    public void isLoad_showProgress_returnTrue() {
        int lastPosition = 0;
        singleAdapter.setVisibleProgress(true);

        assertTrue(singleAdapter.isLoad(lastPosition));
    }

    @Test
    public void isLoad_hideProgress_returnFalse() throws InterruptedException {
        int lastPosition = getData().size() - 1;
        singleAdapter.set(getData());
        singleAdapter.setVisibleProgress(false);
        //SingleAdapter uses AsyncListDiffer which does the calculation asynchronously
        Thread.sleep(250);

        assertFalse(singleAdapter.isLoad(lastPosition));
    }

    @Test
    public void addBindAdapter_isBindAdapterPresent_returnTrue() {
        ExampleBindAdapter exampleBindAdapter = new ExampleBindAdapter();
        singleAdapter.add(exampleBindAdapter);
        boolean actual = singleAdapter.isAdapterPresent(exampleBindAdapter);

        assertTrue(actual);
    }

    @Test
    public void isBindAdapterPresent_unregisteredBindAdapter_returnFalse() {
        ExampleBindAdapter exampleBindAdapter = new ExampleBindAdapter();
        boolean actual = singleAdapter.isAdapterPresent(exampleBindAdapter);

        assertFalse(actual);
    }

    @Test
    public void setItems_returnSameNumberList() {
        singleAdapter.set(getData());

        assertEquals(getData().size(), singleAdapter.getItemCount());
    }

    @Test
    public void setLoadLayoutId_returnSameId() {
        singleAdapter.set(R.layout.example_item);
        int actual = singleAdapter.getProgressLayoutId();

        assertEquals(R.layout.example_item, actual);
    }

    @Test
    public void getProgressLayoutId_withoutProgressLayout_returnDefaultProgressLayout() {
        int actual = singleAdapter.getProgressLayoutId();

        assertEquals(R.layout.progress_view, actual);
    }

    @Test
    public void getItem_returnSameAttributeValues() {
        int pos = 0;
        singleAdapter.set(getData());
        ExampleModel exampleModel = singleAdapter.getItem(pos);
        String actual = exampleModel.getName();
        String expected = getData().get(pos).getName();

        assertEquals(expected, actual);
    }

    @Test
    public void getDiffCallback_returnDiffCall() {
        singleAdapter.set(new ExampleDiffCallback());
        BaseDiffCallback<? extends BindItem> diffCallback = singleAdapter.getDiffCallback();

        assertTrue(diffCallback instanceof ExampleDiffCallback);
    }

    @Test
    public void getDiffCallback_withoutDiffCallback_returnDefaultDiffCallback() {
        BaseDiffCallback<? extends BindItem> diffCallback = singleAdapter.getDiffCallback();

        assertTrue(diffCallback instanceof DefaultBaseDiff);
    }
}