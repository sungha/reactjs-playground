import React from 'react'
import PropTypes from 'prop-types'
import { Provider } from 'react-redux'
import { BrowserRouter, Route } from 'react-router-dom'

import { Login } from '../layout/Login'
import { Default } from '../layout/Default'

export const Root = ({ store }) => (
  <Provider store={store}>
    <BrowserRouter>
      <Route exact path='/:lang(en|ko|jp|zh)?/login' component={Login} />
      <Route exact path='/:lang(en|ko|jp|zh)?/' component={Default} />
      <Route exact path='/:lang(en|ko|jp|zh)?/profile' component={Default} />
      <Route exact path='/:lang(en|ko|jp|zh)?/theme' component={Default} />
      <Route exact path='/:lang(en|ko|jp|zh)?/test' component={Default} />
      <Route exact path='/:lang(en|ko|jp|zh)?/pilot/pixi' component={Default} />
    </BrowserRouter>
  </Provider>
)

Root.propTypes = {
  // eslint-disable-next-line
  store: PropTypes.object.isRequired
}
