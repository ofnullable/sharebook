const dev = process.env.NODE_ENV !== 'production';

const path = require('path');
const Next = require('next');
const fastify = require('fastify')({
  logger: { level: dev ? 'info' : 'error' },
});

fastify
  .register(require('fastify-static'), {
    root: path.join(__dirname, 'static'),
    prefix: '/static',
  })
  .register((fastify, opts, next) => {
    const app = Next({ dev });

    app.prepare().then(() => {
      fastify.get('*', (req, reply) => {
        return app.handleRequest(req.req, reply.res).then(() => {
          reply.sent = true;
        });
      });
      fastify.setNotFoundHandler((request, reply) => {
        return app.render404(request.req, reply.res).then(() => {
          reply.sent = true;
        });
      });

      next();
    });
  });

const port = Number(process.env.PORT) || 3010;
const host = dev ? 'localhost' : '0.0.0.0';

fastify.listen(port, host, err => {
  if (err) throw err;
  console.log(`Server listening on ${host}:${port}`);
});
// });
