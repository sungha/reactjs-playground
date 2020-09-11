import 'react-app-polyfill/ie11'
import 'react-app-polyfill/stable'

import React from 'react'
import { Provider } from 'react-redux'
import ReactDOM from 'react-dom'
import * as serviceWorker from './static/serviceWorker'

import 'jquery'
import 'bootstrap'
import 'popper.js/dist/esm/popper'
import 'select2'

import 'holderjs'

import './static/theme/sungha/theme.scss'
import './static/index.scss'

import store from './static/store'
import { Root } from './static/Root'

window.frontend = window.frontend || {
  mode: 'dark'
}
const ua = window.navigator.userAgent
const msie = ua.indexOf('MSIE ')

if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv:11\./)) {
  console.log('\nSUNGHA.KR v2020.08 (https://sungha.kr/)\n')
} else {
  console.log(...[`\n%c %c  SUNGHA.KR v2020.08, React: ${React.version}.  %c %c  https://sungha.kr/  DARK MODE: ${window.frontend.mode === 'dark'}\n`, 'background: #eee; padding:3px 0;', 'color: #030307; background: #b3ff00; padding:3px 0;', 'background: #eee; padding:3px 0;', 'background: transparent; padding:3px 0;'])
  window.matchMedia('(prefers-color-scheme: dark)')
  .addEventListener('change', (e) => {
    window.frontend.mode = e.matches ? 'dark' : 'light'
  })
}

ReactDOM.render(
  <Provider store={store}>
    <Root />
  </Provider>,
  document.getElementById('root'),
  () => {
    // DO NOTHING. (YET)
  }
)

serviceWorker.unregister()
