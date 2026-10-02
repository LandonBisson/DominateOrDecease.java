<h1 align="center">Dominate Or Decease</h1>

<h2 align="center">
    <a href="https://github.com/LandonBisson">Landon Bisson</a> &nbsp&nbsp&nbsp
    <a href="https://github.com/nikocalabro">Niko Calabro</a> &nbsp&nbsp&nbsp
    <a> Coltrane Creed </a> &nbsp&nbsp
    <a> Wesley Richey </a> 
</h2>

<hr>

## Description

A multiplayer PvP turn based combat game, using spells and attacks to remove your opponents 
from the board. 2-6 players can choose between nine different classes, allowing them to have 
different attacks and change their gameplay every time. Whether that be trying to heal, run away, power 
up one's special move, or just go straight for the K.O. Each class also have a unique character design, including voice lines.
The list is as follows: Artificer, Barbarian, Bard, Cleric, Druid, Monk, Ranger, Rogue, and Wizard.
The board is made out of seven hexagon tiles, making the middle tile open to attacks on all six sides. 
Each tile is unique, making positioning one of the most important aspects to winning the game. This is a game of intensive strategy, starting from choosing
which class to pick. However, while the game focuses on the many different game plans, the winner might just
be decided on who is the luckiest. 

## Features
### Character Classes
9 Character Classes making 27 unique voice lines.

![Character Classes](screenshots/character_classes.png)

### Board

Board is constructed of 7 uniquely designed tiles with different effects and working hexagonal bounding boxes.
<ul>
  <li>Top Left: 75% to get the top left fourth of the orb.</li>
  <li>Top Right: 75% to get the top right fourth of the orb.</li>
  <li>Middle Left: Do 1 more damage on your next attack this turn.</li>
  <li>Middle Middle: Heal 3 health.</li>
  <li>Middle Right: Take -1 damage from attacks this turn.</li>
  <li>Bottom Left: 75% to get the bottom left fourth of the orb.</li>
  <li>Bottom Right: 75% to get the bottom right fourth of the orb.</li>
</ul>

![Board](screenshots/board.png)

### Dice

We made 3 different types of dice to accommodate:
<ul>
  <li>1d2, 2d2, 3d2</li>
  <li>1d4, 2d4, 3d4</li>
  <li>1d6, 2d6, 3d6</li>
</ul>
Players can click the dice on their turn for a dice rolling (or coin flipping) animation, resulting in a random 
combination of numbers. Each attack form each character rolls different dice and of different amounts.

![coin](assets/Images/coin.gif) ![d4](assets/Images/d4.gif) ![d6](assets/Images/d6.gif)

### Rules

Dominate Or Decease is an original game with an original and complex rule set. 

![Rules](assets/Images/rulesDorD.jpg)

## Installation
```bash
git clone https://github.com/landonbisson/dominate-or-decease.git
```

## Repository Structure
```
DominateOrDecease/
├───assets/
│   ├───attackAbilitySuper/
│   │   ├───artificer
│   │   ├───barbarian
│   │   ├───bard
│   │   ├───cleric
│   │   ├───druid
│   │   ├───monk
│   │   ├───ranger
│   │   ├───rogue
│   │   └───wizard
│   ├───Images/
│   └───Songs/
├───screenshots/
└───src/
    │   Board.java
    │   Cannon.java
    │   Class.java
    │   Dice.java
    │   DominateOrDecease.java
    │   Images.java
    │   MainMenu.java
    │   Mouse.java
    │   Placeables.java
    │   Player.java
    │   Poison.java
    │   Shield.java
    │   Sounds.java
    │   SpikeGrowth.java
    │   Tile.java
    │   Window.java
    │   
    └───CharacterClasses/
            Artificer.java
            Barbarian.java
            Bard.java
            Cleric.java
            Druid.java
            Monk.java
            Ranger.java
            Rogue.java
            Wizard.java
```
