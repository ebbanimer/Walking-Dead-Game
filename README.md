# Left to do
- Comment
- POM file (JAR)
- Material: readme
- Purpose: readme
- Section to how the game is executed: readme
- Procedures: readme
- Fix factory method?
Discussion: readme
Solution no higher than 15 jdk?

# Material
Microsoft Windows 10 Home 64-bit, IntelliJ IDEA 2021.3.3, Terminal,
java 19.0.1 2022-10-18
Java(TM) SE Runtime Environment (build 19.0.1+10-21)
Java HotSpot(TM) 64-Bit Server VM (build 19.0.1+10-21, mixed mode, sharing)
Apache Maven 3.8.5
& Git version 2.33.0.windows.2


# Purpose
The purpose of this final project is to create a GUI application based on the Swing library, running on the
Event Dispatch Thread (EDT). The aim is to achieve the highest grade A, which involves adding the following
concepts/functionalities;

- The following six design patterns:
  - Observer pattern
  - Producer/Consumer pattern
  - Object Pool pattern
  - Template Method pattern
  - Factory Method pattern
  - Abstract Factory pattern
- Five concurrent processes.
- Synchronization of three concurrent processes.
- Six different Swing components.
- Four different Layout managers.
- Three usages of Streams API.
- Functioning jar-file should be produced using Maven.

These six functionalities should be designed according to Model-View-Controller (MVC) architecture, separating
the model and the view, and having the controller govern the communication. The objective is to assemble a game inspired
by The Walking Dead TV series, having one selected character collecting food meanwhile being threatened by being
hit by a zombie. The intent is to have processes to select a character, and collect points by accumulating food items,
meanwhile having a timer counting down to the end of the game. A character should die if it gets hit by a zombie, or
have the option to advance to the next level if the character accomplishes winning the game. The ambition is to build
this application, based on given design patterns and functionalities such as concurrent processes, as mentioned above.

# Procedure
As the aim of the project was high, and it had multiple components and concepts to take into consideration, the initial
step was to set up a plan. More precisely, how would the application work? How would the functionalities, design patterns,
and other requirements be incorporated? A plan had to take form, to take each requirement step by step, to avoid the
feeling of overwhelm.

The plan was to create a game using Swing components, and somehow, the user would be able to select a character to
use throughout the game, and step by step, incorporate the rest of the functionalities. Hence, starting with Swing and
the creation of characters made sense. The start JFrame was created, using the Border-Layout manager to create a JPanel for
each responsibility. One had the responsibility to select a character, another one had to display the chosen character,
and one had to have buttons to initialize the game. Could a design pattern be applied here? Or concurrent processes or
Streams API? Well, the character had to be chosen. The characters were created using simple factory out of personal
preference, but the belonging weapon was created using the factory method pattern. However, how would the weapons be 
incorporated here? Where should the characters be created, in the controller or in the model?  This was the main issue 
throughout the rest of the application, as Model should contain the data, View contains the GUI component and the 
displayed data, and Controller is responsible for making decisions and communicating these data between Model and View.

Characters and weapons are a part of the data - thus, they were placed in Model, to be filtered based on what the user 
chose in View, which was sent through via Controller. It was important that the View and Model were completely separated, 
which led to that the controller containing a lot of code as there was a lot of happening in the background of the
application. Next off, can any other requirement from the purpose be incorporated? Well, why not include Streams API? 
Perhaps, it will look forced, as the application works completely fine without using them at this point, but Streams API 
could indeed be used for sorting and filtering through the characters.

At this point, once a valid character has been chosen, the user is ready to start the game. But, the controller had grown
to contain a lot of code, including action listeners and communication between View and Model. Was there too much code?
Should the code be distributed to View or Model? As mentioned above, this was an issue throughout the program. However,
the choice was to keep the action listeners in the Controller, as the action listener needed information from the model, and
the view should be limited to the GUI behavior and the design of the GUI. Thus, the next thing was to make an instance of a
new controller - the GameController. Once opening the new GameController when the game should start, suddenly, all data
was lost as a new instance of the Model was created. Should the model be created as a Singleton instead, so it only
requires to be initialized once? Well, then the same data will be kept if the user restarts the game, and that is
not as desired. Additionally, remembering from assignment 3, none of the actors should be a singleton... Even though
it perhaps is not readable by passing the model to different classes, it would be the salvation of this issue.

The purpose of the GameController had to be the following;
- Creating zombies and incorporating an animation for them to move around.
- Creating food items.
- Initialize a key-listener so the actor could move around.
- Keeping track of the score.

