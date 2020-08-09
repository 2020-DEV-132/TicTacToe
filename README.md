# TicTacToe
## Rules

- X always goes first.
- Players cannot play on a played position.
- Players alternate placing X’s and O’s on the board until either:
  - One player has three in a row, horizontally, vertically or diagonally
  - All nine squares are filled.
- If a player is able to draw three X’s or three O’s in a row, that player wins.
- If all nine squares are filled and neither player has three in a row, the game is a draw.

## How to run
Prerequisites:

- Installed JDK
- Installed Android SDK
- Have an Android emulator or real device attached to your pc with developer options enabled.
- Optionally installed Android Studio

Download the project.
You can choose to run it from Android Studio or not

### Android Studio
- `File` > `Open` select the root of the project.
- Click the play icon or <kbd>CMD</kbd> + <kbd>R</kbd>
- Grab a coffee

#### Tests
- Open a terminal in the root of the project
- Run `./gradlew test`

### Command Line
- Navigate to the root of the project
- Run `./gradlew installDebug`
- Grab a coffee

### Tests
- Open a terminal in the root of the project
- Run `./gradlew test`
