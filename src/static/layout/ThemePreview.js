import React from 'react'
import { Link } from 'react-router-dom'
import { Container, Button, FormControl, InputGroup } from 'react-bootstrap'

export const ThemePreview = () => (
  <>
    <Container>
      Theme Preview

      <form>
        Search
        <div className="input-group">
          <input type="text" placeholder="Enter search phrase here..." />
          <Button variant="secondary"><i className="fa fa-search" /></Button>
        </div>

        <InputGroup className="mb-3">
          <FormControl
            placeholder="Recipient's username"
            aria-label="Recipient's username"
            aria-describedby="basic-addon2"
          />
          <InputGroup.Append>
            <Button variant="secondary"><i className="fa fa-search" /></Button>
          </InputGroup.Append>
        </InputGroup>

        <Link to="/">Back to Home</Link>

      </form>
    </Container>
  </>
)
