import React, { useState, useRef, useEffect } from 'react'
import PropTypes from 'prop-types'
import $ from 'jquery'
import 'select2'

export const Select2 = ({id, children, defaultValue, onChange}) => {
  const [value, setValue] = useState()
  const ref = useRef()

  useEffect(() => {
    console.log('effect')

    $(() => {
      $(ref.current).select2().on('change', (e) => {
        onChange(e)
      })
    })
  }, [])

  return (
    <>
      <select id={id} className="select2" ref={ref} defaultValue={defaultValue} onChange={onChange}>
        {children}
      </select>
    </>
  )
}

Select2.propTypes = {
  id: PropTypes.string,
  children: PropTypes.arrayOf(PropTypes.element),
  defaultValue: PropTypes.string,
  onChange: PropTypes.func
}
Select2.defaultProps = {
  id: null,
  children: null,
  defaultValue: null,
  onChange: null
}

