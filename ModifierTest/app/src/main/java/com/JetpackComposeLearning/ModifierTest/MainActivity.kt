package com.JetpackComposeLearning.ModifierTest

import android.R.attr.orientation
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.JetpackComposeLearning.ModifierTest.ui.theme.ModifierTestTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ModifierTestTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // IconImage()
                    // AddExtensionInfo()
                    // PointerInputEvent()
                    // HighLevelCompose()

                    /**
                     * // 理解当前作用域
                     * // 串接顺序有影响
                     * // 增加Modifier参数
                     */
                    ParentLayout()

                }
            }
        }
    }
}


@Composable
fun ParentLayout(modifier: Modifier = Modifier) {

    Column {
        IconImage(Modifier.align(Alignment.CenterHorizontally))
    }
}


@Composable
fun IconImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = "Icon image",
        modifier = modifier
            // 让Image根据自身内容来决定控件的大小
            .wrapContentSize(
                // 就是可以对控件的对齐方式进行指定
                align = Alignment.CenterStart
            )
            .background(Color.Gray)
            // 添加边距
            .padding(18.dp)
            // 增加边框
            .border(5.dp, Color.Magenta, CircleShape)
            // 添加边距(调用顺序不同，与上面的效果也是不一样的)
            .padding(18.dp)
            // 裁剪为圆形
            .clip(CircleShape)
            // 旋转
            .rotate(120f)
    )
}

// ==================================================================================
// 为Compose控件增加额外的信息
// ==================================================================================
@Composable
fun AddExtensionInfo() {
    Button(onClick = {}) {
        Text(
            text = "这是一个按钮",
            color = Color.Magenta,
            fontSize = 26.sp
        )
    }
}

// ==================================================================================
// 处理用户的输入
// ==================================================================================
@Composable
fun PointerInputEvent() {
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Color.Blue)
            /*.pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        Log.d("PointerInputEvent", "event: ${event.type}")
                    }
                }
            }*/
            .pointerInput(Unit) {
                // 检测点击手势
                detectTapGestures {
                    Log.d("PointerInputEvent", "Tap")
                }
            }
            .pointerInput(Unit) {
                // 检测拖动手势
                detectDragGestures { change, dragAmount ->
                    Log.d("PointerInputEvent", "Drag")
                }
            }
    )
}

// ==================================================================================
// 使控件可点击、滚动、拖拽
// ==================================================================================
@Composable
fun HighLevelCompose() {

    // 支持点击
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Color.Blue)
            .clickable {
                Toast.makeText(context, "你点击了 Box", Toast.LENGTH_SHORT).show()
            }
    )

    // 支持滚动
    Column(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Color.Blue)
            .verticalScroll(rememberScrollState())

    ) {
        repeat(10) {
            Text(text = "Item $it", color = Color.White, fontSize = 26.sp)
        }
    }

    // 支持拖拽(仅允许横向或者竖向)
    /*var offsetX by remember { mutableFloatStateOf(0f) }
    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .requiredSize(200.dp)
            .background(Color.Blue)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    offsetX += delta
                }
            )
    )*/

    // 随意拖动
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .requiredSize(200.dp)
            .background(Color.Blue)
            .pointerInput(Unit) {

                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ModifierTestTheme {
        IconImage()
    }
}
