package com.vetkoli.sanket.standapp.estimation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_estimation_cards.*

class EstimationCardsActivity : BaseActivity() {

    private val estimatePoints: List<String> by unsafeLazy { arrayListOf("0.5", "1", "2", "3", "5", "8", "13", "21", "34") }

    companion object {
        fun newIntent(context: Context) = Intent(context, EstimationCardsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estimation_cards)

        init()
    }

    private fun init() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rvEstimationCards.adapter = EstimationCardsAdapter(estimatePoints)
    }

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, rvEstimationCards)
    }

}
