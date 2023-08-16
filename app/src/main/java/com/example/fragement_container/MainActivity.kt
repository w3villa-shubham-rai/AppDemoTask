package com.example.fragement_container

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.example.fragement_container.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.button.setOnClickListener {

//            val myfirstfragemet=firstfragement()
//            val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragmentContainerView,myfirstfragemet)
//            transaction.addToBackStack(null)
//            transaction.commit()
//            val myfirstfragement=firstfragement()
//            myfirstfragement.show(supportFragmentManager, myfirstfragement.tag)
//
//        }

//        binding.button2.setOnClickListener {

//            val mysecandfragemet=secandfragement()
//            val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragmentContainerView,mysecandfragemet)
//            transaction.addToBackStack(null)
//            transaction.commit()
//            val mysecandfragemet=secandfragement()
//            mysecandfragemet.show(supportFragmentManager,mysecandfragemet.tag)
//
//
//        }


//        binding.button3.setOnClickListener {

//            val mythirdfragement=thirdfragement()
//            val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragmentContainerView,mythirdfragement)
//            transaction.addToBackStack(null)
//            transaction.commit()

//            val mythirdfragement=thirdfragement()
//            mythirdfragement.show(supportFragmentManager,mythirdfragement.tag)
//
//        }







    }
}