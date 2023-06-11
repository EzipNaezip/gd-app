package com.dongminpark.projectgd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.compose.rememberNavController
import com.dongminpark.projectgd.Screens.OAuthData
import com.dongminpark.projectgd.Screens.handleSignInResult
import com.dongminpark.projectgd.ui.theme.ProjectgdTheme
import com.dongminpark.projectgdn.navigation.SetupNavGraph
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OAuthData.auth = FirebaseAuth.getInstance()
        OAuthData.GoogleSignResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }
        setContent {
            ProjectgdTheme {
                val navController = rememberNavController()
                OAuthData.nav = navController
                OAuthData.mGoogleSignInClient = GoogleSignIn.getClient(this, OAuthData.gso)
                OAuthData.account = GoogleSignIn.getLastSignedInAccount(this)

                SetupNavGraph(navController = navController)
            }
        }
    }
}