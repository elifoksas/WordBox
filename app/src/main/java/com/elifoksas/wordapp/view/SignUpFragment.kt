package com.elifoksas.wordapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.elifoksas.wordapp.R
import com.elifoksas.wordapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.navigation.Navigation


class SignUpFragment : Fragment() {


    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpButton.setOnClickListener{
            signUp()

        }
        binding.signInTxt.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater)
        return binding.root
    }

    private fun signUp(){

        val name = binding.nameText.text.toString()
        val email = binding.signEmailText.text.toString().trim()
        val password = binding.signPasswordText.text.toString().trim()

        if(email.isEmpty()){
            binding.signEmailText.setError("Please enter your email")
            return
        }
        if(password.isEmpty()){
            binding.signPasswordText.setError("Please enter your password")
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {task->
                Log.d("sign","sign oldu")

            }
            .addOnFailureListener {
                Log.d("sign",it.message.toString())
            }
            .addOnSuccessListener {
                Log.d("sign" , it.user?.email.toString())
                val action = SignUpFragmentDirections.actionSignUpFragmentToWordsFragment()
                view?.let { Navigation.findNavController(it).navigate(action) }
            }
            .addOnCanceledListener {
                Log.d("sign" , "cancel")
            }








    }
}