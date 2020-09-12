import {useEffect, useRef, useState} from 'react'

const useGlobalState = (globalState, stateGetter) => {
  const [state, setState] = useState(stateGetter(globalState.state))

  // We don't want to re-create the listener as we want to unlisten on unmount
  // of the component which uses this hook, so we "tunnel" the state in.
  const stateRef = useRef(state)
  stateRef.current = state

  const listener = useRef(nextState => {
    const stateUpdate = stateGetter(nextState)
    if (stateRef.current !== stateUpdate) {
      setState(stateUpdate)
    }
  })

  useEffect(() => {
    globalState.listen(listener.current)
    return () => globalState.unlisten(listener.current)
  }, [globalState])

  return [state, globalState.dispatch]
}

export default useGlobalState
