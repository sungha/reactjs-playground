import React, { useState } from 'react'
import { Container, Sprite, Stage, useTick } from '@inlet/react-pixi'

import * as PIXI from 'pixi.js'

PIXI.utils.skipHello()

const Bunny = () => {
  const [x, setX] = useState(0)
  const [y, setY] = useState(0)
  const [rotation, setRotation] = useState(50)

  let i = 0

  useTick((delta) => {
    i += 1

    const x = Math.sin(i) * 100
    // console.log(i)

    setX(x)

    // setX(Math.sin(i) * 100)
    // setY(Math.sin(i / 1.5) * 100)
    // setRotation(-10 + Math.sin(i / 10 + Math.PI * 2) * 10)
  })

  return (
    <Sprite
      image="/img/examples/bunny.png"
      anchor={0.5}
      x={x}
      y={y}
      rotation={rotation}
    />
  )
}


export const PixiTest = () => {

  return (
    <Stage width={300} height={300} options={{ transparent: true }}>
      <Container x={150} y={150}>
        <Bunny />
      </Container>
    </Stage>
  )
}
