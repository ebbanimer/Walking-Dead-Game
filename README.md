# Material
Microsoft Windows 10 Home 64-bit, IntelliJ IDEA 2021.3.3, Terminal,
java 19.0.1 2022-10-18
Java(TM) SE Runtime Environment (build 19.0.1+10-21)
Java HotSpot(TM) 64-Bit Server VM (build 19.0.1+10-21, mixed mode, sharing)
Apache Maven 3.8.5
& Git version 2.33.0.windows.2


# Purpose
The purpose of this final project is to comprehend the concepts of concurrency, design patterns used throughout the course, 
Swing library, and Streams API. The goal is to get an understanding of how these can be applied in a GUI application
built with the Swing library, and how they can be incorporated into working with each other, applying it to a possible 
real-life project. To be more specific, the objective is to build an application based on the following technical 
requirements;

Create a GUI application based on the Swing library, running on the Event Dispatch Thread (EDT). The aim is to achieve 
the highest grade A, which involves adding the following concepts/functionalities;

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
- Functioning jar file should be produced using Maven.

These six functionalities should be designed according to Model-View-Controller (MVC) architecture, separating the model 
and the view, and having the controller govern the communication.

# Procedure

### Setting up a plan
As the aim of the project was high, and it had multiple components and concepts to take into consideration, the initial 
step was to set up a plan. More precisely, what type of application should be assembled? What type of functionalities 
would the application have? How would the concepts, design patterns, and other requirements be incorporated? A plan had 
to take form, to take each requirement step by step, to avoid the feeling overwhelm.

The plan was to create a game inspired by the TV show “The Walking Dead” using Swing components, and somehow, the user 
would be able to select a character to use throughout the game, and step by step, incorporate the rest of the 
functionalities. Hence, starting with Swing and the creation of characters was the initial approach. 

### Preparation of game; Selecting and creation of characters
The start JFrame was created, using the Border-Layout manager to create a JPanel for each responsibility. One had the 
responsibility to select a character, another one had to display the chosen character, and one had to have buttons to 
initialize the game. Could a design pattern be applied here? Or concurrent processes or Streams API? As mentioned, the character 
had to be picked by the user. The characters were created using a simple factory, but could the factory method be 
incorporated here somehow? The decision was to separate the weaponry to a factory method, as the weaponry was not really 
useful for the game per se, and it could easily represent itself, being matched to each character. However, how would 
the weapons be incorporated here? Where should the characters be created, in the controller or the model? This was the 
main issue that persisted throughout the rest of the application, where to put each functionality being implemented. As 
the model should contain the data, the view contains the GUI components and the displayed data, and the controller is 
responsible for making decisions and communicating these data between the model the view.

Characters and weaponry were included in the model as they were part of the data and would be filtered according to the 
user's selections in the view, which was given via the controller as the view and the model needed to be completely 
separated. Next, can any further purpose-related requirements be included? Why not incorporate the Streams API? Although
it might appear forced at this stage because the program now functions just fine without them, the Streams API could be 
used to sort and filter the characters.

### Initialization of game; creation and management of items
The player is now prepared to begin the game after selecting a viable character. However, the controller has expanded 
to include a substantial amount of code, such as action listeners and interactions between the view and model. Should 
the code be distributed to the view or the model? The decision was made to keep the action listeners in the controller 
as they need data from the model and the view, and the view should only be used to control how the GUI behaves and looks. 
Making an instance of a new controller, the GameController, was what came next. All data was suddenly lost as soon as the new 
GameController was opened when the game was supposed to begin because a new instance of the model had been generated. 
Should the model instead be built as a Singleton, requiring only one initialization? If the player restarts the game, 
the same data will be maintained, which is not what is wanted. In addition, keep in mind that none of the actors should 
be singletons based on assignment 3. Even though it perhaps is not readable by passing the model to other classes, it 
would be the solution to this problem.

The functionalities of the GameController had to be the following;
- Creating zombies and incorporating an animation for them to move around.
- Creating food items.
- Initialize a key listener so the actor could move around.
- Keeping track of the score.

Controlling the communication between the model and the view would be necessary to do this. As zombies and foods are in 
one way characters themselves in this application, they were created within the model using an abstract factory, which further contains 
factory methods for each item. An opportunity to incorporate the template method pattern appeared at this point. As the 
template method is a behavioral pattern, the game could be divided into two levels. Each level would consist of a common 
behavior, and a separate behavior. This was demonstrated by creating the same type of food items for both levels, but 
two different zombie items.

