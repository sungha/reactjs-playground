import React from 'react'
import PropTypes from 'prop-types'
import { Provider } from 'react-redux'
import { BrowserRouter, Route } from 'react-router-dom'

import { Login } from '../layout/Login'
import { Default } from '../layout/Default'

import {path} from '../i18n'

export const Root = ({ store }) => {
  console.log(path)

  return (
    <Provider store={store}>
      <BrowserRouter>
        <Route exact path={`${path}/login`} component={Login} />
        <Route exact path={`${path}/`} component={Default} />
        <Route exact path={`${path}/profile`} component={Default} />
        <Route exact path={`${path}/theme`} component={Default} />
        <Route exact path={`${path}/test`} component={Default} />
        <Route exact path={`${path}/pilot/pixi`} component={Default} />
      </BrowserRouter>
    </Provider>
  )
}

Root.propTypes = {
  // eslint-disable-next-line
  store: PropTypes.object.isRequired
}
