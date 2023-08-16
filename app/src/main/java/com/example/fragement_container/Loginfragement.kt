package com.example.fragement_container

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fragement_container.databinding.FragmentLoginRegfragementBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class Loginfragement<GoogleSignInAccount> : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLoginRegfragementBinding
    private lateinit var googleSignInClient: GoogleSignInClient
     lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginRegfragementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.Register.setOnClickListener {

                val registerFragment = Registerfragment()
                val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragmentContainerView, registerFragment)
                fragmentTransaction.addToBackStack(null) // Optional, allows going back to previous fragment
                fragmentTransaction.commit()

//                signupUser()
            }

          firebaseAuth=FirebaseAuth.getInstance()

        binding.signinbtn.setOnClickListener {
            login()
        }

        firebaseAuth=FirebaseAuth.getInstance()
        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webclient_id))
            .requestEmail()
            .build()

        googleSignInClient=GoogleSignIn.getClient(requireActivity(),gso)

        binding.logingoogle.setOnClickListener {
            signInGoogle()
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val firstbtnsheet=super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        firstbtnsheet.behavior.peekHeight=resources.getDimensionPixelSize(R.dimen.peekheignt_of_fragement)
        return firstbtnsheet;


    }

    private fun signInGoogle()
    {
        val signInIntent=googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }
    private val launcher=registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        result->
        if(result.resultCode==Activity.RESULT_OK)
        {
            val task=GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<com.google.android.gms.auth.api.signin.GoogleSignInAccount>) {
        if(task.isSuccessful)
        {
           val account: com.google.android.gms.auth.api.signin.GoogleSignInAccount? =task.result
            if (account !=null)
            {
                updateUI(account)
            }
            else
            {
                Toast.makeText(requireContext(), "error find in sigin", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI(account: com.google.android.gms.auth.api.signin.GoogleSignInAccount?) {
        val credential=GoogleAuthProvider.getCredential(account?.idToken,null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful)
            {
                Toast.makeText(requireContext(), "login success", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()
            }
        }
    }



    //    normal login
   private fun  login()
   {
       val emaillogin=binding.emaillogin.text.toString()
       val passwordlogin=binding.passwordlogin.text.toString()
       if(emaillogin.isBlank() || passwordlogin.isBlank())
       {
           Toast.makeText(requireContext(), "Passworad/email cannot be empty", Toast.LENGTH_SHORT).show()
       }
       firebaseAuth.signInWithEmailAndPassword(emaillogin, passwordlogin).addOnCompleteListener(requireActivity()) { task ->
           if (task.isSuccessful) {
               val user = firebaseAuth.currentUser

               if (user != null) {
                   val userName = user.email
                   Log.d("Tag", "onViewCreated: Hello shubham")
               Log.d("TAG", "login:$userName")

                   Toast.makeText(requireContext(), "Login success", Toast.LENGTH_SHORT).show()
                   val registerFragment = firstfragement.newInstance(userName ?: "")
                   val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                   fragmentTransaction.replace(R.id.fragmentContainerView, registerFragment)
                   fragmentTransaction.addToBackStack(null) // Optional, allows going back to previous fragment
                   fragmentTransaction.commit()
               }
           } else {
               Toast.makeText(requireContext(), "Authentication failed", Toast.LENGTH_SHORT).show()
           }
       }
   }






    companion object {
            private const val RC_SIGN_IN = 9001
    }
}
