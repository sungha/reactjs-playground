import React, { useState } from 'react'
import { Container, Sprite, Stage, useTick } from '@inlet/react-pixi'

import * as PIXI from 'pixi.js'

PIXI.utils.skipHello()

export const PixiTest = () => {
  const Bunny = () => {
    const [x, setX] = useState(0)
    const [y, setY] = useState(0)
    const [rotation, setRotation] = useState(0)

    let i = 0

    useTick(delta => {
      i += 0.05 * delta

      setX(Math.sin(i) * 100)
      setY(Math.sin(i / 1.5) * 100)
      setRotation(-10 + Math.sin(i / 10 + Math.PI * 2) * 10)
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

  return (
    <Stage width={300} height={300} options={{ transparent: true }}>
      <Container x={150} y={150}>
        <Bunny />
      </Container>
    </Stage>
  )
}
