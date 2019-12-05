package com.assignment.caller

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.assignment.caller.databinding.ActivityMainBinding
import com.assignment.caller.models.ScreenModel
import com.assignment.caller.viewmodels.MainViewModel
import com.assignment.domain.usecases.FetchTenthCharacterUseCase
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val screenModel: ScreenModel = ScreenModel()

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.model = screenModel

        val viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel::class.java)


        viewModel.getTenthCharacter().observe(this, Observer {
            it?.let {
                screenModel.tenthChar = it
            }

        })

    }
}
