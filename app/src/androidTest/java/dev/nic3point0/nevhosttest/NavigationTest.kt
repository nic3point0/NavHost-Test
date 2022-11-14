package dev.nic3point0.nevhosttest

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    lateinit var navController: TestNavHostController

    @Before
    fun initNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NevHostTestApp(navController = navController)
        }
    }


    @Test
    fun navHost_verifyStartDestination() {

        assertEquals(NavigationRouts.OVERVIEW.name, navController.currentBackStackEntry?.destination?.route)

    }


    /**
     * FIXME this test is failing, the expected destination is not reached
     */
    @Test
    fun navHost_navigateToAbout_verifyDestinationIsReached(){

        // Open navigation drawer
        val menuIconDesc = composeTestRule.activity.getString(R.string.icon_desc_menu)
        composeTestRule.onNodeWithContentDescription(menuIconDesc).performClick()

        // Click "Go To About Screen" button on the navigation drawer
        val navButtonDesc = composeTestRule.activity.getString(R.string.btn_goto_about)
        composeTestRule.onNodeWithText(navButtonDesc).assertExists()

        // Assert About Screen destination was reached
        assertEquals(NavigationRouts.ABOUT.name, navController.currentBackStackEntry?.destination?.route)

    }


}