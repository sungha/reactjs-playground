import React from 'react'
import { useHistory } from 'react-router-dom'
import i18n, {pattern} from '../i18n'
import { Select2 } from '../component/Select2'

export const Languages = () => {
  const history = useHistory()

  const onChange = (value) => {
    i18n.changeLanguage(value)

    const path = window.location.pathname.replace(pattern, '')
    const search = window.location.search ? `?${window.location.search}` : ''

    history.push(`/${i18n.language}/${path}${search}`)
  }

  return (
    <>
      <Select2 id="language-selector" defaultValue={i18n.language} onChange={e => onChange(e.target.value)}>
        {i18n.available.map((lang) => (
          <option key={lang.code} value={lang.code}>{lang.name}</option>
        ))}
      </Select2>
    </>
  )
}

