package com.programmer2704.movapp.usage

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Pagination class to add more items to the list when reach the last item.
 */
abstract class PaginationListener
/**
 * Supporting only LinearLayoutManager for now.
 *
 * @param layoutManager
 */
    (var gridLayoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = gridLayoutManager.childCount
        val totalItemCount = gridLayoutManager.itemCount
        val firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }//                    && totalItemCount >= ClothesFragment.itemsCount
        }
    }

    abstract fun loadMoreItems()
}