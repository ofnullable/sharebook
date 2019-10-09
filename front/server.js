const dev = process.env.NODE_ENV !== 'production';

const path = require('path');
const app = require('next')({ dev });
const server = require('fastify');

app.prepare().then(() => {
  const fastify = server({
    logger: { level: dev ? 'info' : 'error' },
  });

  fastify.register(require('fastify-static'), {
    root: path.join(__dirname, 'static'),
    prefix: '/static',
  });

  fastify.get('/book/:id', async (req, reply) => {
    const id = req.params.id;
    return app.render(req.req, reply.res, '/book', { id }).then(() => {
      reply.sent = true;
    });
  });

  fastify.get('/settings/books/register', async (req, reply) => {
    return app.render(req.req, reply.res, '/book/register').then(() => {
      reply.sent = true;
    });
  });

  fastify.get('/settings/profile', async (req, reply) => {
    return app.render(req.req, reply.res, '/settings', { menu: 'profile' }).then(() => {
      reply.sent = true;
    });
  });

  fastify.get('/settings/books/:status', async (req, reply) => {
    const status = req.params.status;
    return app.render(req.req, reply.res, '/management/books', { status }).then(() => {
      reply.sent = true;
    });
  });

  fastify.get('/settings/rentals/:status', async (req, reply) => {
    const status = req.params.status || 'requested';
    return app.render(req.req, reply.res, '/management/rentals', { status }).then(() => {
      reply.sent = true;
    });
  });

  fastify.get('*', async (req, reply) => {
    return app.handleRequest(req.req, reply.res).then(() => {
      reply.sent = true;
    });
  });
  fastify.setNotFoundHandler((request, reply) => {
    return app.render404(request.req, reply.res).then(() => {
      reply.sent = true;
    });
  });

  const port = Number(process.env.PORT) || 3010;
  const host = '0.0.0.0';

  fastify.listen(port, host, err => {
    if (err) throw err;
    console.log(`Server listening on ${host}:${port}`);
  });
});
