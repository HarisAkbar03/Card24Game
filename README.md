Card 24 Game
This is a Card 24 Game built with JavaFX, where players are given four cards and must use arithmetic operations to reach the result of 24. The game allows players to input mathematical expressions, and the app verifies if the expression is valid and evaluates to 24. Additionally, the app includes a brute force solver that attempts to find a solution by evaluating all possible combinations of card values and operations.

Features
Card Generation: Randomly generates four cards from a deck of standard cards.
Equation Input: Players can input an equation using the card values and basic arithmetic operations (+, -, *, /).
Equation Validation: Verifies if the input equation is valid and if it evaluates to 24.
Brute Force Solver: Attempts to find a valid solution by evaluating all possible combinations of the cards and arithmetic operations.
Hint Generator: Uses OpenAI's API to provide hints for solving the equation (requires an OpenAI API key).
Card Display: Displays card images dynamically based on randomly selected cards.
Prerequisites
To run the project, ensure you have the following installed:

Java 18 or higher
JavaFX (included in the project dependencies)
Maven (for dependency management and build)
OpenAI API Key (if you want to use the hint generator)
Setup
Clone the repository
Clone the repository to your local machine:

git clone https://github.com/HarisAkbar03/Card24Game.git
Import the project
Import the project into your IDE (e.g., IntelliJ IDEA, Eclipse) as a Maven project.
Ensure that Java 18 or higher is set as the project SDK.
Set up OpenAI API Key (Optional)
If you want to use the hint generation feature:

Sign up for OpenAI API access here.
Get your API key.
Add your API key in the OpenAIClient class to use the hint generation functionality.
Build and Run the Project
Build the project using Maven:

mvn clean install
Run the application:

In your IDE, run the HelloApplication class.
Alternatively, run from the command line:

mvn javafx:run
The application will open a window displaying four random cards and a text field where you can enter an equation. The app will evaluate the expression to check if it equals 24.

Project Structure
src/main/java/org/example/card24game/: Contains the main Java classes of the application.
HelloApplication.java: The main class that sets up the JavaFX interface and handles card generation, equation validation, and brute force solution.
OpenAIClient.java: (Optional) Handles interaction with OpenAI's API to generate hints for solving the equation.
src/main/resources/: Contains the resources for the project.
/cards/: Contains card image files (e.g., 2_of_clubs.png, king_of_spades.png).
style.css: Custom stylesheet for the JavaFX UI layout and design.
pom.xml: Maven configuration file for dependency management.
Usage
The application will display four randomly selected cards from the deck.
You can input an arithmetic equation using the values from the four cards in the text field.
Click "Verify" to check if the equation is valid and evaluates to 24.
You can click "Refresh" to generate a new set of cards.
Click "Find Solution" to get a hint or see the brute force solution if OpenAI's API is unavailable.
Example Usage
Generate the cards:

You will see cards such as "3 of hearts", "jack of spades", etc.
Enter an equation like:

3 + 5 * (2 - 1)
Click "Verify" to check if the equation evaluates to 24. If the equation is valid and evaluates to 24, you will receive a success message.

If the equation doesn't evaluate to 24, you can try another or click "Find Solution" to get a hint or brute force solution.

Contributing
Feel free to fork this repository and create pull requests if you want to contribute to the project. Please follow these steps:

Fork the repository.
Create a new branch (git checkout -b feature/your-feature).
Commit your changes (git commit -am 'Add new feature').
Push to the branch (git push origin feature/your-feature).
Create a new pull request.







