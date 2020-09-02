import React from 'react'
// import { Link } from 'react-router-dom'
import {useTranslation} from 'react-i18next'
import { MenuItem } from './MenuItem'
// import i18n from '../i18n'

export const Menu = () => {
  const { t } = useTranslation()
  return (
    <ul>
      <MenuItem title={t('menu.profile')} />
      <MenuItem title={t('menu.profile')} />
      <MenuItem title={t('menu.profile')} />
      <MenuItem title={t('menu.profile')} />
      <MenuItem title={t('menu.profile')} />
      <MenuItem title={t('menu.profile')} />
      <MenuItem title={t('menu.profile')} />
      <MenuItem title={t('menu.profile')} />
      <MenuItem title={t('menu.profile')} />
      <MenuItem title={t('menu.profile')} />
    </ul>
  )
}
