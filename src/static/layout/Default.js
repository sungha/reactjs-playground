import React from 'react'
import {BrowserRouter, Switch, Route} from 'react-router-dom'

import { Header } from './Header'
import { Sidebar } from '../component/Sidebar'

import { Profile } from '../view/Profile'
import { ThemePreview } from './ThemePreview'
import { Test } from './Test'
import { PixiTest } from '../view/pilot/pixi/PixiTest'

import {path} from '../i18n'

const loading = (
  <div className="pt-3 text-center">
    <div className="sk-spinner sk-spinner-pulse" />
  </div>
)

const Welcome = React.lazy(() => import('../view/Welcome'))


export const Default = (match) => {
  console.log(match)

  return (
    <>
      <div id="app">
        <Sidebar />
        <div id="body">
          <Header />
          <div id="content">
            <BrowserRouter>
              <React.Suspense fallback={loading}>
                <Switch>
                  <Route exact path={`${path}/`} name="welcome" component={() => <Welcome />} />
                  {/* <Route exact path={`${path}/profile`} component={Profile} /> */}
                  {/* <Route exact path={`${path}/theme`} component={ThemePreview} /> */}
                  {/* <Route exact path={`${path}/test`} component={Test} /> */}
                  {/* <Route exact path={`${path}/pilot/pixi`} component={PixiTest} /> */}
                </Switch>
              </React.Suspense>
            </BrowserRouter>
          </div>
        </div>
      </div>
    </>
  )
}

Default.propTypes = {
  match: require('react-router-prop-types').match.isRequired
}

