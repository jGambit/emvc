# emvc
Extended MVC base interfaces

The well known software development pattern Model View Controller or short MVC is used as a basis.
This pattern is extended with a few approved mechanisms to help the developer implement business applications
with graphical user interfaces or short GUI in Java.
With that help the developer can focus on the specific task to implement and not on event-handling questions.

### The basic ideas are:
- Seperate controller-logic from business logic further than standard MVC.
- Controllers are state-machines that call methods on a GUI-related process when a state changes.
- Business logic is handled by a process not a controller
- Processes are split into GUI-related and headless processes.
- Headless processes have to be stateless and they just do the processing.
- GUI processes should be stateless and delegate the processing from the Controller to the right headless process.
- Events from the Models of GUI-components are handled by the Controller and lead to a change of state.
- A change of state is atomic and must not fire new events which had to be handled.
- The user must be informed when processing (a change of state) is started and when it is finished.
