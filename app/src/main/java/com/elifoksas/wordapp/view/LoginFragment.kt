package com.elifoksas.wordapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.elifoksas.wordapp.R
import com.elifoksas.wordapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener{
            Login()
        }

        binding.signUpTxt.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            Navigation.findNavController(it).navigate(action)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    private fun Login(){
        val email = binding.loginEmailText.text.toString()
        val password = binding.loginPasswordText.text.toString()

        if(email.isEmpty()){
            binding.loginEmailText.setError("Please enter your email")
            return
        }
        if(password.isEmpty()){
            binding.loginPasswordText.setError("Please enter your password")
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                Log.d("login","login oldu")

            }
            .addOnFailureListener { exception ->
                Toast.makeText(context,("Giriş Başarısız"), Toast.LENGTH_LONG).show()
                Log.d("login",exception.message.toString())
            }
            .addOnSuccessListener {

                Log.d("login" , it.user?.email.toString())
                //val action = LoginFragmentDirections.actionLoginFragmentToWordsFragment()
                //view?.let { Navigation.findNavController(it).navigate(action) }
            }
            .addOnCanceledListener {
                Toast.makeText(context,("Giriş Başarısız."), Toast.LENGTH_LONG).show()
                Log.d("login" , "cancel")
            }





    }

}