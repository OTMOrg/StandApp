package com.vetkoli.sanket.standapp.estimation

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.utils.inflate
import kotlinx.android.synthetic.main.item_estimation_cards.view.*

/**
 * Created by Sanket on 25/01/19.
 */
class EstimationCardsAdapter(private val items: List<String>): RecyclerView.Adapter<EstimationCardsAdapter.EstimationCardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EstimationCardsViewHolder {
        return EstimationCardsViewHolder(parent!!.inflate(R.layout.item_estimation_cards))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: EstimationCardsViewHolder?, position: Int) {
        holder?.bind(items[position])
    }


    class EstimationCardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(points: String) {
            itemView.tvStoryPoints.text = points
        }

    }

}