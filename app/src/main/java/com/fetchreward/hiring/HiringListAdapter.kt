package com.fetchreward.hiring

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fetchreward.hiring.databinding.ItemHiringBinding
import com.fetchreward.hiring.model.HiringItem

class HiringListAdapter(private val clickedItem: (HiringItem) -> Unit):
    RecyclerView.Adapter<HiringListAdapter.HiringViewHolder>() {

    private var hiringList: List<HiringItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HiringViewHolder {
        val binding = ItemHiringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HiringViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HiringViewHolder, position: Int) {
        holder.bind(hiringList[position])
    }

    override fun getItemCount(): Int {
        return hiringList.size
    }

    fun setHiringList(list: List<HiringItem> ) {
        hiringList = list
        notifyDataSetChanged()
    }

    inner class HiringViewHolder (private val binding: ItemHiringBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: HiringItem) {
                binding.hiringListId.text = binding.root.context.getString(R.string.id, item.id.toString())
                binding.hiringListListId.text = binding.root.context.getString(R.string.listId, item.listId.toString())
                binding.hiringListName.text = binding.root.context.getString(R.string.name, item.name)
                binding.root.setOnClickListener {
                    clickedItem(item)
                }
            }
    }
}