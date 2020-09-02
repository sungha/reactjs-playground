
export const hateoas = (data) => {
  /* eslint no-underscore-dangle: ["error", { "allow": ["_embedded"] }] */
  const { users } = data._embedded
  const list = users.splice(10)
  return list
}
