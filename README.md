# emvc
Extended MVC Base Classes

The well known software development pattern Model View Controller or short MVC is used a basis
an extended with a few approved mechanism to help the developer implement business applications.

### The basic ideas are:
- DRY & SRP
- Controllers are state-machines.
- Processes are split into GUI-related and headless processes.
- Headless processes have to be stateless and they just do the processing.
- GUI processes should be stateless and delegate the processing from the Controller to the right headless process.
- Events from the Models of GUI-components are handled by the Controller and lead to a change of state.
- A change of state is atomic and must not fire new events which had to be handled.
- The user must be informed when processing (a change of state) is started and when it is finished.
