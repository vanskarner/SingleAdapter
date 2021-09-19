package com.vanskarner.adapters.common.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class Pagination extends RecyclerView.OnScrollListener {
    private static final int LINEAR = 1;
    private static final int STAGGERED = 2;
    public static final int LAST_POSITION = 1;
    public static final int LAST_POSITION_COMPLETE = 2;

    public int pageNumber = 1;
    public boolean isLoading = false;
    private final OnLoadMoreListener onLoadMoreListener;
    private final int layoutManagerType;
    private final int positionType;

    private Pagination(OnLoadMoreListener onLoadMoreListener, int layoutManagerType,
                       int positionType) {
        this.onLoadMoreListener = onLoadMoreListener;
        this.layoutManagerType = layoutManagerType;
        this.positionType = filterPositionType(positionType);
    }

    private static int filterPositionType(int positionType) {
        return (positionType != LAST_POSITION && positionType != LAST_POSITION_COMPLETE) ?
                LAST_POSITION_COMPLETE : positionType;
    }

    private int lastPositionStaggered(RecyclerView.LayoutManager layoutManager) {
        StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
        int[] lastPositions = (positionType == LAST_POSITION) ?
                manager.findLastVisibleItemPositions(null) :
                manager.findLastCompletelyVisibleItemPositions(null);
        if (lastPositions == null || lastPositions.length <= 0) {
            return 0;
        }
        int max = lastPositions[0];
        for (int value : lastPositions) {
            max = Math.max(max, value);
        }
        return max;
    }

    private int lastPositionLinear(RecyclerView.LayoutManager layoutManager) {
        LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
        return (positionType == LAST_POSITION) ?
                manager.findLastVisibleItemPosition() :
                manager.findLastCompletelyVisibleItemPosition();
    }

    private boolean conditionForScrolled(RecyclerView.LayoutManager manager) {
        int lastPosition = (layoutManagerType == LINEAR) ?
                lastPositionLinear(manager) : lastPositionStaggered(manager);
        return (!isLoading && manager != null && lastPosition == manager.getItemCount() - 1);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (conditionForScrolled(manager)) {
            pageNumber++;
            isLoading = true;
            onLoadMoreListener.loadMore();
        }
    }

    public static Pagination createWithLinear(OnLoadMoreListener onLoadMoreListener,
                                              int positionType) {
        return new Pagination(onLoadMoreListener, LINEAR, positionType);
    }

    public static Pagination createWithStaggered(OnLoadMoreListener onLoadMoreListener,
                                                 int positionType) {
        return new Pagination(onLoadMoreListener, STAGGERED, positionType);
    }

    public interface OnLoadMoreListener {
        void loadMore();
    }

}
