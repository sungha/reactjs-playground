import React from 'react'
import { Link } from 'react-router-dom'
import { Button, Container, Form } from 'react-bootstrap'

export const Login = () => {

  return (
    <>
      <Container>
        Sign In to Application

        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iure voluptas aperiam odit, reiciendis dicta nihil.</p>

        <Form>
          <Form.Group controlId="formBasicEmail">
            <Form.Label>Email address</Form.Label>
            <Form.Control type="email" placeholder="Enter email" />
            <Form.Text className="text-muted">
              We&apos;ll never share your email with anyone else.
            </Form.Text>
          </Form.Group>

          <Form.Group controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" />
          </Form.Group>
          <Form.Group controlId="formBasicCheckbox">
            <Form.Check type="checkbox" label="Check me out" />
          </Form.Group>
          <Button type="submit" variant="primary" block>
            Submit
          </Button>
        </Form>

        <Link to="/">Back to Home</Link>

        <div>
          &copy; 2020 All Rights Reserved.
          <br />
          Built with Spring Boot 2.3.2, Bootstrap 4, React
        </div>

      </Container>

    </>
  )
}
