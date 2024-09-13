### Android View State

In my methodoogy I prefre to only expose **one** function from the `ViewModel` and only allowing **one** `Observable` entity.


### Observing Changes

For observing changes I prefer to use a `data Class` that represents the state of the application or a specific view. This class is defined inside the `ViewModel`

```
class MainViewModel : ViewModel() {
    // ....

  sealed class LoadingState {
    data object None : LoadingState()
    data object Loading : LoadingState()
  }

  data class ViewState(
    val counter: Int = 0,
    val loadingState: LoadingState = LoadingState.None
  )
}


```
Then a `StateFlow` or `LiveData` is created to make the state `Observable` by the consumer
```
class MainViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = _viewState
    // ....

   sealed class LoadingState {
      data object None : LoadingState()
      data object Loading : LoadingState()
    }

    data class ViewState(
      val counter: Int = 0,
      val loadingState: LoadingState = LoadingState.None
    )
}

```

The consumer collects the `ViewState` from the `ViewModel` and observes changes in the state.

```
val viewState by mainViewModel.viewState.collectAsState()

when (viewState.loadingState) {
  MainViewModel.LoadingState.Loading -> {
    // Show loading
  }
  MainViewModel.LoadingState.None -> {
    // Hide loading
  }
}
```

And updates to the `ViewState` are done by copying the value and updating the properties of interest
```
_viewState.value = _viewState.value.copy(loadingState = LoadingState.Loading)
```

### Performing Actions

To interact with the `ViewModel` I create a `sealed Class` inside the `ViewModel` for Actions that can be performed.

```
class MainViewModel : ViewModel() {
    // ....

  sealed class Action {
    data object ActionOne : Action()
    data object ActionTwo : Action()
  }
}
```

Then a public method is created the takes in `Action` as argument, and allows the user to interact with the `ViewModel`

```
class MainViewModel : ViewModel() {
    // ....
  fun performAction(action: Action) {
    when (action) {
      // Handle Specifc Actions ...
    }
  }

  // ...

  sealed class Action {
    data object ActionOne : Action()
    data object ActionTwo : Action()
  }
}
```
