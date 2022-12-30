package com.elifoksas.wordapp.view

import android.opengl.Visibility
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elifoksas.wordapp.R
import com.elifoksas.wordapp.databinding.FragmentTestBinding
import com.elifoksas.wordapp.databinding.FragmentWordsBinding
import com.elifoksas.wordapp.model.Words
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class WordsFragment : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var binding: FragmentWordsBinding


    var categoryid = 0
    var categoryName = ""
    lateinit var textToSpeech : TextToSpeech







    companion object{
        var count = 0
        var wordList : ArrayList<Words> = arrayListOf()




    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            categoryid = WordsFragmentArgs.fromBundle(it).categoryid
            Log.d("oncreate",categoryid.toString())
            if (categoryid==1){
                categoryName = "alphabet"
            }
            if (categoryid==2){
                categoryName = "numbers"
            }
            if (categoryid==3){
                categoryName = "colors"
            }
            if (categoryid==4){
                categoryName = "shapes"
            }
            if (categoryid==5){
                categoryName = "fruits"
            }
            if (categoryid==6){
                categoryName = "vegetables"
            }
            if (categoryid==7){
                categoryName = "animals"
            }
            if (categoryid==8){
                categoryName = "body parts"
            }
            if (categoryid==9){
                categoryName = "days of the week"
            }
            if (categoryid==10){
                categoryName = "school"
            }
            if (categoryid==11){
                categoryName = "jobs"
            }
            if (categoryid==12){
                categoryName = "electronics"
            }

            Log.d("categoryname",categoryName.toString())

            wordList.clear()
            count=0
            getWords()


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWordsBinding.inflate(inflater)

        return binding.root
    }


    fun getWords(){
        FirebaseDatabase.getInstance().getReference(categoryName).addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {



                val words = snapshot.child("word").value
                val image = snapshot.child("imageUrl").value

                if (words!=null && image!=null ){
                    val wordObj = Words(words.toString(),image.toString())

                    wordList.add(wordObj)
                }
                else{

                }


                Picasso.get().load(wordList[count].imageUrl).into(binding.wordImage)
                binding.wordTxt.text = wordList[count].word

                if (count==0){
                    binding.leftBtn.visibility = View.GONE
                }



                binding.rightBtn.setOnClickListener {

                    count++

                    //binding.leftBtn.visibility = View.VISIBLE
                    Picasso.get().load(wordList[count].imageUrl).into(binding.wordImage)
                    binding.wordTxt.text = wordList[count].word

                }

                binding.leftBtn.setOnClickListener {

                    count--
                    if (count==0){
                        binding.leftBtn.visibility = View.GONE
                    }
                    else{
                        binding.leftBtn.visibility = View.VISIBLE

                    }
                    Picasso.get().load(wordList[count].imageUrl).into(binding.wordImage)
                    binding.wordTxt.text = wordList[count].word

                }


            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }

    private fun speakOut(){
        val text = binding.wordTxt.text.toString()
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textToSpeech = TextToSpeech(context,this)






        binding.voiceBtn.setOnClickListener {

            textToSpeech.language = Locale.ENGLISH

            speakOut()

        }



    }

    override fun onDestroy() {
        if (textToSpeech!=null){
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onInit(p0: Int) {

    }

}