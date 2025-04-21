
# **Card 24 Game 🃏🎮**

Welcome to the **Card 24 Game**! This fun puzzle game challenges players to use arithmetic operations to reach the target number **24** using four randomly drawn cards from a standard deck.

The game includes several exciting features like equation validation, a brute force solver, and an optional **Hint Generator** powered by **OpenAI's API**.

----------

## 🌟 **Features**

-   **Card Generation**: Randomly selects four cards from a standard deck.
    
-   **Equation Input**: Players input equations using card values and basic arithmetic operations (`+`, `-`, `*`, `/`).
    
-   **Equation Validation**: Verifies if the entered equation is valid and if it evaluates to 24.
    
-   **Brute Force Solver**: Attempts to find a valid solution by evaluating all possible combinations of cards and arithmetic operations.
    
-   **Hint Generator**: Uses OpenAI's API to provide hints for solving the equation (**API key required**).
    
-   **Card Display**: Dynamically displays card images based on the randomly selected cards.
    

----------

## 🛠️ **Prerequisites**

To run this project, make sure you have the following installed:

-   **Java 18** or higher
    
-   **JavaFX** (included in the project dependencies)
    
-   **Maven** (for dependency management and building the project)
    
-   **OpenAI API Key** (for hint generation)
    

----------

## 🚀 **Setup Instructions**

### 1. **Clone the Repository**
    
  Clone the project to your local machine using Git:
  
    git clone https://github.com/HarisAkbar03/Card24Game.git` 

### 2. **Import the Project**

Import the project into your IDE (e.g., IntelliJ IDEA, Eclipse) as a **Maven** project.  
Ensure that **Java 18** or higher is set as the project SDK.

### 3. **Set Up OpenAI API Key (Optional)**

If you want to use the **Hint Generator** feature:

1.  Sign up for **OpenAI API** access [here](https://beta.openai.com/signup/).
    
2.  Obtain your **API Key**.
    
3.  Add the API key in the `OpenAIClient.java` class to enable the hint generation functionality.
    

### 4. **Build and Run the Project**

-   **Build the project** using Maven:
    `mvn clean install` 
    
-   **Run the application**:
    
    -   In your IDE, run the `HelloApplication.java` class.
        
    -   Alternatively, run from the command line:
        
    `mvn javafx:run` 
    

Once the application is running, a window will open displaying four random cards. You can then input an arithmetic equation and verify if it evaluates to **24**!

----------

## 🗂️ **Project Structure**

Here’s a brief overview of the project structure:

-   **`src/main/java/org/example/card24game/`**: Contains the main Java classes of the application.
    
    -   `HelloApplication.java`: The main class that sets up the JavaFX interface, handles card generation, equation validation, and brute force solution.
        
    -   `OpenAIClient.java`: (Optional) Handles interaction with OpenAI's API for generating hints.
        
-   **`src/main/resources/`**: Contains the resources for the project.
    
    -   **`/cards/`**: Contains the card image files (e.g., `2_of_clubs.png`, `king_of_spades.png`).
        
    -   `style.css`: Custom stylesheet for the JavaFX UI layout and design.
        
-   **`pom.xml`**: Maven configuration file for managing dependencies.
    

----------

## 🎮 **Usage**

1.  **Generate Cards**: Upon starting the application, four random cards will be displayed.
    
2.  **Enter an Equation**: Use the values from the four cards to create an arithmetic equation in the text field.
    
    -   Example: `3 + 5 * (2 - 1)`
        
3.  **Verify the Equation**: Click "Verify" to check if your equation evaluates to **24**. If valid, a success message will appear.
    
4.  **Refresh**: Click "Refresh" to generate a new set of cards.
    
5.  **Find Solution**: If your equation doesn't work, click "Find Solution" to either get a hint or see the brute force solution (if OpenAI API is unavailable).
    

----------

## 💡 **Example Usage**

-   **Generated Cards**:
    
    -   "3 of hearts", "jack of spades", "2 of diamonds", "king of clubs"
        
-   **Input Equation**:
    
    -   `3 + 5 * (2 - 1)`
        
-   **Click "Verify"**:
    
    -   If the equation evaluates to **24**, you'll see a success message. If not, try another equation or click **Find Solution** to get help.
        

----------

## 💬 **Contributing**

We welcome contributions! If you'd like to improve or add to this project, follow these steps:

1.  **Fork** the repository.
    
2.  **Create a new branch** for your feature:
    
    `git checkout -b feature/your-feature` 
    
3.  **Commit** your changes:
   \
    
    `git commit -am 'Add new feature'` 
    
4.  **Push** to your branch:

    `git push origin feature/your-feature` 
    
5.  **Create a pull request** to merge your changes.
