package com.malkinfo.drawerlayouts.uitel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FavoriteScreen(
    openDrawer:()->Unit
)
{
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        /**set ToolBar*/
        /**set ToolBar*/
        TopAppBar (
            title = {
                Text(text = "Favorite Screen")
            },
            navigationIcon = {
                IconButton(onClick =  openDrawer ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription ="Menu" )
                }
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription ="Favorite" )
            }
        )
        /** set Image And Text*/
        /** set Image And Text*/
        Surface (
            color = Color(0xFFffd7d7.toInt()),
            modifier = Modifier.weight(1f)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Card (
                            modifier = Modifier.padding(1.dp),
                            elevation = 16.dp,
                            shape = CircleShape
                        ){
                            Image(modifier = Modifier.padding(25.dp),
                                imageVector = Icons.Default.Favorite ,
                                contentDescription = "Favorite",
                                colorFilter = ColorFilter
                                    .lighting(Color.Red,add = Color.Red)
                            )
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Favorite Screen",
                            style = TextStyle(
                                fontStyle = FontStyle.Normal,
                                fontSize = 19.sp
                            ),
                            color = Color.Red
                        )

                    }
                }
            )

        }

    }

}