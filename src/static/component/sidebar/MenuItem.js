import React from 'react'
// import { Link } from 'react-router-dom'
// import {useTranslation} from 'react-i18next'
import PropTypes from 'prop-types'
// import i18n from '../i18n'

export const MenuItem = ({ icon, title }) => {
  return (
    <>
      <li>
        <i className={`fa fa-${icon}`} />
        {title}
      </li>
    </>
  )
}

MenuItem.propTypes = {
  icon: PropTypes.string,
  title: PropTypes.string.isRequired
}
MenuItem.defaultProps = {
  icon: 'search'
}

