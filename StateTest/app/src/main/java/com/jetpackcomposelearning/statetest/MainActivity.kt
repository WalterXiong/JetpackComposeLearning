package com.jetpackcomposelearning.statetest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetpackcomposelearning.statetest.ui.theme.StateTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateTestTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    // Counter(modifier = Modifier.padding(innerPadding))

                    /**
                     * State 状态提升
                     *
                     * 以下是你应该考虑的状态提升最少应该到达哪个层级的关键因素：
                     * 1. 如果有多个Composable函数需要读取同一个State对象，那么至少要将State提升到这些Composable函数共有的父级函数当中。
                     * 2. 如果有多个Composable函数需要对同一个State对象进行写入，那么至少要将State提升到所有执行写入的Composable函数里调用层级最高的那一层。
                     * 3. 如果某个事件的触发会导致两个或更多的State发生变更，那么这些State都应该提升到相同的层级。
                     */
                    CallCounterState(modifier = Modifier.padding(innerPadding))


                    // 使用 ViewModel
                    CallCounterByViewModel(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CallCounterByViewModel(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {

    val count by viewModel.count.observeAsState(0)
    val doubleCount by viewModel.doubleCount.observeAsState(0)
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatelessCounter(
            count,
            onIncrement = { viewModel.incrementCount() },
            modifier = modifier.fillMaxSize()
        )

        StatelessCounter(
            doubleCount,
            onIncrement = { viewModel.incrementDoubleCount() },
            modifier = modifier.fillMaxSize()
        )
    }
}

// ==============================================================================================
// State 状态提升
// ==============================================================================================
@Composable
fun CallCounterState(modifier: Modifier = Modifier) {

    var count by rememberSaveable { mutableIntStateOf(0) }

    var doubleCount by rememberSaveable { mutableIntStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatelessCounter(
            count,
            onIncrement = { count++ },
            modifier = modifier.fillMaxSize()
        )

        StatelessCounter(
            doubleCount,
            onIncrement = { doubleCount += 2 },
            modifier = modifier.fillMaxSize()
        )
    }
}


@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {

    Text(
        text = "$count",
        fontSize = 50.sp
    )

    Button(
        onClick = { onIncrement() }
    ) {
        Text(
            text = "点我 +1 ",
            fontSize = 26.sp
        )
    }
}


@Composable
fun Counter(modifier: Modifier = Modifier) {

    // remember函数的作用是让其包裹住的变量在重组的过程中得到保留，从而就不会出现变量被重新初始化的情况了
    // var count by remember { mutableIntStateOf(0) }

    // 增强版，旋转屏幕还能保留
    var count by rememberSaveable { mutableIntStateOf(0) }

    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "$count", fontSize = 50.sp)

        Button(onClick = {
            count++
            Toast.makeText(context, "+1", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "点我 +1 ", fontSize = 26.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StateTestTheme {
        Counter()
    }
}