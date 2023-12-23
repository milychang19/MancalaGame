# MancalaGame

## Project Overview

The `MancalaGame` project is a Java implementation of the Mancala game, developed as a school assignment for the CIS2430 Intro to Object-Oriented Programming course. The game is designed to be interactive, offering players the ability to make moves, save and load games, and enjoy a graphical user interface (GUI). The implementation also includes various Mancala game rules, allowing users to change the rules dynamically.

## Features

1. **Countable and Serializable:**
    - The game implements the `Countable` and `Serializable` interfaces, enabling efficient counting of game elements and the capability to save and load game states.

2. **Inheritance Abstraction:**
    - Utilizes inheritance to create an abstraction for common functionality, promoting code reuse and maintainability.

3. **MancalaDataStructure:**
    - Implements `MancalaDataStructure` to efficiently iterate over pits during distribution.

4. **GameRules Abstraction:**
    - Utilizes the `GameRules` class to provide an abstraction for game rules, allowing for easy rule changes.

5. **Graphical User Interface (GUI):**
    - Provides a GUI that enables users to play the game seamlessly.

6. **User-Friendly Outputs:**
    - The game delivers clear and suitable output messages, informing the player about incorrect moves and announcing the winner at the end of the game.

## How to Compile and Run

To compile and run the program, follow these steps:

```
gradle build
java -jar build/libs/GameUI.java
```
## Game Actions

**Quit Game:**

Players can exit the game at any point.  
**Save/Load Game:**

Implements successful saving and loading of game states.  
**Change Game Rules:**

The GameRules class allows for dynamic rule changes.  
**Start New Game:**

Players can start a new game at any time.
