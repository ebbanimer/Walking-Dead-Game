# Zombie Adventure Game: Swing-Based Java Application
### Overview
This project showcases a GUI application built using the Swing library in Java, designed with an MVC architecture to 
implement advanced programming concepts. The application is a game inspired by `The Walking Dead`, featuring player character 
selection, zombie animations, and interactive gameplay. It highlights the use of concurrency, design patterns, and the 
Streams API to deliver a dynamic and engaging user experience.

### Key Features

- Design Patterns: Implemented six patterns to ensure modularity and reusability:
  - Observer: For tracking state changes in the model. 
  - Producer/Consumer: For synchronized zombie animations. 
  - Object Pool: For distributing game items like zombies and food. 
  - Template Method: For level-specific game logic. 
  - Abstract Factory: For creating zombies and food items. 
  - Factory Method: For generating character weapons.
- Concurrency: Utilized synchronized processes, multi-threading (e.g., timers for animations), and producer-consumer mechanics.
- Streams API: Used for filtering, sorting, and managing game data.
- Swing Components: Designed an intuitive UI with components like JPanel, JButton, JLabel, and multiple layout managers.
- Maven Build: Packaged the game into a runnable JAR file with resources properly managed.
  
### Gameplay
Players choose a character, navigate through levels, and interact with dynamically generated zombies and food items. The game includes animations, score tracking, and level-based challenges. Zombies move concurrently using TimerTask, and game state management ensures responsiveness and interactivity.

### Learning Outcomes
This project deepened my understanding of key programming concepts, such as:

- Designing scalable and maintainable applications using MVC.
- Efficiently applying concurrency and synchronization in real-time processes.
- Leveraging design patterns to enhance code structure and flexibility.
- Integrating Java Streams for cleaner and more expressive data processing.
By combining creativity with technical requirements, I developed a project that demonstrates both problem-solving skills and a commitment to learning new concepts.