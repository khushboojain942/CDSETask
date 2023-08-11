package com.task.cdse.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.cdse.databinding.SearchResultLayoutBinding
import com.task.cdse.model.SearchEmployeeItemModel

class SearchEmployeeListAdapter(
     private var arrayList: MutableList<SearchEmployeeItemModel>
) : RecyclerView.Adapter<SearchEmployeeListAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            SearchResultLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val userItemModel = arrayList.get(position)
        holder.itemViewHolder.tNameSearchItem.text =
            "${userItemModel.firstName} ${userItemModel.lastName}"

        holder.itemViewHolder.tvGenderSearchItem.text =
            "Gender : ${userItemModel.gender}"

        holder.itemViewHolder.tvBirthSearchItem.text =
            "Birth Of Date : ${userItemModel.birthDate}"

        holder.itemViewHolder.tvSalarySearchItem.text =
            "Salary : ${userItemModel.salary}"

        holder.itemViewHolder.tvTitleSearchItem.text =
            "Designation : ${userItemModel.title}"


    }


    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ItemViewHolder(itemLayoutBinding: SearchResultLayoutBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {
        var itemViewHolder: SearchResultLayoutBinding = itemLayoutBinding

    }
}