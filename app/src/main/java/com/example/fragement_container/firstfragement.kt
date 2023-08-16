package com.example.fragement_container

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragement_container.Adapters.MybalanceAdapter
import com.example.fragement_container.Adapters.myfirst_home_frag_Adapter
import com.example.fragement_container.Models.balancehistory
import com.example.fragement_container.Models.first_farg_model
import com.example.fragement_container.databinding.FragmentFirstfragementBinding
import com.example.fragement_container.databinding.FragmentSecandfragementBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class firstfragement :BottomSheetDialogFragment(),myfirst_home_frag_Adapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentFirstfragementBinding

    private lateinit var pricearrlist:ArrayList<first_farg_model>

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
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_firstfragement, container, false)
        binding=FragmentFirstfragementBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        inflaterecycle view
        val includelayput=binding.bottomSheet.firstrecycleview
        val reccyclevieone=binding.firstfragementid
        val userhistorylist= ArrayList<balancehistory>()
        for (i in 1..20)
        {
            userhistorylist.add(balancehistory(R.drawable.carrybag, "shubham", "12-6-2013", "300"))
        }


        val pricearrlist = ArrayList<first_farg_model>() 
        for (i in 1..20)
        {
            pricearrlist.add(first_farg_model("Balance","$2435","$234"))
        }
        val userName = arguments?.getString(ARG_USER_NAME) ?: ""
        binding.showname.text="$userName"

        Log.d("Tag", "onViewCreated: $userName")

        
// inflayout buttonsheet

        val recyclerViewLayoutManageroflist =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        includelayput.layoutManager = recyclerViewLayoutManageroflist
        val recycleAdapter1 = MybalanceAdapter(userhistorylist)
        includelayput.adapter = recycleAdapter1
        binding.bottomSheet.firstrecycleview.setHasFixedSize(true)

//  actual file
        val recyclerViewLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        reccyclevieone.layoutManager = recyclerViewLayoutManager
        val recycleAdapter = myfirst_home_frag_Adapter(pricearrlist,this)
        reccyclevieone.adapter = recycleAdapter

        binding.firstfragementid.setHasFixedSize(true)



        val topbtn=binding.topbtn
        val userprofile=binding.userprofile

        topbtn.setOnClickListener {
            cornershowbtnsheet()
        }
        userprofile.setOnClickListener {
            openloginbtnsheet()
        }

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val firstbtnsheet=super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        firstbtnsheet.behavior.peekHeight=resources.getDimensionPixelSize(R.dimen.peekheignt_of_fragement)
        return firstbtnsheet;
    }

    override fun onItemClick(position: Int) {
//        Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
        val mythirdfragement = secandfragement() // Replace with your BottomSheetDialogFragment class
        mythirdfragement.show(requireFragmentManager(), mythirdfragement.tag)
    }



    fun cornershowbtnsheet()
    {
        val mythirdfragement=thirdfragement()
        mythirdfragement.show(childFragmentManager,mythirdfragement.tag)
    }

    fun openloginbtnsheet()
    {
        val loginobj= Loginfragement<Any>()
        loginobj.show(childFragmentManager,loginobj.tag);
    }






    companion object {
        private const val ARG_USER_NAME = "user_name"

        fun newInstance(userName: String): firstfragement {
            val args = Bundle()
            args.putString(ARG_USER_NAME, userName)
            val fragment = firstfragement()
            fragment.arguments = args
            return fragment
        }
    }
}