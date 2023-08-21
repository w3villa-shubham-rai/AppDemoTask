package com.example.fragement_container
import android.app.Activity
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ActionMode.Callback2
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fragement_container.databinding.FragmentLoginRegfragementBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class Loginfragement<GoogleSignInAccount> : BottomSheetDialogFragment() {


    private lateinit var binding: FragmentLoginRegfragementBinding
     lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
     private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var callbackManager: CallbackManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginRegfragementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth=FirebaseAuth.getInstance()

//        sign in with facebook
         FacebookSdk.sdkInitialize(getApplicationContext())
        callbackManager = CallbackManager.Factory.create()

        binding.facebookbtn.setOnClickListener {

            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email", "public_profile"))
        }

        val callback = object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
            }
        }

        // Register the callback
        LoginManager.getInstance().registerCallback(callbackManager, callback)

//end code of face book







              binding.Register.setOnClickListener {
                val registerFragment = Registerfragment()
                val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragmentContainerView, registerFragment)
                fragmentTransaction.addToBackStack(null) // Optional, allows going back to previous fragment
                fragmentTransaction.commit()

//                signupUser()
            }

        binding.signinbtn.setOnClickListener {
            login()
        }





//      here clicklistner on google image
        firebaseAuth=FirebaseAuth.getInstance()
        binding.logingoogle.setOnClickListener {

               signInWithGoogle()
            Log.d(TAG, "onViewCreated: Hello shubham")
        }


        googleSignInOptions=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

//        facebook onclick btn
//        binding.facebookbtn



    }

//    oncreated finish


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val firstbtnsheet=super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        firstbtnsheet.behavior.peekHeight=resources.getDimensionPixelSize(R.dimen.peekheignt_of_fragement)
        return firstbtnsheet;

        Log.d(TAG, "onViewCreated: Hello shubham")

    }

//    open the google option pop
    private fun signInWithGoogle()
    {
       val googleSignInClient=GoogleSignIn.getClient(requireContext(),googleSignInOptions)
        val signInIntent=googleSignInClient.signInIntent
        Log.d(TAG, "signInIntent value: $signInIntent")
        Toast.makeText(requireContext(), "RC_SIGN_IN value: $RC_SIGN_IN", Toast.LENGTH_SHORT).show()
        startActivityForResult(signInIntent, RC_SIGN_IN)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode,resultCode, data)


        // Displaying the values in toast messages
//        Toast.makeText(requireContext(), "requestCode value: $requestCode", Toast.LENGTH_SHORT).show()
//        Toast.makeText(requireContext(), "RC_SIGN_IN value: $RC_SIGN_IN", Toast.LENGTH_SHORT).show()
        callbackManager.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "requestCodetwo value: $requestCode")
        Log.d(TAG, "RC_SIGN_INtwo  value: $RC_SIGN_IN")
        Log.d(TAG, "Datatwo  value: $data")

        if (requestCode == RC_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            Log.d(TAG, "Datatwo  value: $data")
            try {
//                val account = task.getResult(ApiException::class.java)
                val account = task.getResult(com.google.android.gms.common.api.ApiException::class.java)
                Log.d(TAG, "Datatwo  value: $data")
                Log.d(TAG, "Account details: $account")
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: com.google.android.gms.auth.api.signin.GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = firebaseAuth.currentUser

                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }




//    facebooklogic code again
    fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")
        Log.d(TAG, "handleFacebookAccessToken: shubhamrai")

        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->

                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success shubham")
                    val user = firebaseAuth.currentUser
                    // TODO: Navigate to HomeActivity
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed. ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

//    end



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
        private const val TAG = "Loginfragement"
        private const val RC_SIGN_IN = 9001
    }
}
