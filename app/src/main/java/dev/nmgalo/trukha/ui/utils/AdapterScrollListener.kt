package dev.nmgalo.trukha.ui.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdapterScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val onNext: () -> Unit
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (layoutManager.findLastCompletelyVisibleItemPosition() + 1 == layoutManager.itemCount)
            onNext()

    }
}
