# Left to do
- Comment
Material: readme
Purpose: readme
Procedures: readme
Discussion: readme
Refactor zombie animation? 
Refactor object pool?
Check why some zombies are still
Verify that timer ends when winning level 2


# Material

# Purpose
The purpose of this final project is to create a GUI application applying following concepts/functionality:
- Observer pattern, Producer/Consumer pattern, Object Pool pattern, Template Method pattern, Factory Method pattern,
  and Abstract Factory pattern.
- Five concurrent processes.
- Synchronization of three concurrent processes.
- Six different Swing components.
- Four different Layout managers.
- Three uses of Streams API.

These six functionalities should be designed according to Model-View-Controller (MVC) architecture, separating
the model and the view, having the controller managing the communication. The goal is to create a game inspired
by The Walking Dead TV-series, having one selected character collecting food meanwhile being threatened being 
hit by a zombie. The aim is to have functionalities to select a character, collect points by collecting food items,
meanwhile having a timer counting down to end game. A character should die if hit, or go to next level if it's a win. 

# Procedure
Issues:
Zombie Animation - where to put?



# Discussion points:

Some things are unnecessary for example stream api, only to squeeze it in.

ZombieAnimation in controller, because it works with JLabels, and model should not know about view,
and view should not have this much logic, so i decided to put it in controller folder.

Observer/observable could have been used for zombie animation, and zombies could have been implemented
as Runnables. However, it's more readable to have a separate class for the animation.

Observer could be view, but chose controller.

Starting timer in controller, as timer per se is not business logic, thus not suitable in model. 

Starting threads in controller. 

Model acts as a manager of object pool for zombies/food, that will be re-used for next level. 

ThreadSizeManager handles amount of threads and operations on threads, meanwhile the controller start them,
and verify the size amount. The controller observes the pool for changes. 