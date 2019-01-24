package com.vetkoli.sanket.standapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_estimation_cards.*

class EstimationCardsActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, EstimationCardsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estimation_cards)
    }

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }

}
