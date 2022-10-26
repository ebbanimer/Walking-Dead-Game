Left to do
- Comment
- POM file (JAR)
- Material: readme
- Purpose: readme
Procedures: readme
Discussion: readme
Solution no higher than 15 jdk?
Section how the game should be executed (if it doesn't work in teachers environment)

# Material
Microsoft Windows 10 Home 64-bit, IntelliJ IDEA 2021.3.3, Command Prompt,
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

These six functionalities should be designed according to Model-View-Controller (MVC) architecture, separating
the model and the view, and having the controller govern the communication. The objective is to assemble a game inspired
by The Walking Dead TV series, having one selected character collecting food meanwhile being threatened by being
hit by a zombie. The intent is to have processes to select a character, and collect points by accumulating food items,
meanwhile having a timer counting down to the end of the game. A character should die if it gets hit by a zombie, or
have the option to advance to the next level if the character accomplishes winning the game. The ambition is to build
this application, based on given design patterns and functionalities such as concurrent processes, as mentioned above.


# Procedure
Issues:
1. Zombie Animation - where to put?
2. JAR-file did not work from root

How were they solved?
1. Placed in controller. Does not belong in model, as it communicates with View. Does not belong in view,
   as it has too much logic.
2. java -jar "target\ebni2100_project_ht22-1.0-SNAPSHOT.jar" from root


# Goal achivements
MVC architecture: controller handling communication between data in model and GUI in view
application running on EDT
6 patterns: observer pattern, con/prod (zombie size in startview), factory method (create characters), abstract factory
(create items), template (levels), object pool (model distributes food/zombies when gamecontroller requests)
6 swing components: jlabel, jcombobox, btn, image, imageicon, panel, joptionpane
4 layout managers: gridbagconstraints, borderlayout, springlayout, flowlayout
3 streams: sortCharacterNames (sorted), createCharacter (filter), findFirst
5 concurrent processes: 10 zombies, 7 producers, 1 consumer
3 synchronized: add consumer, remove producer, add producer, remove consumer, remove size, add size

Analyze the procedure. Alternative ways?


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
