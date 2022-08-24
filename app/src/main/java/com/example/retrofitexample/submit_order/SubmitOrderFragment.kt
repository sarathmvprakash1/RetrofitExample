package com.example.retrofitexample.network

import android.annotation.SuppressLint
import android.app.Activity
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
import com.example.retrofitexample.databinding.FragmentSubmitOrderBinding
import com.example.retrofitexample.login.LogInViewModel
import com.example.retrofitexample.submit_order.SubmitOrderViewModel
import com.example.retrofitexample.submit_order.SubmitOrderViewModelFactory

@SuppressLint("UseRequireInsteadOfGet")
class SubmitOrderFragment : Fragment() {
    private var _binding: FragmentSubmitOrderBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    val sharedPrefFile = "kotlinsharedpreference"
    lateinit var sharedPreferences: SharedPreferences
    //val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    //val editor = sharedPreferences.edit()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        ///(application as? ApplicationClass)?.()?.inject(this)
        sharedPreferences = getActivity()!!.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)!!
        val editor = sharedPreferences.edit()

        val viewModel = ViewModelProvider(this, SubmitOrderViewModelFactory(getActivity()!!)).get(SubmitOrderViewModel::class.java)
           // .get(LoginViewModel::class.java)

        //val viewModel = ViewModelProvider(this).get(SubmitOrderViewModelFactory(activity!!)::class.java)

        _binding = FragmentSubmitOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val submitOrderButton: Button = binding.submitOrderButton
        val bookId = _binding!!.bookId
        val customerName = _binding!!.customerName
        submitOrderButton.setOnClickListener {
            //Log.e("LogInFragment", "setOnClickListener")
            //sharedPreferences.getString("ACCESS_TOKEN_KEY", "")
            Toast.makeText(context, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
            Log.e("SubmitOrderFragment", "ID: "+_binding!!.bookId.text.toString().toIntOrNull()+
                    " :customerName: "+_binding!!.customerName.text.toString())

            viewModel.submitBookOrder(
                bookId.text.toString().toIntOrNull()!!,
                customerName.text.toString())
        }

        /*viewModel.accessToken.observe(viewLifecycleOwner, {
            editor.putString("ACCESS_TOKEN_KEY", it.accessToken)
        })*/

        viewModel.submitBookOrderResult.observe(viewLifecycleOwner, {
            Toast.makeText(activity, "Submitted Order SuccessFully", Toast.LENGTH_SHORT).show()
            bookId.text.clear()
            customerName.text.clear()
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}