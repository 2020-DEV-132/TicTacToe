package be.bnp.tictactoe

import android.view.View
import android.widget.Button
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import be.bnp.tictactoe.CellRobot.Companion.tap
import be.bnp.tictactoe.GameOverDialog.Companion.gameOverDialog
import be.bnp.tictactoe.ResetButton.Companion.replayButton
import be.bnp.tictactoe.model.Coordinate
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("UNCHECKED_CAST")
interface Robot<V : View> {
    val viewMatcher: Matcher<V>
    fun tap() {
        onView(viewMatcher as Matcher<View>).perform(click())
    }

    fun isShown() {
        onView(viewMatcher as Matcher<View>).check(matches(isDisplayed()))
    }
}

class CellRobot(private val coordinate: Coordinate) : Robot<Button> {
    override val viewMatcher: Matcher<Button> =
        allOf(withTagValue(object : BaseMatcher<Any>() {
            override fun describeTo(description: Description?) {
                description?.appendText("with coordinates x:${coordinate.x}, y:${coordinate.y}")
            }

            override fun matches(item: Any?): Boolean =
                item.toString() == "${coordinate.x},${coordinate.y}"
        }))

    companion object {
        fun tap(coordinate: Pair<Int, Int>) {
            CellRobot(Coordinate(coordinate.first, coordinate.second)).tap()
        }
    }
}

class GameOverDialog(text: String) : Robot<View> {
    override val viewMatcher: Matcher<View> = withText(text)

    companion object {
        fun gameOverDialog(text: String, performAction: GameOverDialog.() -> Unit) =
            GameOverDialog(text).performAction()
    }
}

class ResetButton : Robot<View> {
    override val viewMatcher: Matcher<View> = withText("Replay")

    companion object {
        fun replayButton(performAction: ResetButton.() -> Unit) =
            ResetButton().performAction()
    }
}

@RunWith(AndroidJUnit4::class)
@LargeTest
class TicTacToeUiTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun xWinsTheGame() {
        //X
        tap(0 to 0)
        //O
        tap(1 to 0)
        //X
        tap(1 to 1)
        //O
        tap(2 to 0)
        //X
        tap(2 to 2)

        gameOverDialog("We have a winner! X") {
            isShown()
        }
    }

    @Test
    fun oWinsTheGame() {
        //X
        tap(0 to 0)
        //O
        tap(0 to 1)
        //X
        tap(1 to 0)
        //O
        tap(1 to 1)
        //X
        tap(2 to 2)
        //O
        tap(2 to 1)

        gameOverDialog("We have a winner! O") {
            isShown()
        }
    }

    @Test
    fun tie() {
        tap(0 to 0)
        tap(0 to 1)
        tap(0 to 2)

        tap(1 to 1)
        tap(1 to 0)
        tap(1 to 2)

        tap(2 to 1)
        tap(2 to 0)
        tap(2 to 2)

        gameOverDialog("We have a tie! Nobody won. Better luck next time!") {
            isShown()
        }
    }

    @Test
    fun replayResetsTheBoard() {
        xWinsTheGame()

        replayButton {
            tap()
        }

        oWinsTheGame()
    }
}
