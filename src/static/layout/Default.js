import React from 'react'
import {BrowserRouter, Switch, Route} from 'react-router-dom'

import { Header } from './Header'
import { Sidebar } from '../component/Sidebar'

import { Index } from '../view/Index'
import { Profile } from '../view/Profile'
import { ThemePreview } from './ThemePreview'
import { Test } from './Test'
import { PixiTest } from '../view/pilot/pixi/PixiTest'

import i18n, {path} from '../i18n'


export const Default = ({match}) => {
  return (
    <>
      <div id="app">
        <Sidebar />
        <div id="body">
          <Header />
          <div id="content">
            <BrowserRouter>
              <Switch>
                <Route exact path={`${path}/`} component={Index} />
                <Route exact path={`${path}/profile`} component={Profile} />
                <Route exact path={`${path}/theme`} component={ThemePreview} />
                <Route exact path={`${path}/test`} component={Test} />
                <Route exact path={`${path}/pilot/pixi`} component={PixiTest} />
              </Switch>
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
