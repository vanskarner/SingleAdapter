package com.vanskarner.adapters.common.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class PaginationListener2 extends RecyclerView.OnScrollListener {
    private static final int LINEAR = 1;
    private static final int STAGGERED = 2;
    public static final int LAST_POSITION = 1;
    public static final int LAST_POSITION_COMPLETE = 2;

    public int pageNumber = 1;
    public boolean isLoading = false;
    private final Scrolled scrolled;
    private final int layoutManagerType;
    private final int positionType;

    private PaginationListener2(Scrolled scrolled, int layoutManagerType, int positionType) {
        this.scrolled = scrolled;
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
            scrolled.loadMore();
        }
    }

    public static PaginationListener2 createWithLinear(Scrolled scrolled, int positionType) {
        return new PaginationListener2(scrolled, LINEAR, positionType);
    }

    public static PaginationListener2 createWithStaggered(Scrolled scrolled, int positionType) {
        return new PaginationListener2(scrolled, STAGGERED, positionType);
    }

    public interface Scrolled {
        void loadMore();
    }

}
