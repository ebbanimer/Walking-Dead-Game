
# Discussion points:

Observer/observable could have been used for zombie animation, and zombies could have been implemented
as Runnables. However, it's more readable to have a separate class for the animation.

Starting timer in controller, as timer per se is not business logic, thus not suitable in model. 

Starting threads in controller. 

Model acts as a manager of object pool for zombies/food, that will be re-used for next level. 

ThreadSizeManager handles amount of threads and operations on threads, meanwhile the controller start them,
and verify the size amount. The controller observes the pool for changes. 