package com.vetkoli.sanket.standapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vetkoli.sanket.standapp.application.Constants
import kotlinx.android.synthetic.main.activity_story_points.*

class StoryPointsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_points)
        supportPostponeEnterTransition()
        init()
    }

    private fun init() {
        initViews()
    }

    private fun initViews() {
        tvStoryPoints.text = intent.getStringExtra(Constants.BUNDLE_KEYS.STORY_POINTS)
        supportStartPostponedEnterTransition()
    }

    companion object {
        fun newIntent(context: Context, storyPoints: String): Intent {
            val intent = Intent(context, StoryPointsActivity::class.java)
            intent.putExtra(Constants.BUNDLE_KEYS.STORY_POINTS, storyPoints)
            return intent
        }
    }
}
