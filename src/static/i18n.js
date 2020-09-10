import i18next from 'i18next'
import {initReactI18next, useTranslation} from 'react-i18next'
import Cookies from 'js-cookie'

const cookieKey = 'language'
const fallbackLanguage = 'en'
const defaultLanguage = Cookies.get(cookieKey) || fallbackLanguage // window.navigator.language.substr(0, 2)

const resources = {
  en: require('./i18n/english.json'),
  ko: require('./i18n/korean.json')
}

const languages = Object.keys(resources)
const pattern = new RegExp(`^\\/?(${languages.join('|')}|)\\/`)
const path = `/:lang(${languages.join('|')})?`

// console.log(window.location.pathname)
const match = pattern.exec(`${window.location.pathname}/`)
const lang = match && match[1] ? match[1] : defaultLanguage

i18next
.use(initReactI18next)
.init({
  resources, fallbackLng: 'en', lng: lang, debug: false, interpolation: {
    escapeValue: false
  }
}).then()

i18next.on('languageChanged', (lng) => {
  console.log(`language changed to [${lng}]`)
  Cookies.set(cookieKey, lng)
})

i18next.available = Object.entries(resources).map(([k, v]) => ({
  code: k, name: v.name
}))

i18next.selected = {
  code: i18next.language, name: resources[i18next.language].name
}

i18next.path = path

export default i18next
export {
  languages, pattern, path, useTranslation
}


