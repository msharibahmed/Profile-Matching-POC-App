package com.msharibahmed.shaadidotcomassignment.presentation.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.msharibahmed.shaadidotcomassignment.R
import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.databinding.ActivityMainBinding
import com.msharibahmed.shaadidotcomassignment.presentation.adapter.UserMatchAdapter
import com.msharibahmed.shaadidotcomassignment.presentation.viewmodel.MainViewModel
import com.msharibahmed.shaadidotcomassignment.utils.events.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var rvAdapter: UserMatchAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.getMatchProfiles()
        subscribeObservers()
        setupRecyclerView()
        setupChangeProfileStatusListener()

    }

    private fun subscribeObservers() {
        viewModel.responseState.observe(this) { responseState ->
            when (responseState) {
                is ResponseState.Loading -> {
                    displayLoading(true)
                }
                is ResponseState.Success<List<MatchProfile>> -> {
                    displayLoading(false)
                    populateRecyclerView(responseState.data)
                }
                is ResponseState.Error -> {
                    displayLoading(false)
                    displayError(responseState.exception)
                    populateRecyclerView(responseState.data)
                }
            }
        }
    }

    private fun setupRecyclerView() {

        binding.rvMatchProfiles.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }
    }

    private fun setupChangeProfileStatusListener() {
        rvAdapter.assignChangeProfileStatusListener { matchProfile, status ->
            viewModel.changeMatchProfileStatus(matchProfile = matchProfile, status = status)
        }
    }

    private fun displayLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.GONE
        }
    }


    private fun populateRecyclerView(matchProfiles: List<MatchProfile>) {
        //Log.d("profiles length", matchProfiles.size.toString())
        if (matchProfiles.isNotEmpty()) rvAdapter.submitList(matchProfiles)
    }

    private fun displayError(exception: Exception) {
        exception.message?.let { Log.d("exception:", it) }

        when (exception) {

            is IOException -> {
                Toast.makeText(
                    this,
                    "No Internet Connection!",
                    Toast.LENGTH_LONG
                ).show()
            }
            is HttpException -> Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()

            else -> Toast.makeText(this, "Something went wrong...", Toast.LENGTH_LONG).show()
        }
    }

}