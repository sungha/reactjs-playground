import React from 'react'
import { Link } from 'react-router-dom'
import { Button, FormControl, InputGroup } from 'react-bootstrap'

export const NotFound = () => (
  <>
    <div id="body">
      <div id="content">
        404 Not Found
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iure voluptas aperiam odit, reiciendis dicta nihil.</p>

        <form>
          Search
          <div className="input-group">
            <input type="text" className="form-control" placeholder="Enter search phrase here..." />
            <Button variant="secondary"><i className="fa fa-search" /></Button>
          </div>

          <InputGroup className="mb-3">
            <FormControl placeholder="Recipient's username" />
            <InputGroup.Append>
              <Button variant="secondary"><i className="fa fa-search" /></Button>
            </InputGroup.Append>
          </InputGroup>
        </form>


        <Link to="/">Back to Home</Link>

        <div>
          &copy; 2020 All Rights Reserved.
          <br />
          Built with Spring Boot 2.3.2, Bootstrap 4, React
        </div>

      </div>
    </div>
  </>
)
