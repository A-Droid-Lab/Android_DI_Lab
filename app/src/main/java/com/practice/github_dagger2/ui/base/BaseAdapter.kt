package com.practice.github_dagger2.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH: RecyclerView.ViewHolder, in D>:  RecyclerView.Adapter<VH>() {

    abstract fun setData(dataList: List<D>)
}