All this would have to be done by controlling the communication between the data and the view. As zombies and foods
are characters themselves, they were created within the model using an abstract factory. However, it was the controller
initializing the creation, as the controller makes the decisions. The key listener was also created in the controller,
however, it seemed like the controller would have too much logic by placing this in the controller, using the information
from the user interaction in the view. How much should the controller take care of? The view should not
be responsible for detecting collisions, but the model should not be aware of what is happening in the view. Thus, the
decision was to place this in the controller, based on the responsibilities model and view not should have. Hence, all
key events were determined in the controller and made decisions whether it was a collision or not, and update values
in data and view based on this. The detection of collisions, the moving of x- and y-coordinates of the character,
determining if it was a win, and stopping the game or increasing the score based on this.

At this point, it started to become the game that was envisioned. However, there were still some issues and some
functionalities/design patterns were still not incorporated. Where should the animation of the zombie be placed? Should
the zombie animation be implemented as runnable and running in a Thread, or timers and timer tasks? Is there any way to
include some synchronization of the concurrent processes? Why are images not being displayed in the jar file?

To overcome these issues, there were different approaches to be used to incorporate zombie animation. Should their
coordinates be dynamically updated in the model, and the controller retrieves these values by using a thread together with 
sleep to get the desired interval? Or, should a TimerTask be implemented and run by a timer in the controller? Well, the 
latter choice was the decision, as it made sense to implement a timer that would execute a task at a certain interval. 
The TimerTask would have to be implemented in the controller, as otherwise, the model would be aware of the GUI, or the 
view would handle too much logic. Hence, the TimerTask was created in a separate class in the controller, being started 
in GameController, to have the zombies moving simultaneously in a concurrent fashion. The Timer and TimerTask are working
in a background thread, but some synchronized threads and processes somehow needed to be included as well. This was more 
of a "shoehorned" added functionality, which ended up being included in the start frame. Another JPanel was created, with
an animated zombie, increasing and decreasing, with producer- and consumer threads working in the background, applying 
the producer and consumer pattern.

At this point, all patterns, functionalities, and concepts were included - now it was time to produce the JAR file.
However, the images were not displayed in the JAR file as they were in the console. In the target folder where Maven
had generated the jar file, there were no images to be found. This was solved by moving the images to the resource-folder,
to be able to access them from the jar file and the console. The images had to instead be loaded from the resource folder,
using the ClassLoader interface.

# Discussion
The final product of this game fulfills the requirements of this assignment. It contains six Swing components;
- JLabel.
- JPanel.
- JCombobox.
- JButton.
- JOptionPane.
- JFrame.

It contains four Layout managers;
- BorderLayout.
- FlowLayout.
- SpringLayout.
- GridBagLayout.

It includes three usages of Streams API;
- .sorted() is used in sortCharacterNames in Model.
- .filter() is used in createCharacter in Model.
- .findFirst() is used in removeConsumer and removeProducer in ThreadSizeManager.

It contains over three synchronized concurrent processes;
- addSize() and removeSize() in SizePool.
- addConsumer(), removeConsumer(), addProducer(), and removeProducer() in ThreadSizeManager.

It contains following concurrent processes;
- Amount of maximum zombies is 10, as 10 different Timers are used simultaneously in different background Threads.
- Amount of consumers + producers starts with eight threads in total, and increases/decreases.

It includes all mentioned six design patterns;
- Observer pattern is used in SizePool as Observable, and ThreadSizeManager as Observer,
  Observer pattern is also used in Model as Observable, and StartController as Observer.
- Producer/Consumer pattern is used for increasing/decreasing size of the animation in StartView of the Zombie.
- Template Method is used for LevelMethods, with subclasses LevelOne and LevelTwo.
- Abstract Factory pattern is used for creating Zombies and Foods.
- Object Pool pattern is used for distributing foods and zombies in Model to GameController.
- Factory Method pattern is used for creating Weapons in Model.

The entire application is constructed based on the MVC pattern, distributing responsibilities to each class. 
The subpackage of models contains classes for handling data, the subpackage of view contains classes for the
GUI design, and the subpackage of controllers contains classes for handling communication between the models 
and view. The view is getting updated based on what is happening behind the scenes in controller and model,
not being aware of the model, and the model is providing and handling data based on what the controller requests,
not being aware of the view. It is running on the EDT and the jar-file has been successfully generated using
Maven. 

On top of these technical requirements, the game has been developed according to the original idea, mentioned
in purpose. The explanation of the final game execution is detailed in the section of Game Execution further
down below. 

This choice of this assignment has provided a lot of freedom, which has been very appreciated. It has enabled the 
students to develop an application of their own interest, however, limited to given requirements. The choice of 
application in this project was chosen based on a personal interest, as it has been a TV-show that has been followed for 
several years. Developing an application using knowledge from the course, but applying it on a project of personal choice,
led to being indeed tempting to developing a game based on personal interest.