### Game simulation; concurrent processes, action-listeners
The model was responsible for keeping these data and distributing the items once requested by the controller. The model 
stored the items in lists, hence the model was created to also act as a distributor, to get and return items by and from 
the calling client. All these interactions were done from the controller, and aside from that, the key listener was also 
created in the controller, however, it seemed like the controller would have too much logic by placing this in the 
controller, using the information from the user interaction in the view. How much responsibility should the controller 
bear? While the model shouldn't be aware of what is happening in the view, neither the view nor the model should be in 
charge of detecting collisions for this reason. Thus, the decision was to place this in the controller to manage these interactions, 
using Streams API to find matching coordinates. The key listener's activities were used to stop the game or raise the 
score, detect collisions, move the character's x- and y-coordinates, and determine if it was a win.

It started to take on the form of the game that was intended at this stage. However, there were still some problems, and 
some functionalities and design principles were still not incorporated. Where should the zombie animation be placed? 
Should timers and timer tasks be used instead of runnable threads to implement the zombie animation? Is there any way 
to include some synchronization of the concurrent processes?

To overcome these issues, there were different approaches to be used to incorporate zombie animation. Should their 
coordinates be dynamically updated in the model, and the controller retrieves these values by using a thread together 
with sleep to get the desired interval? Or, should a TimerTask be implemented and run by a timer in the controller? The 
decision was taken to use the latter option since it was appropriate to set up a timer to do a task at predetermined 
intervals. The TimerTask would have to be implemented in the controller, as otherwise, the model would be aware of the 
GUI, or the view would handle too much logic. To enable the zombies to move concurrently and simultaneously, the 
TimerTask was developed in a separate class in the controller and executed in GameController.

Although the Timer and TimerTask are running in a separate background thread, it was still necessary to integrate some 
synchronized threads and processes. This was more of a "shoehorned" extra feature that was eventually incorporated into 
the start frame. The producer and consumer patterns were used to construct a new JPanel with an animated zombie that 
increased and decreased while producer and consumer threads ran in the background. The processes of doing this were 
synchronized and applying the observer pattern so that the SizeController could observe changes in the size, that was 
altered in the pool. A ThreadSizeManager was included, to manage the number of threads. The third usage of Streams API 
was included here, to remove threads if desired.

### Final touch; jar-file
All patterns, features, and concepts had now been incorporated; the jar file needed to be created. However, unlike in 
the console, the graphics were not visible in the jar file. There were no images in the target folder where Maven had 
created the jar file. To access the photos from the jar file and the console, the problem was fixed by transferring the 
images to the resource folder. Rather, the ClassLoader interface had to be used to load the images from the resource 
folder.

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

It contains the following concurrent processes;
- Amount of maximum zombies is 10, as 10 different Timers are used simultaneously in different background threads.
- Amount of consumers + producers starts with eight threads in total, and increases/decreases.

It includes all mentioned six design patterns;
- Observer pattern is used in SizePool as Observable, and ThreadSizeManager as Observer,
  The observer pattern is also used in Model as Observable, and StartController as Observer.
- Producer/Consumer pattern is used for increasing/decreasing the size of the animation in StartView of the Zombie.
- Template Method is used for LevelMethods, with subclasses LevelOne and LevelTwo.
- Abstract Factory pattern is used for creating Zombies and Foods.
- Object Pool pattern is used for distributing foods and zombies in Model to GameController.
- Factory Method pattern is used for creating Weapons in the Model.

The MVC approach was used to build the entire application, assigning duties to each class. The subpackage of controllers 
includes classes for handling communication between the models and view, the subpackage of views contains classes for 
the GUI design, and the subpackage of models has classes for handling data handling. Without being aware of the model, 
the view is updated based on what is going on in the controller and model behind the scenes, and the model is processing 
and giving data based on what the controller wants without being aware of the view. The jar file was successfully 
produced using Maven, and it is operating on EDT.

In addition to these technical specifications, the game was created in accordance with the original concept outlined in 
the plan. The below-level section on game execution provides a full explanation of the final game execution.

This assignment has provided a great deal of independence. It has enabled the students to create an application of their
choosing while adhering to the guidelines. The selected choice of type of application to develop was chosen based on 
personal interest. A popular TV program from the past few years served as the basis for this application. The motivation 
was undoubtedly ignited by creating an application using the course material while also using it on a project of one's 
own choosing. The decision to create an application that would incorporate all the technical requirements occasionally 
felt overbearing, but because it was driven by personal interest, the motivation to learn new concepts that were relevant
to this game and come up with solutions for implementing requirements that weren't part of the game itself was kept. The 
use of the Streams API was one illustration of this. The technique could easily be done in a foreach loop in 
createCharacter() to find the character based on the characterName specified, but that would not meet the Streams API 
requirement. However, to give credit to Streams API, you can agree on that it is a very valuable tool to use for handling
large data, and looks more sophisticated than to use a larger for-loop. It is more efficient, and you can see the benefits
of learning and applying this concept to carrying on for future larger applications, regardless of whether it was necessary 
in this game or not. 

