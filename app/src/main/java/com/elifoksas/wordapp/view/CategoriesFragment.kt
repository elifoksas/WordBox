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

        categoryList.add(Categories(1,"alphabet","https://cdn.shopify.com/s/files/1/1278/1015/products/curious-columbus-abc-sensory-learning-felt-alphabet-flashcard-set-lowercase-curious-columbus-the-creative-toy-shop-3.jpg?v=1603821989"))
        categoryList.add(Categories(2,"numbers","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRcGOTDc958t_SM2dOoZ3eEWG9u73eyOtSfUw&usqp=CAU"))
        categoryList.add(Categories(3,"colors","https://i0.wp.com/www.adams.es/blogs/alumno/files/2019/04/portada-colores-ok.jpg?fit=1000%2C667&ssl=1"))
        categoryList.add(Categories(4,"shapes","https://img.freepik.com/premium-vector/cartoon-shapes-kid-isolated-white_6997-2067.jpg?w=2000"))
        categoryList.add(Categories(5,"fruits","https://img.freepik.com/premium-vector/set-fruits-cartoon-style-illustration_313437-80.jpg?w=2000"))
        categoryList.add(Categories(6,"vegetables","https://img.freepik.com/premium-vector/pile-fresh-cartoon-vegetables_267448-186.jpg?w=2000"))
        categoryList.add(Categories(7,"animals","https://cdn.pixabay.com/photo/2021/07/06/17/05/animals-6392131_960_720.png"))
        categoryList.add(Categories(8,"food","https://media.istockphoto.com/id/477338550/vector/fast-food-cartoon-set.jpg?s=612x612&w=0&k=20&c=43d4dJfLkT0b70VLW61a-H1svzcKZqHRnD9z9pyqkCY="))
        categoryList.add(Categories(9,"animals","https://i.pinimg.com/736x/49/94/84/499484164d0c0d44242d205f3b08bb6b.jpg"))
        categoryList.add(Categories(10,"days of the week","https://static.vecteezy.com/system/resources/thumbnails/000/302/050/small/3i78_7g6x_140704.jpg"))

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