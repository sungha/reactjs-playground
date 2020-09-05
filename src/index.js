import 'react-app-polyfill/ie11'
import 'react-app-polyfill/stable'

import React from 'react'
import { createStore } from 'redux'
import ReactDOM from 'react-dom'
import * as serviceWorker from './static/serviceWorker'

import 'jquery'
import 'bootstrap'
import 'popper.js/dist/esm/popper'
import 'select2'

import 'holderjs'

import './static/theme/sungha/theme.scss'
import './static/index.scss'

import { Root } from './static/view/Root'

const store = createStore(() => {
  return {
    number: 0
  }
})

ReactDOM.render(
  <Root store={store} />,
  document.getElementById('root'),
  () => {
    // DO NOTHING. (YET)
  }
)

serviceWorker.unregister()
