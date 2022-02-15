package com.utility.adapters.examples.progress;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class Pagination extends RecyclerView.OnScrollListener {
    public static final int LAST_POSITION = 1;
    public static final int LAST_POSITION_COMPLETE = 2;

    private int pageNumber = 1;
    private boolean isLoading = false;
    private final OnLoadMoreListener onLoadMoreListener;
    private final int positionType;

    public interface OnLoadMoreListener {
        void onLoadMore(int page);
    }

    public Pagination(@NonNull OnLoadMoreListener onLoadMoreListener, int positionType) {
        this.onLoadMoreListener = onLoadMoreListener;
        this.positionType = positionType == LAST_POSITION ? LAST_POSITION : LAST_POSITION_COMPLETE;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void onLoadMore() {
        onLoadMoreListener.onLoadMore(pageNumber);
    }

    public void reset() {
        pageNumber = 1;
        isLoading = false;
    }

    public void increment() {
        pageNumber++;
        setLoading(false);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (isConditionForScrolled(recyclerView.getLayoutManager(), dy)) {
            onLoadMoreListener.onLoadMore(pageNumber);
        }
    }

    private boolean isConditionForScrolled(RecyclerView.LayoutManager manager, int dy) {
        return isLayoutManagerNotNull(manager) &&
                isScrollingUp(dy) &&
                isLastPosition(manager) &&
                !isLoading;
    }

    private boolean isLayoutManagerNotNull(RecyclerView.LayoutManager manager) {
        return manager != null;
    }

    private boolean isScrollingUp(int dy) {
        return dy > 0;
    }

    private boolean isLastPosition(RecyclerView.LayoutManager manager) {
        return (getLastPosition(manager) == manager.getItemCount() - 1);
    }

    private int getLastPosition(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            return lastPositionGrid((GridLayoutManager) layoutManager);
        } else if (layoutManager instanceof LinearLayoutManager) {
            return lastPositionLinear((LinearLayoutManager) layoutManager);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return lastPositionStaggered((StaggeredGridLayoutManager) layoutManager);
        }
        return 0;
    }

    private int lastPositionGrid(@NonNull GridLayoutManager manager) {
        return (positionType == LAST_POSITION) ?
                manager.findLastVisibleItemPosition() :
                manager.findLastCompletelyVisibleItemPosition();
    }

    private int lastPositionLinear(@NonNull LinearLayoutManager manager) {
        return (positionType == LAST_POSITION) ?
                manager.findLastVisibleItemPosition() :
                manager.findLastCompletelyVisibleItemPosition();
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

}
