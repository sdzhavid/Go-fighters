# Go-Fighters

#Description
This Application creates heroes and lets them fight in turns.
Each hero attacks and defends once per turn.
The game ends when one of the heroes which are fighting, dies.

#Usage
Create an Instance of GameEngine - GameEngine gameEngine = new GameEngine();
Create Two heroes of a particular class and pass them correct parameters - E.G Hero firstHero = new Warrior(200, 3000, 300);
Then simply call the startGame with both heroes passed as parameters method on gameEngine and enjoy the battle - gameEngine.startGame(firstHero, secondHero);
