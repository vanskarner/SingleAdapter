package com.vanskarner.adapters.common.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class PaginationListener extends RecyclerView.OnScrollListener {
    public int pageNumber = 1;
    public boolean isLoading = false;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (!isLoading && manager != null &&
                itemPositionForLoading(manager) == manager.getItemCount() - 1) {
            pageNumber++;
            loadMore(pageNumber);
            isLoading = true;
        }
    }

    /**
     * Returns the last position of the fully visible item from StaggeredGridLayoutManager
     *
     * @param manager instance of StaggeredGridLayoutManager
     * @return last position of the fully visible item
     */
    public static int lastPositionStaggeredGrid(StaggeredGridLayoutManager manager) {
        int[] lastPositions = manager.findLastCompletelyVisibleItemPositions(null);
        if (lastPositions == null || lastPositions.length <= 0) {
            return 0;
        }
        int max = lastPositions[0];
        for (int value : lastPositions) {
            max = Math.max(max, value);
        }
        return max;
    }

    /**
     * Use the findLastCompletelyVisibleItemPosition() method by default for LinearLayoutManager
     * or GridLayoutManager
     *
     * @param manager instance of RecyclerView.LayoutManager
     * @return last item position fully visible
     */
    protected int itemPositionForLoading(RecyclerView.LayoutManager manager) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
        return linearLayoutManager.findLastCompletelyVisibleItemPosition();
    }

    protected abstract void loadMore(int pageNumber);

}
