package com.ronasit.fiesta.ui.login

import com.ronasit.fiesta.R
import javax.inject.Inject
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ronasit.fiesta.di.qualifiers.ViewModelInjection
import com.ronasit.fiesta.di.ViewModelInjectionField
import com.ronasit.fiesta.ui.base.BaseActivity
import com.ronasit.fiesta.ui.login.confirmation.ConfirmationFragment
import com.ronasit.fiesta.ui.login.signin.SignInFragment
import dagger.android.support.HasSupportFragmentInjector

class LoginActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    @ViewModelInjection
    lateinit var viewModel: ViewModelInjectionField<LoginVM>

    override fun layoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(viewModel.get()) {
            if (isProfileCompleted()) {
                // TODO: open new activity
            } else {
                onMoveToSignInFragment()
            }
        }
    }

    private fun onMoveToSignInFragment() {
        val signInFragment = SignInFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, signInFragment)
            .commitNow()

        signInFragment.viewModel.get().isPhoneValid().observe(this@LoginActivity, Observer {
            onMoveToConfimationFragment()
        })
    }

    private fun onMoveToConfimationFragment() {
        val confirmationFragment = ConfirmationFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.mainContainer, ConfirmationFragment.newInstance())
        transaction.commitNow()
        transaction.runOnCommit {
            confirmationFragment.viewModel.get().isCodeValid()
                .observe(this@LoginActivity, Observer {
                    Toast.makeText(this, "kek", Toast.LENGTH_SHORT).show()
                    onMoveToProfileFragment()
                })
        }
    }

    private fun onMoveToProfileFragment() {

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}