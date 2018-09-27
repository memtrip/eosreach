package com.memtrip.eosreach.uikit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.RxView
import com.memtrip.eosreach.R
import io.reactivex.subjects.PublishSubject

abstract class SimpleAdapter<T>(
    context: Context,
    val interaction: PublishSubject<Interaction<T>>,
    protected val inflater: LayoutInflater = LayoutInflater.from(context),
    internal val data: MutableList<T> = ArrayList(),
    private val marginSize: Int = context.resources.getDimensionPixelOffset(R.dimen.padding_large)
) : RecyclerView.Adapter<SimpleAdapterViewHolder<T>>() {

    fun populate(items: List<T>) {
        this.data.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleAdapterViewHolder<T> {

        val viewHolder = createViewHolder(parent)

        RxView.clicks(viewHolder.itemView).map {
            Interaction(viewHolder.itemView.id, data[viewHolder.adapterPosition])
        }.subscribe(interaction)

        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: SimpleAdapterViewHolder<T>, position: Int) {
        viewHolder.populate(position, data[position])
        defaultRowMargin(position, viewHolder.itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return DEFAULT_ITEM_TYPE
    }

    abstract fun createViewHolder(parent: ViewGroup): SimpleAdapterViewHolder<T>

    private fun defaultRowMargin(position: Int, view: View) {

        if (position == 0) {
            (view.layoutParams as RecyclerView.LayoutParams).topMargin = 0
        } else {
            (view.layoutParams as RecyclerView.LayoutParams).topMargin = marginSize
        }

        if (position == data.size - 1) {
            (view.layoutParams as RecyclerView.LayoutParams).bottomMargin = marginSize
        } else {
            (view.layoutParams as RecyclerView.LayoutParams).bottomMargin = 0
        }
    }

    fun atEnd(id: Int) {
        interaction.onNext(Interaction(id, data[data.size - 1]))
    }

    companion object {
        private const val DEFAULT_ITEM_TYPE = 0x100
    }
}

abstract class SimpleAdapterViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun populate(position: Int, value: T)

    fun getString(@StringRes id: Int): String {
        return itemView.context.getString(id)
    }
}

data class Interaction<T>(@IdRes val id: Int, val data: T)