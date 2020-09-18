class GlobalState {
  state = {}

  listeners = []

  constructor(reducer, initialState = {}) {
    this.reducer = reducer
    this.state = initialState
    this.devTools = typeof window !== 'undefined' && window?.__REDUX_DEVTOOLS_EXTENSION__?.connect() // eslint-disable-line no-underscore-dangle
  }

  listen(listener) {
    this.listeners.push(listener)
  }

  unlisten(listener) {
    this.listeners = this.listeners.filter(l => l !== listener)
  }

  dispatch = (actionName, payload) => {
    const nextState = this.reducer(this.state, actionName, payload)

    if (nextState !== this.state) {
      this.state = nextState
      this.listeners.forEach(l => l(nextState))

      if (this.devTools) {
        this.devTools.send(actionName, payload)
      }
    }
  }
}

const darkTheme = {
  name: 'dark',
  background: 'black',
  text: 'white',
}

const lightTheme = {
  name: 'light',
  background: 'white',
  text: 'black',
}

function reducer(state, actionName, payload) {
  switch (actionName) {
    case 'toggleTheme':
      return {
        ...state,
        theme: state.theme.name === 'light' ? darkTheme : lightTheme,
      }
    default:
      throw new Error(`Action does not exist: ${actionName}`)
  }
}

const appState = new GlobalState(reducer, { theme: lightTheme })

export default appState
