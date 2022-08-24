package com.example.retrofitexample.network

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitexample.databinding.FragmentLoginBinding
import com.example.retrofitexample.login.LogInViewModel

@SuppressLint("UseRequireInsteadOfGet")
class LogInFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    val sharedPrefFile = "kotlinsharedpreference"
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        ///(application as? ApplicationClass)?.()?.inject(this)
        sharedPreferences = getActivity()!!.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)!!
        val editor = sharedPreferences.edit()

        val viewModel = ViewModelProvider(this).get(LogInViewModel::class.java)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val logIn: Button = binding.loginButton
        logIn.setOnClickListener {
            //Log.e("LogInFragment", "setOnClickListener")
            //sharedPreferences.getString("ACCESS_TOKEN_KEY", "")
            Toast.makeText(context, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
            viewModel.getAuthenticationToken()
        }

        viewModel.accessToken.observe(viewLifecycleOwner, {
            editor.putString("ACCESS_TOKEN_KEY", it.accessToken)
            editor.commit()


            val token = sharedPreferences.getString("ACCESS_TOKEN_KEY","defaultName")
            Log.e("LogInFragment", "token "+token)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}