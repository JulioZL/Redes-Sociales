package com.example.redessociales

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.CallbackManager.Factory.create
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import java.util.Arrays


class MainActivity : AppCompatActivity() {
    var callbackManager = CallbackManager.Factory.create();
    var shareDialog: ShareDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //se instancia la ventana de compartir contenido
        shareDialog = ShareDialog(this)



        //boton de compartir link
        var btnlink = findViewById<View>(R.id.btnlink) as Button

        btnlink.setOnClickListener {
            val content: ShareLinkContent = ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.facebook.com/photo/?fbid=934234880051401&set=a.118731581601739"))
                .build()
            if (ShareDialog.canShow(ShareLinkContent::class.java)){
                shareDialog!!.show(content)
            }
        }


        //COSAS PARA EL LOGIN
        val EMAIL = "email"

        var loginButton = findViewById<View>(R.id.login_button) as LoginButton
        loginButton.setReadPermissions(Arrays.asList(EMAIL))
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // App code
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })


        callbackManager = create()

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    // App code
                }

                override fun onCancel() {
                    // App code
                }

                override fun onError(exception: FacebookException) {
                    // App code
                }
            })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}