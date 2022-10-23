package com.elifoksas.wordapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elifoksas.wordapp.R
import com.elifoksas.wordapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {


    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpButton.setOnClickListener{
            signUp()
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
            binding.signEmailText.setError("Please enter your email.")
            return
        }
        if (email.isBlank()){
            binding.signEmailText.setError("Email cannot be empty.")
        }
        if(password.isEmpty()){
            binding.signPasswordText.setError("Password cannot be empty.")
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                Log.d("sign","sign oldu")
                if (!it.isSuccessful) {
                    return@addOnCompleteListener
                }
            }.addOnFailureListener {
                it.printStackTrace()
            }
    }
}