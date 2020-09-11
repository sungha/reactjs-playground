import React, { lazy } from 'react'
import { BrowserRouter, Route } from 'react-router-dom'

import { path } from './i18n'

// import { Test } from './view/Test'
const Test = lazy(() => import('./view/Test'))
const Login = lazy(() => import('./layout/Login'))
const Default = React.lazy(() => import('./layout/Default'))



const Loading = (
  <div className="pt-3 text-center">
    <div className="sk-spinner sk-spinner-pulse">Loading</div>
  </div>
)


export const Root = () => {
  // const path = ''

  return (
    <BrowserRouter>
      <React.Suspense fallback={Loading}>
        <Route exact path={`${path}/`} render={() => <Default />} />
        <Route exact path={`${path}/login`} render={() => <Login />} />
        {/* <Route exact path={`${path}/`} component={Default} /> */}
        {/* <Route exact path={`${path}/profile`} component={Default} /> */}
        {/* <Route exact path={`${path}/theme`} component={Default} /> */}
        {/* <Route exact path={`${path}/test`} component={Default} /> */}
        {/* <Route exact path={`${path}/pilot/pixi`} component={Default} /> */}
      </React.Suspense>
    </BrowserRouter>
  )
}