At times, it felt like the choice of the functionalities and goals were a bit overwhelming, and perhaps overkill,
but the fact that it was based on a personal interest, it keeps the motivation up for learning new relevant concepts to 
this game, and coming up with ideas with how to implement certain requirements that did not really belong to the game 
itself. One example of this, was the usage of Streams API. In createCharacter(), the method could easily be done in a 
foreach loop to find the character based on the provided characterName, but that would not conform to the Streams API 
requirement. Additionally, it would come into handy to use a Singleton in certain places, but that would not conform to 
the idea of the MVC architecture. 

Aside from the examples mentioned above, some other concepts that were not included in the game-functionality
and were forced into it to conform to the requirements, but ended up being a quite nice touch. The primary
example of this is the animation of the zombie in the StartView. This was not included at first and not really
according to the plan, but a producer/consumer pattern was missing - why not include an animation in the StartView? It
was a nice touch to the introduction of the game, giving the user an idea of the zombie-theme. 

Another concept that was quite forced was the abstract factory pattern, template method, and factory method pattern. 
The initial thoughts were to create one type of zombie and one typ of food, which could have been done without a factory.
However, due to the requirements, factories had to be included with more options on types of items, and template method 
to create two levels. If two levels was created, this could have been done without classes as LevelOne and LevelTwo, and 
instead directly decided in controller/model. To mention the factory method pattern, preferably the weapon could be
initialized within the character classes themselves, by adding the variable weapon. But, as mentioned earlier, the
application had to include a factory method pattern, and simple factory pattern was out of personal choice in creating
the characters, which led to the decision of separating these to include factory method. 

But all this being said, it is good practice using these patterns, as they were not used in previous assignments. The 
benefits of using these patterns can definitely be seen, especially when adding new objects within the categories. For 
instance, if a third zombie would be included in the future, it would be as simple as just extending the super-class 
and set the values to this zombie in particular. However, for a small application as this, it may seem like an overkill 
with adding more classes than necessary and clutter. 




## Game execution
When the application runs, the user will see a JFrame containing four JPanels. One JPanel has a JCombobox where the user
selects a game character (Rick, Michonne, Eugene, or Daryl). These are sorted by name using Streams API. Once the user
has selected a character, the second JPanel will display an image of the chosen character together with the characters'
weapon. The characters are built using a factory method pattern. The third JPanel will display an animation of a zombie,
that is increasing or decreasing.
The fourth JPanel will have two buttons; Start Game and Instructions.
If the user clicks on Instructions, the user will see a JOptionPane containing the instructions of the game, which are;
- The character will collect food items by moving using the arrow keys.
- If the character gets too close to a zombie, the character will die, and it is game over.
- If the character collects all food before the time is up (30 seconds), the character wins and will have the choice to go
  to the next level. Otherwise, the game is canceled.
- In level two, the character will battle against 10 zombies and will have 10 foods to collect.

If the user clicks on the Start Game button, a new JFrame will be displayed containing three JPanels. One GamePanel, one ButtonPanel,
and one StatsPanel. The GamePanel will display a small image of the chosen character, five zombies, and five food items.
The zombies are moving simultaneously, the food items are still, and the character will be moving around using arrow keys.
When a character collides with a food item, the food item will disappear on the GamePanel and the score will increase by
one point. If the character collides with a zombie, the game is over. The same concept goes for level two, but level two
will have 10 food items and 10 zombies.

The second JPanel will contain the stats for the game, which are time counting down, score, food left, and amount of
zombies. These will get updated throughout the game.

The third JPanel will contain three buttons, one for exiting the game, and one for ending the game. If the user clicks on
End Game, the timer will stop and the key listener will be disabled. If the user clicks on Exit Game, the JFrame will
be disposed, but the StartView is still displayed so the user can have the option to select another character and run
the game again.



# Discussion points:

Model is used in many places and could be created as a singleton to avoid passing around model instance to different classes
for more readability, however, then the model class will remain with same objects if the user exits/wins/loses the game and
restart the game, e.g. will have 10 foods instead of 5.

Some things are unnecessary for example stream api, only to squeeze it in.

Abstract factory is implemented with interfaces, as it was straightforward and some tutorials showed it's possible.

ZombieAnimation in controller, because it works with JLabels, and model should not know about view,
and view should not have this much logic, so i decided to put it in controller folder.

Observer/observable could have been used for zombie animation, and zombies could have been implemented
as Runnables. However, it's more readable to have a separate class for the animation.

Observer could be view, but chose controller.

Starting timer in controller, as timer per se is not business logic, thus not suitable in model.

Starting threads in controller.

If I had chosen, model would act as a manager of object pool for zombies/food, that will be re-used for next level.

ThreadSizeManager handles amount of threads and operations on threads, meanwhile the controller start them,
and verify the size amount. The controller observes the pool for changes.
