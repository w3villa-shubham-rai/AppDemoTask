package com.example.fragement_container

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragement_container.Adapters.MybalanceAdapter
import com.example.fragement_container.Models.balancehistory
import com.example.fragement_container.databinding.FragmentSecandfragementBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"




class secandfragement : BottomSheetDialogFragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentSecandfragementBinding


    private lateinit var userhistorylist:ArrayList<balancehistory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_secandfragement, container, false)
        binding=FragmentSecandfragementBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycleview = binding.firstrecycleview
        val userhistorylist = ArrayList<balancehistory>()
        for (i in 1..20)
        {
            userhistorylist.add(balancehistory(R.drawable.carrybag, "shubham", "12-6-2013", "300"))
        }



        val recyclerViewLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        recycleview.layoutManager = recyclerViewLayoutManager
        val recycleAdapter = MybalanceAdapter(userhistorylist)
        recycleview.adapter = recycleAdapter
        binding.firstrecycleview.setHasFixedSize(true)


    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       val bottomSheetDialog=super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.behavior.peekHeight=resources.getDimensionPixelSize(R.dimen.bottom_sheet_peek_height)
        return bottomSheetDialog
    }



    companion object {



    }




}