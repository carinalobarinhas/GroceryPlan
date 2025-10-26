package br.com.carina.grocery_plan.domain.scaffold

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModelScaffold is a unidirectional data flow scaffold that helps to manage the state and side effects
 * of a composable.
 */
interface ViewModelScaffold<ViewState, ViewEvent, SideEffect> {
    val viewState: StateFlow<ViewState>
    val sideEffects: Flow<SideEffect>

    fun handleEvent(event: ViewEvent)

    fun updateViewState(block: (ViewState) -> ViewState)

    fun CoroutineScope.emitSideEffect(sideEffect: SideEffect)
}

/**
 * ViewModelScaffoldDelegate is a default implementation of CollectibleScaffold. IMPORTANT: Because this is a delegate, its overriden
 * properties and methods are public. However, `updateViewState` and `emitSideEffect` are intended to be private and
 * should not be used outside of the ViewModel.
 */
class ViewModelScaffoldDelegate<ViewState, ViewEvent, SideEffect>(
    initialState: ViewState
) : ViewModelScaffold<ViewState, ViewEvent, SideEffect> {

    private val _viewState = MutableStateFlow(initialState)

    /**
     * The view state to be used by the consumer of this delegate. It is a [StateFlow], which is the appropriate
     * flow type for UI state in Compose.
     */
    override val viewState: StateFlow<ViewState>
        get() = _viewState.asStateFlow()

    private val _sideEffects by lazy { Channel<SideEffect>(Channel.BUFFERED) }

    /**
     * The side effects flow to be used by the consumer of this delegate. It is backed by a [Channel], which ensures
     * that it has a single consumer, and it's buffered to ensure the consumer doesn't miss events. If it becomes
     * necessary to have multiple consumers, override this property to use a SharedFlow. This should only be necessary
     * if though testing you find that some consumers are not receiving emitted side effects.
     */
    override val sideEffects: Flow<SideEffect> by lazy { _sideEffects.receiveAsFlow() }

    /**
     * The UI can send events to be handled by calling this. Override this method to apply event handling.
     */
    override fun handleEvent(event: ViewEvent) {}

    /**
     * Updates the view state. Treat this as if it is a private method. DO NOT USE OUTSIDE THE VIEWMODEL.
     */
    override fun updateViewState(block: (ViewState) -> ViewState) {
        _viewState.update(block)
    }

    /**
     * Emits a view effect. DO NOT USE OUTSIDE THE VIEWMODEL.
     */
    override fun CoroutineScope.emitSideEffect(sideEffect: SideEffect) {
        this.launch { _sideEffects.send(sideEffect) }
    }
}

/**
 * Creates a ViewModelScaffold with the given initial state. Apply to your ViewModel using the `by` keyword.
 * ```
 * class MyViewModel
 *    : ViewModel(), ViewModelScaffold<MyViewState, MyEvent, MyEffect> by viewModelScaffold(initialState = MyViewState()) {
 * ```
 */
fun <ViewState, ViewEvent, SideEffect> viewModelScaffold(
    initialState: ViewState,
): ViewModelScaffold<ViewState, ViewEvent, SideEffect> {
    return ViewModelScaffoldDelegate(initialState)
}
