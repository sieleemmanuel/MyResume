package com.siele.myresume

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.siele.myresume.ui.theme.MyResumeTheme

@Composable
 fun WebViewContent(url: String, navController: NavHostController) {
    val context = LocalContext.current
     Scaffold(topBar ={WebTopBar(
         title = url,
         imageVector = Icons.Default.Close,
         iconClick = {
             navController.popBackStack()
             Toast.makeText(context, "Close Screen", Toast.LENGTH_SHORT).show()
         }
     )}) { pValues ->
         WebContent(url, pValues)
     }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebContent(url: String, pValues: PaddingValues) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            clearCache(true)
            clearHistory()
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}

@Composable
fun WebTopBar(title: String, imageVector: ImageVector, iconClick:()->Unit) {
   TopAppBar(
       backgroundColor = MaterialTheme.colors.background,
       elevation = 0.dp,
       title = { Text(text = title, overflow = TextOverflow.Ellipsis, maxLines = 1)},
       navigationIcon = {
           IconButton(onClick = iconClick ) {
               Icon(imageVector = imageVector, contentDescription = null)
           }

       }
   )
}

@Preview(
    showSystemUi = true
)
@Composable
fun WebPreview() {
/*MyResumeTheme {
    }*/
    Surface {
        WebViewContent(
            url = "https://sieleemmanuel.github.io",
            navController = rememberNavController()
        )
    }

}