Aside from the example mentioned above, there are some more ideas that were imposed on the game functionality to meet 
the requirements but ultimately turned out to be a good touch. The zombie animation in the StartView serves as the best
illustration of this. A producer/consumer pattern was missing, so why not add animation to the StartView? This wasn't 
initially included and wasn't actually in the idea. It was a good touch to the game's start that gave the player a sense 
of its zombie theme. Learning how to manage synchronized processes in a multithreaded environment, and identify where
it has a benefit to include synchronized processes, is a skill a good programmer should possess. 

The abstract factory pattern, template method, and factory method pattern were additional somewhat forced concepts in 
this particular project. One sort of food and one type of zombie were the early ideas, which could have been accomplished 
without a factory. However, due to the requirements, factories had to be included with more options on types of items, 
and template methods to create two levels. If two levels needed to be built, this might have been done directly in the 
controller or model instead of using classes like LevelOne and LevelTwo.  To mention the factory method pattern, it 
would be preferable if the variable weapon could be added inside the character classes itself to initialize the weapon. 
However, as was already indicated, the application had to have a factory method pattern, and since the characters were 
created in accordance with a simple factory pattern, it was decided to separate them in order to incorporate the factory 
method.

However, employing these patterns is a good idea as not all of them were used in prior projects. There are obvious advantages to
using these patterns, especially when adding new objects to the categories. For example, all that would be required to 
add a third zombie in the future is to extend the super-class and define the variables, particularly for this zombie. 
However, the addition of more classes and clutter may appear overkill for such a little application.

The object pool pattern was incorporated in the main class of the model, as the model class was responsible for the data 
but distributing the operations on the data to other classes, such as above-mentioned factories. However, as the model stored the data 
created from the factories, it made sense to assign the duty of object pool distribution within the main model class. 
Another approach could have been to separate the distribution activity into a separate class, to only be responsible for 
retrieving and returning objects. Doing so in this case would result in an unnecessary amount of code, hence, it was 
incorporated into the model.

As mentioned in the procedure, the main issue that persisted throughout the game development was deciding where to put 
certain functionalities. Some examples of these were where to put the moving animation of the zombies, and where to 
detect collisions in order to determine a victory or loss. These are functionalities that alter the data, and for 
instance, the animation of moving the zombies could have been altered in the model, using a runnable as a task to change 
coordinates, and initializing a thread with the runnable as a task.



## Game execution
When the application runs, the user will see a JFrame containing four JPanels. One JPanel has a JCombobox where the user 
selects a game character (Rick, Michonne, Eugene, or Daryl). These are sorted by name using the Streams API. Once the 
user has selected a character, the second JPanel will display an image of the chosen character together with the 
character's weapon. The characters are built using a factory method pattern. The third JPanel will display an animation
of a zombie that is increasing or decreasing. Start Game and Instructions will be the two buttons on the fourth JPanel.
If the user clicks on Instructions, the user will see a JOptionPane containing the instructions for the game, which are:

-The character will collect food items by moving using the arrow keys.
-If the character gets too close to a zombie, the character will die, and it is game over.
-If the character collects all the food before the time is up (30 seconds), the character wins and will have the choice 
to go to the next level. Otherwise, the game is canceled.
-In level two, the character will battle against 10 zombies and will have 10 foods to collect.

If the user clicks on the Start Game button, a new JFrame will be displayed containing three JPanels. There is a 
GamePanel, a ButtonPanel, and a StatsPanel. The GamePanel will display a small image of the chosen character, five 
zombies, and five food items. The zombies are moving simultaneously, the food items are still, and the character will be 
moving around using arrow keys. When a character collides with a food item, the food item will disappear from the 
GamePanel and the score will increase by one point. If the character collides with a zombie, the game is over. The same
concept goes for level two, but level two will have 10 food items and 10 zombies.
The second JPanel will contain the stats for the game, which are time counting down, score, food left, and zombies. 
These will be updated throughout the game.
The third JPanel will contain three buttons: one for exiting the game, and one for ending the game. If the user clicks 
on End Game, the timer will stop and the key listener will be disabled.  If the user clicks on Exit Game, the JFrame will 
be disposed of, but the StartView is still displayed so the user can have the option to select another character and run 
the game again. There is only one way for the player to exit the game, which is by clicking on this button.




# Discussion points:

Observer could be view, but chose controller.

Starting threads in controller.

ThreadSizeManager handles amount of threads and operations on threads, meanwhile the controller start them,
and verify the size amount. The controller observes the pool for changes.


