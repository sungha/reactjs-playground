import 'react-app-polyfill/ie11'
import 'react-app-polyfill/stable'

import React from 'react'
import { Provider } from 'react-redux'
import ReactDOM from 'react-dom'
// import * as serviceWorker from './static/serviceWorker'

import 'jquery'
import 'bootstrap'
import 'popper.js/dist/esm/popper'
import 'select2'
import 'holderjs'

import './static/theme/sungha/theme.scss'
import './static/index.scss'

import store from './static/store'
import { Root } from './static/Root'

(() => {
  const root = document.getElementById('root')

  const ua = window.navigator.userAgent
  const msie = ua.indexOf('MSIE ')

  const banner = `SUNGHA.KR v2020.08 [React: v${React.version}]`

  if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv:11\./)) {
    console.log(`\n${banner}\n`)
  } else {
    console.log(...[`\n%c %c  ${banner}  %c %c  `, 'background: #eee; padding:3px 0;', 'color: #030307; background: #b3ff00; padding:3px 0;', 'background: #eee; padding:3px 0;', 'background: transparent; padding:3px 0;'])
    window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', (e) => {
      root.classList.add(e.matches ? 'dark' : 'light')
    })
  }

  ReactDOM.render(
    <Provider store={store}>
      <Root />
    </Provider>,
    root,
    () => {
      // DO NOTHING. (...YET)
    }
  )

  // serviceWorker.unregister()
})()
