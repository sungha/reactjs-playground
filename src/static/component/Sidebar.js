import React from 'react'
import {Link} from 'react-router-dom'
import {useTranslation} from '../i18n'

import {Languages} from '../layout/Languages'
// import { Image } from 'react-bootstrap'
// import { MenuItem } from './sidebar/MenuItem'

export const Sidebar = () => {
  const {t} = useTranslation()

  return (
    <>
      <div id="sidebar">

        <div className="logo">SUNGHA.KR</div>

        <img src="/img/sample/iu.jpg" alt="" className="img-thumbnail rounded-circle" width="100" />
        {t('author.name')}
        Jobless
        <i className="fa fa-facebook" />

        <nav className="menu">
          <ul>
            <li>
              <Link to="/">
                <i className="ri-database-2-fill" />
                <span className="title">메뉴</span>
              </Link>
            </li>
            <li>
              <Link to="/">
                <i className="ri-database-2-fill" />
                <span className="title">메뉴</span>
              </Link>
            </li>
            <li>
              <Link to="/">
                <i className="ri-database-2-fill" />
                <span className="title">메뉴</span>
              </Link>
            </li>
            <li>
              <Link to="/">
                <i className="ri-database-2-fill" />
                <span className="title">{t('menu.simulation')}</span>
              </Link>
            </li>
            <li>
              <Link to="/">
                <i className="ri-database-2-fill" />
                <span className="title">메뉴</span>
              </Link>
            </li>
          </ul>
        </nav>

        <div id="option">
          <a href="/site/summary.html" target="_blank" className="btn btn-sm btn-dark btn-block">
            <i className="fa fa-dashboard" />
            Project Reports
          </a>

          <Languages />
          <Languages />

        </div>
      </div>
    </>
  )
}


// Sidebar.propTypes = {
//   match: require('react-router-prop-types').match.isRequired
// }
