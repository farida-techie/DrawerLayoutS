package com.malkinfo.drawerlayouts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malkinfo.drawerlayouts.ui.theme.DrawerLayoutSTheme
import com.malkinfo.drawerlayouts.uitel.FavoriteScreen
import com.malkinfo.drawerlayouts.uitel.HomeScreen
import com.malkinfo.drawerlayouts.uitel.NotificationScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawerLayoutSTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                  /**ok now call function*/
                    DrawerAppComponent()
                }
            }

        }
    }
}
/** DrawerAppComponent*/
@Composable
fun DrawerAppComponent(){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val currentScreen = remember { mutableStateOf(DrawerAppScreen.Home) }
    val scope = rememberCoroutineScope()
    ModalDrawer(drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            DrawerContentComponent(
                currentScreen = currentScreen,
                closeDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )

        },
        content = {
            BodyContentComponent(
                currentScreen =currentScreen.value ,
                openDrawer = {
                    scope.launch {
                        drawerState.open()
                    }
                })
        }
    )
}
/**set CloseDrawer Content*/
@Composable
fun DrawerContentComponent(
    currentScreen:MutableState<DrawerAppScreen>,
    closeDrawer:()->Unit
)
{
    /**set design*/
    Column (modifier = Modifier.fillMaxSize()){
        for (index in DrawerAppScreen.values().indices){
            /**set Screen ,Icon and Color*/
            val screen = getScreenBasedOnIndex(index)
            val iconss = getIcon(index)
            val colors = getColor(index)
            Column(
                modifier = Modifier.clickable {
                    currentScreen.value = screen
                    closeDrawer()
                },
                content = {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = if (currentScreen.value == screen){
                            MaterialTheme.colors.secondary
                        }else{
                            MaterialTheme.colors.surface
                        }

                    ) {
                        /**set Icon and Text*/
                        Row{
                            Image(modifier = Modifier.padding(7.dp),
                                imageVector = iconss ,
                                contentDescription = "Menu",
                                colorFilter = ColorFilter.lighting(
                                    colors,add= colors))
                            Text(text = "${screen.name} Screen",
                                modifier = Modifier.padding(7.dp),
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    fontStyle = FontStyle.Normal,
                                    color = colors
                                )
                            )
                        }

                    }
                }
            )
        }
    }

}
/**set Screen*/
fun getScreenBasedOnIndex(index:Int)= when(index){
    0-> DrawerAppScreen.Home
    1-> DrawerAppScreen.Favorite
    2-> DrawerAppScreen.Notification
    else->DrawerAppScreen.Home
}
/**set Icon*/
fun getIcon(index:Int)= when(index){
    0-> Icons.Default.Home
    1-> Icons.Default.Favorite
    2-> Icons.Default.Notifications
    else->Icons.Default.Home
}
/**set Color*/
fun getColor(index:Int)= when(index){
    0-> Color.Black
    1-> Color.Red
    2->Color.Blue
    else->Color.Black
}

/**set BodyContentComponent*/
@Composable
fun BodyContentComponent(
    currentScreen:DrawerAppScreen,
    openDrawer:()->Unit

)
{
    when(currentScreen){
        DrawerAppScreen.Home -> HomeScreen(openDrawer =  openDrawer )
        DrawerAppScreen.Favorite -> FavoriteScreen(openDrawer =  openDrawer )
        DrawerAppScreen.Notification -> NotificationScreen(openDrawer =  openDrawer)
    }

}

enum class DrawerAppScreen{Home,Favorite,Notification}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DrawerLayoutSTheme {
        Greeting("Android")
    }
}