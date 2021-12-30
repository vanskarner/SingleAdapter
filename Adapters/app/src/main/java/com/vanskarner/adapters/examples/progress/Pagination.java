package com.vanskarner.adapters.examples.progress;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class Pagination extends RecyclerView.OnScrollListener {
    public static final int LAST_POSITION = 1;
    public static final int LAST_POSITION_COMPLETE = 2;

    public int pageNumber = 1;
    public boolean isLoading = false;
    private final OnLoadMoreListener onLoadMoreListener;
    private final int positionType;

    public Pagination(@NonNull OnLoadMoreListener onLoadMoreListener, int positionType) {
        this.onLoadMoreListener = onLoadMoreListener;
        this.positionType = filterPositionType(positionType);
    }

    private static int filterPositionType(int positionType) {
        return (positionType != LAST_POSITION && positionType != LAST_POSITION_COMPLETE) ?
                LAST_POSITION_COMPLETE : positionType;
    }

    private int lastPositionStaggered(@NonNull StaggeredGridLayoutManager manager) {
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

    private int lastPositionLinear(@NonNull LinearLayoutManager manager) {
        return (positionType == LAST_POSITION) ?
                manager.findLastVisibleItemPosition() :
                manager.findLastCompletelyVisibleItemPosition();
    }

    private int lastPositionGrid(@NonNull GridLayoutManager manager) {
        return (positionType == LAST_POSITION) ?
                manager.findLastVisibleItemPosition() :
                manager.findLastCompletelyVisibleItemPosition();
    }

    private int getLastPosition(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            if (layoutManager instanceof GridLayoutManager) {
                return lastPositionGrid((GridLayoutManager) layoutManager);
            }
            return lastPositionLinear((LinearLayoutManager) layoutManager);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return lastPositionStaggered((StaggeredGridLayoutManager) layoutManager);
        }
        return 0;
    }

    private boolean conditionForScrolled(@NonNull RecyclerView.LayoutManager manager) {
        int lastPosition = getLastPosition(manager);
        return (lastPosition == manager.getItemCount() - 1);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (!isLoading && manager != null && conditionForScrolled(manager)) {
            isLoading = true;
            onLoadMoreListener.onLoadMore(pageNumber);
            pageNumber++;
        }
    }

    public void onLoadMore() {
        isLoading = true;
        onLoadMoreListener.onLoadMore(pageNumber);
        pageNumber++;
    }

    public void reset() {
        pageNumber = 1;
        isLoading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int page);
    }

}
