package dev.nic3point0.nevhosttest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun Drawer(onNavigation: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = onNavigation, modifier = Modifier.align(Alignment.Center)) {
            Text(text = stringResource(id = R.string.btn_goto_about))
        }
    }
}

@Composable
fun TopBar(data: TopBarData, onClick: () -> Unit = {}) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = data.title))
        },
        navigationIcon = {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    imageVector = data.iconVector,
                    contentDescription = stringResource(id = data.iconDescription)
                )
            }
        }
    )
}


@Composable
fun ScreenContent(message: String) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Surface(modifier = Modifier.align(Alignment.Center)) {
            Text(text = message, fontSize = 18.sp)
        }
    }

}

@Preview
@Composable
fun ScreenContentPreview() {
    ScreenContent("About me")
}


@Composable
@Preview
fun TopBarPreview() {
    TopBar(
        data = TopBarData(R.string.title_overview, Icons.Filled.Menu, R.string.icon_desc_menu)
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun DrawerPreview() {
    Drawer()
}