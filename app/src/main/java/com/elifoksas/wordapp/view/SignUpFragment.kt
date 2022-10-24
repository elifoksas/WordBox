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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


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


        if(email.isNotEmpty() && password.isNotEmpty()){

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {task->
                    if (!task.isSuccessful) {
                        Log.d("sign","sign oldu")
                        val user = auth.currentUser
                        return@addOnCompleteListener
                    } else{
                        Log.d("sign","sign olamadı")
                    }
                }

        }

    }
}