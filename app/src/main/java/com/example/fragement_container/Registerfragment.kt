package com.example.fragement_container

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fragement_container.databinding.FragmentLoginRegfragementBinding
import com.example.fragement_container.databinding.FragmentRegisterfragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Registerfragment : BottomSheetDialogFragment() {


    lateinit var binding: FragmentRegisterfragmentBinding;
    private lateinit var firebaseAuth:FirebaseAuth;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentRegisterfragmentBinding.inflate(inflater,container,false)
        return binding.root;
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog=super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.behavior.peekHeight=resources.getDimensionPixelSize(R.dimen.bottom_sheet_peek_height)
        return bottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registeruser.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()
            signupUser()
        }

    }
    private fun signupUser()
    {
        val email=binding.email.text.toString()
        val password=binding.password.text.toString()

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(requireActivity()) {
                if(it.isSuccessful)
                {
                    Toast.makeText(requireContext(), "success user", Toast.LENGTH_SHORT).show()
                    val myLoginfragement=Loginfragement<Any>()
                    myLoginfragement.show(childFragmentManager,myLoginfragement.tag)

                }
                else{
                    Toast.makeText(requireContext(), "Error creatin user", Toast.LENGTH_SHORT).show()
                }
            }
    }


    companion object {

    }
}