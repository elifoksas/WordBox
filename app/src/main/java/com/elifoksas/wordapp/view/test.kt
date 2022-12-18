package com.elifoksas.wordapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elifoksas.wordapp.R
import com.elifoksas.wordapp.databinding.FragmentLoginBinding
import com.elifoksas.wordapp.databinding.FragmentTestBinding
import com.elifoksas.wordapp.model.Words
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class test : Fragment() {

    private lateinit var binding: FragmentTestBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {

            val test = Words("Cherry","https://cdn-icons-png.flaticon.com/512/3137/3137038.png")

            FirebaseDatabase.getInstance().getReference("fruits/3").setValue(test).addOnSuccessListener { Log.d("frbs","true") }.addOnFailureListener {Log.d("frbs",it.message.toString()) }


        }
    }

}