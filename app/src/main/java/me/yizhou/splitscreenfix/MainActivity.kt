package me.yizhou.splitscreenfix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.highcapable.yukihookapi.YukiHookAPI

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold (
                    topBar = {
                        TopAppBar(title = {
                            val title =  if (YukiHookAPI.Status.isModuleActive) "模块已激活" else "模块未激活"
                            Text(text = "$title version: 1.0.1")
                        })

                    }
                ){
                    Column(modifier = Modifier.padding(it)) {
                        Card (
                            modifier = Modifier.padding(10.dp).fillMaxWidth().height(150.dp).clickable {  }
                        ) {
                            Column {
                                Text(text = "Split Screen Fix", modifier = Modifier.padding(20.dp), fontSize = 26.sp, fontWeight = FontWeight.Bold)
                                Text(text = "鸣谢Yuki Hook API", modifier = Modifier.padding(20.dp), fontSize = 16.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }
    }
}