import React, { useEffect, useState } from 'react'
import axios from 'axios'
// import * as app from '../lib/app'

export default () => {
  const [posts, setPosts] = useState([])
  const [users, setUsers] = useState([])

  useEffect(() => {
    axios
    .get('https://jsonplaceholder.typicode.com/posts')
    .then(({data}) => {
      data.splice(10)
      return data
    })
    .then((data) => setPosts(data))

    axios
    .get('/rest/users')
    .then(({data}) => setUsers(data.content || []))
  }, [])

  return (
    <>
      {posts.map((post) => (
        <div key={post.id}>
          {post.title}
        </div>
      ))}

      {users.map((user) => (
        <div key={user.id}>
          {user.realname}
        </div>
      ))}

      {/* <LoremIpsum p={10} /> */}
    </>
  )
}

