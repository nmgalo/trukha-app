package dev.nmgalo.trukha.ui.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdapterScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val onNext: () -> Unit
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val first = layoutManager.findFirstVisibleItemPosition()
        if (layoutManager.childCount + first >= layoutManager.itemCount && first >= 0) {
            onNext()
        }
    }
}
