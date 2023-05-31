package com.example.redessociales

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
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
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Arrays


class MainActivity : AppCompatActivity() {
    var callbackManager = CallbackManager.Factory.create(); // Crea un CallbackManager
    var shareDialog: ShareDialog? = null // Declara una variable para el diálogo de compartir

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val info = packageManager.getPackageInfo(
                "com.example.redessociales",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }


        val TAG = MainActivity::class.java.simpleName
        Log.d(TAG,"KeyHash:")

        // Se instancia la ventana de compartir contenido
        shareDialog = ShareDialog(this)

        // Boton de compartir link
        var btnlink = findViewById<View>(R.id.btnlink) as Button

        btnlink.setOnClickListener {
            val content: ShareLinkContent = ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.facebook.com/photo/?fbid=934234880051401&set=a.118731581601739"))
                .build()
            if (ShareDialog.canShow(ShareLinkContent::class.java)) {
                shareDialog!!.show(content) // Muestra el diálogo de compartir
            }
        }

        // Configuración para el login
        val EMAIL = "email"

        var loginButton = findViewById<View>(R.id.login_button) as LoginButton
        loginButton.setReadPermissions(Arrays.asList(EMAIL))

        // Callback registration para el botón de login
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // Código de la aplicación en caso de éxito en el login
            }

            override fun onCancel() {
                // Código de la aplicación en caso de cancelación del login
            }

            override fun onError(exception: FacebookException) {
                // Código de la aplicación en caso de error en el login
            }
        })

        callbackManager = create()

        // Callback registration para el LoginManager
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    // Código de la aplicación en caso de éxito en el login
                }

                override fun onCancel() {
                    // Código de la aplicación en caso de cancelación del login
                }

                override fun onError(exception: FacebookException) {
                    // Código de la aplicación en caso de error en el login
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}