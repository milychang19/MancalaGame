# Mancala Game
Student Name: Emily Chang
Student Number: 1219592

## Description
This is a Java implementation of the Mancala game. The game is designed to be interactive, allowing players to make moves, save and load games, change rules, and enjoy a graphical user interface.

## Executing Program
Compile and Run:
```
gradle build
java -jar build/libs/GameUI.java

```
## Limitation

The assignment is completed

## Features
* Countable and Serializable: The game implements the Countable and Serializable interfaces, allowing for efficient counting of game elements and the ability to save and load game states.

* Inheritance Abstraction: Utilizes inheritance to create an abstraction for common functionality, promoting code reuse and maintainability.

* MancalaDataStructure: Implements MancalaDataStructure to efficiently iterate over pits during distribution.

* GameRules Abstraction: Utilizes the GameRules class to provide an abstraction for game rules, allowing for easy rule changes.

* Graphical User Interface (GUI): Provides a GUI that enables users to play the game seamlessly.

* User-Friendly Outputs: The game provides clear and suitable output messages, informing the player about incorrect moves and announcing the winner at the end of the game.

* Game Actions:
    * Quit Game: Players can exit the game at any point.
    * Save/Load Game: Implements successful saving and loading of game states.
    * Change Game Rules: The GameRules class allows for dynamic rule changes.
    * Start New Game: Players can start a new game at any time.

