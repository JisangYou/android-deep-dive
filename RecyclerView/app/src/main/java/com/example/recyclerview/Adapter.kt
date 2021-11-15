package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemBinding

// ListAdapter는 RecyclerView.Adapter의 확장기능으로 리스트내에 노출할 아이템의 변경 여부를 백그라운드 쓰레드에서 판단할 수 있는 기능을 제공
// AsyncListDiffer를 더 쓰기 편하도록 랩핑한 클래스
// 초기화할때 DiffUtil 콜백 객체를 받도록 하면 나머지는 AsyncListDiffer와 같이 currentList로 현재 데이터를 불러올 수 있고, submitList로 데이터를 갱신
class Adapter : ListAdapter<ItemData, Adapter.ViewHolder>(DiffUtilItemCallback()) {

    var itemDataList = mutableListOf<ItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemDataList[position])
    }

    override fun getItemCount(): Int {
        return itemDataList.size
    }

    fun update() {
        // diffutil case
//        val diffCallback = DiffUtilCallback(mutableListOf<ItemData>(ItemData("1","jay")), mutableListOf<ItemData>(ItemData("2","you")))
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        diffResult.dispatchUpdatesTo(this) // dispatchUpdatesTo()로 RecyclerView에 변경 사항을 적용하기 전에 원본 리스트를 새로운 리스트로 교체하는 작업을 해 줘야 한다.

        // AsyncListDiffer
        // DiffUtil은 아이템 개수가 많을 경우 비교연산시간에 필요한 시간이 길어질 수 있기 때문에 백그라운드 스레드에서 처리하는데 이를 처리해주는 클래스가 AsyncListDiffer
//        submitList()를 호출하면 diffing 작업과 리스트 변경까지 진행하고,
//        currentList로 현재 리스트의 아이템들을 확인할 수 있다

        submitList(itemDataList)
    }

    class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemData) {
            binding.item = item
        }
    }
}