const { createProxyMiddleware } = require('http-proxy-middleware')

module.exports = (app) => {
  let cookie

  const printHeaders = (headers) => {
    Object.keys(headers).forEach(key => {
      console.debug(`${key} : ${headers[key]}`)
    })
  }

  app.use(createProxyMiddleware([
    '/management',
    '/rest',
    '/oauth2',
    '/api'
  ], {
    target: 'http://localhost:8080',
    secure: false,
    // changeOrigin: true,
    ws: true,
    // logLevel: 'debug',
    onProxyReq: (proxyReq, req) => {
      console.debug('=========>> request')
      printHeaders(req.headers)
      if (cookie) {
        proxyReq.setHeader('cookie', cookie)
      }
    }, onProxyRes: (proxyRes) => {
      console.debug('<<========= response')
      printHeaders(proxyRes.headers)

      const proxyCookie = proxyRes.headers['set-cookie']
      if (proxyCookie) {
        cookie = proxyCookie
      }
    }
  }))
}
