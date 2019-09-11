package com.ronasit.fiesta.ui.login.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject
import com.ronasit.fiesta.R
import com.ronasit.fiesta.databinding.FragmentSignInBinding
import com.ronasit.fiesta.di.qualifiers.ViewModelInjection
import com.ronasit.fiesta.di.ViewModelInjectionField
import com.ronasit.fiesta.ui.base.BaseFragment

class SignInFragment : BaseFragment() {

    override fun layoutRes() = R.layout.fragment_sign_in

    companion object {
        fun newInstance(): SignInFragment {
            return SignInFragment()
        }
    }

    @Inject
    @ViewModelInjection
    lateinit var viewModel: ViewModelInjectionField<SignInVM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSignInBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar!!.hide()
        binding.viewModel = viewModel.get()
        binding.lifecycleOwner = this

        return binding.root
    }

}