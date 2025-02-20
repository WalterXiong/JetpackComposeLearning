package com.androidlearning.composetest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.androidlearning.composetest.ui.theme.ComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // setContent 提供一个 Composable 作用域
        setContent {

            // Composable 函数，Android Studio 自动为我们创建的，主要用于对项目的主题进行设置和定制
            ComposeTestTheme {

                // Composable 函数
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // Composable 函数
                    // Greeting(
                    // name = "Android",
                    // modifier = Modifier.padding(innerPadding)
                    // )

                    // Composable 函数
                    SimpleWight(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SimpleWight(modifier: Modifier = Modifier) {

    var userInput by remember { mutableStateOf("") }
    Column {
        TextFieldWidget(
            userInput,
            { newValue -> userInput = newValue },
            modifier
        )
    }


    // 显示一列，类似于 LinerLayout
    // modifier参数是Compose当中非常灵魂的一个组件,fillMaxSize()函数之后可以让Column的大小充满父布局
    // horizontalAlignment参数可以指定Column当中的子控件在水平方向上的对齐方式，CenterHorizontally表示居中对齐，另外你还可以选择Start和End
    /*Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        // 其中组件的水平对齐方式
        horizontalAlignment = Alignment.CenterHorizontally,
        // 其中组件的垂直对齐方式
        // SpaceEvenly的意思是，让Column中的每个子控件平分Column在垂直方向上的空间
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // =========================================================================================
        // text
        // =========================================================================================
        Text(
            text = "我是文本",
            color = Color.Blue,
            fontSize = 26.sp,
            // 各个组件自己对其方式
            modifier = Modifier.align(Alignment.End)
        )


        // =========================================================================================
        // button
        // =========================================================================================
        val context = LocalContext.current
        Button(onClick = {
            Toast.makeText(context, "这是一个 Toast 通知", Toast.LENGTH_SHORT).show()
        }) {
            Text(
                text = "这是一个按钮",
                color = Color.Magenta,
                fontSize = 26.sp
            )
        }

        // value：参数用于指定当前输入框中显示的文字内容
        // onValueChange：参数用于监听输入框中的文字内容变化
        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(text = "这是一个占位符，类比 Hint")
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White
            )
        )

        // =========================================================================================
        // ImageView
        // =========================================================================================
        // painter：参数用于指定要展示的drawable资源
        // contentDescription：参数用于指定对于这个资源的文字描述
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "一张绿色的照片"
        )

        val bitmap: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.fufu)
        Image(
            bitmap = bitmap,
            contentDescription = "一张 fufu 图片"
        )

        // 显示网络图片
        AsyncImage(
            model = "https://mmbiz.qpic.cn/mmbiz_png/v1LbPPWiaSt4ibXMCwHKJic7CHISyKMMnzl0CVmw5zFj8CWxrtHWz4rjEeic8nm6MaRdy0Oe3Z3egge1LbAFq2tMKw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",
            contentDescription = "第三行代码"
        )

        // =========================================================================================
        // 进度条
        // =========================================================================================
        // 圆形
        CircularProgressIndicator(
            color = Color.Green,
            strokeWidth = 6.dp
        )
        // 条形
        LinearProgressIndicator(
            color = Color.Magenta,
            trackColor = Color.Gray
        )
    }*/


    /**
     * horizontalScroll函数，
     * 这个函数有一个ScrollState参数是必填参数，
     * 它是用于保证在手机横竖屏旋转的情况下滚动位置不会丢失的，
     * 通常可以调用rememberScrollState函数获得
     */
    /*Row(
        modifier = modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "这是一个文本",
            color = Color.Green,
            fontSize = 26.sp
        )

        val context = LocalContext.current
        Button(onClick = {
            Toast.makeText(context, "这是一个 Toast 通知", Toast.LENGTH_SHORT).show()
        }) {
            Text(
                text = "这是一个按钮",
                color = Color.Magenta,
                fontSize = 26.sp
            )
        }

        // value：参数用于指定当前输入框中显示的文字内容
        // onValueChange：参数用于监听输入框中的文字内容变化
        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(text = "这是一个占位符，类比 Hint")
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White
            )
        )
    }*/


    /*Box(
        modifier = modifier.fillMaxSize()
    ) {

        Text(
            text = "这是一个文本",
            color = Color.Green,
            fontSize = 26.sp,
            modifier = modifier.align(Alignment.TopStart)
        )

        val context = LocalContext.current
        Button(
            modifier = modifier.align(Alignment.TopEnd),
            onClick = {
                Toast.makeText(context, "这是一个 Toast 通知", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(
                text = "这是一个按钮",
                color = Color.Magenta,
                fontSize = 26.sp
            )
        }

        // value：参数用于指定当前输入框中显示的文字内容
        // onValueChange：参数用于监听输入框中的文字内容变化
        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(text = "这是一个占位符，类比 Hint")
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White
            ),
            modifier = modifier.align(Alignment.CenterStart)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "一张绿色的照片",
            modifier = modifier.align(Alignment.CenterEnd)
        )

        val bitmap: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.fufu)
        Image(
            bitmap = bitmap,
            contentDescription = "一张 fufu 图片",
            modifier = modifier.align(Alignment.BottomStart)
        )

        CircularProgressIndicator(
            color = Color.Green,
            strokeWidth = 6.dp,
            modifier = modifier.align(Alignment.BottomEnd)
        )
    }*/
}


/**
 * 使用 State 进项函数重组
 */
@Composable
fun TextFieldWidget(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = "这是一个占位符，类比 Hint")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Gray
        )
    )
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


/**
 * 使用了 @Composable 进行声明，那么它就是一个 Composable 函数
 *
 * 注意：
 * Composable 函数只能在 Composable 作用域中才能调用
 * Composable 函数首字母需要大写
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTestTheme {
        // Greeting("Android")
        SimpleWight()
    }
}