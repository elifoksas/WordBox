package com.elifoksas.wordapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.GridView
import androidx.navigation.Navigation
import com.elifoksas.wordapp.Adapter.CategoryAdapter
import com.elifoksas.wordapp.R
import com.elifoksas.wordapp.databinding.FragmentCategoriesBinding
import com.elifoksas.wordapp.model.Categories

class CategoriesFragment : Fragment() {

    var categoryList = ArrayList<Categories>()
    private lateinit var binding : FragmentCategoriesBinding



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
        binding = FragmentCategoriesBinding.inflate(inflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridView = binding.categoryGridVW

        categoryList.add(Categories(1,"alphabet","https://cdn-icons-png.flaticon.com/512/709/709418.png"))
        categoryList.add(Categories(2,"numbers","https://cdn-icons-png.flaticon.com/512/709/709388.png"))
        categoryList.add(Categories(3,"colors","https://cdn-icons-png.flaticon.com/512/2071/2071524.png"))
        categoryList.add(Categories(4,"shapes","https://cdn-icons-png.flaticon.com/512/2059/2059337.png"))
        categoryList.add(Categories(5,"fruits","https://cdn-icons-png.flaticon.com/512/3194/3194591.png"))
        categoryList.add(Categories(6,"vegetables","https://cdn-icons-png.flaticon.com/512/1147/1147934.png"))
        categoryList.add(Categories(7,"animals","https://cdn-icons-png.flaticon.com/512/5312/5312801.png"))
        categoryList.add(Categories(8,"body parts","https://cdn-icons-png.flaticon.com/512/4178/4178727.png"))
        categoryList.add(Categories(9,"days of the week","https://cdn-icons-png.flaticon.com/512/4345/4345649.png"))
        categoryList.add(Categories(10,"school","https://cdn-icons-png.flaticon.com/512/1373/1373058.png"))
        categoryList.add(Categories(11,"jobs","https://cdn-icons-png.flaticon.com/512/4532/4532490.png"))
        categoryList.add(Categories(12,"electronics","https://cdn-icons-png.flaticon.com/512/1261/1261143.png"))



        val adapter = this.context?.let { CategoryAdapter(it,categoryList) }
        gridView.adapter = adapter


        gridView.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                Log.d("category",p2.toString())
                val action = CategoriesFragmentDirections.actionCategoriesFragmentToWordsFragment(p2+1)
                Navigation.findNavController(view).navigate(action)


            }

        })








    }
}