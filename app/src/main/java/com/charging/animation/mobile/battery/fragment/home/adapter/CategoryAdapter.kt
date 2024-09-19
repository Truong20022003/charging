package com.charging.animation.mobile.battery.fragment.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.databinding.ItemFragmentHomeItemCategoryBinding
import com.charging.animation.mobile.battery.fragment.home.model.CategoryModel
import com.charging.animation.mobile.battery.listener.Listener
import com.charging.animation.mobile.battery.util.Constants

class CategoryAdapter(private var context: Context, var listener: Listener) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var list: MutableList<CategoryModel> = mutableListOf()
    private var positionOld: Int = 0
    init {


        list.add(CategoryModel(context.getString(R.string.black), Constants.Black, false))
        list.add(CategoryModel(context.getString(R.string.cat), Constants.Cat, false))
        list.add(CategoryModel(context.getString(R.string.chill), Constants.Chill, false))
        list.add(CategoryModel(context.getString(R.string.fantasy), Constants.Fantasy, false))
        list.add(CategoryModel(context.getString(R.string.fire), Constants.Fire, false))
        list.add(CategoryModel(context.getString(R.string.flower), Constants.Flower, false))
        list.add(CategoryModel(context.getString(R.string.hand_drawing), Constants.Handdrawing, false))
        list.add(CategoryModel(context.getString(R.string.horror), Constants.Horror, false))
        list.add(CategoryModel(context.getString(R.string.lofi), Constants.Lofi, false))
        list.add(CategoryModel(context.getString(R.string.neon), Constants.Neon, false))
        list.add(CategoryModel(context.getString(R.string.animal), Constants.Animal, false))

        list.shuffle()
        list.add(0,CategoryModel(context.getString(R.string.recommend), Constants.Recommend, false))
        list.add(0,CategoryModel(context.getString(R.string.all), Constants.All, true))

    }

    class CategoryViewHolder(val binding: ItemFragmentHomeItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CategoryModel, context: Context) {
            binding.tvNameCategory.text = data.name
            if (data.check) {
                binding.bgCarView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.color_DE1BF2
                    )
                )
                binding.tvNameCategory.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )

            } else {
                binding.bgCarView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.color_262140
                    )
                )
                binding.tvNameCategory.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.color_92909F
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fragment_home_item_category, parent, false)
        return CategoryViewHolder(ItemFragmentHomeItemCategoryBinding.bind(view))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            refresh(position)
            listener.onClickCategory(list[position])
        }
        holder.bind(list[position], context = context)
    }

    private fun refresh(position: Int) {
        if (position != positionOld) {
            list[positionOld].check = false
            notifyItemChanged(positionOld)
            positionOld = position
            list[positionOld].check = true
            notifyItemChanged(positionOld)
        }
    }
}