import React from 'react'
import { NavLink } from 'react-router-dom'
import i18n, {useTranslation} from '../i18n'

export const Header = () => {
  const prefix = i18n.language
  const { t } = useTranslation()

  return (
    <>
      <form id="logout-form" method="post" action="/logout" className="hidden" />
      <div id="header">
        <nav className="navbar navbar-expand">
          <NavLink className="navbar-brand" to="/">{t('menu.home')}</NavLink>

          <ul id="mainmenu" className="navbar-nav">
            <li className="nav-item">
              <NavLink className="nav-link" to={`/${prefix}/profile`}>{t('menu.profile')}</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to={`/${prefix}/blog`}>{t('menu.blog')}</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to={`/${prefix}/theme`}>{t('menu.theme')}</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to={`/${prefix}/login`}>{t('menu.login')}</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to={`/${prefix}/test`}>{t('menu.test')}</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/pilot/pixi">PixiJS Test</NavLink>
            </li>
          </ul>
        </nav>
      </div>
    </>
  )
}
