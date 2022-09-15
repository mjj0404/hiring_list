package com.fetchreward.hiring.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fetchreward.hiring.R
import com.fetchreward.hiring.databinding.ItemHiringBinding
import com.fetchreward.hiring.model.HiringItem

/**
 * Two lists used, one to keep original list of HiringItem,
 * the other is used to present the filtered list of the original
 */
class HiringListAdapter(private val clickedItem: (HiringItem) -> Unit):
    RecyclerView.Adapter<HiringListAdapter.HiringViewHolder>() {

    private var hiringList: List<HiringItem> = emptyList()
    private var filteredHiringList: List<HiringItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HiringViewHolder {
        val binding = ItemHiringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HiringViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HiringViewHolder, position: Int) {
        holder.bind(filteredHiringList[position])
    }

    override fun getItemCount(): Int {
        return filteredHiringList.size
    }

    fun setHiringList(list: List<HiringItem> ) {
        hiringList = list
        filteredHiringList = list
        notifyDataSetChanged()
    }

    /**
     * The function receives search query, in String format
     * and apply filters to the original list, and store it to the 'filteredHiringList'
     * @param text : search query from SearchView
     */
    fun setSearchFilter(text: String) {
        filteredHiringList = if (!text.isNullOrBlank())
            hiringList.filter { it.name.contains(text) }
        else
            hiringList
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