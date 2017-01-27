const express = require('express'),
    httpProxy = require('http-proxy');

let app = express();

let proxy = httpProxy.createProxyServer({
    target: {
        host: 'localhost',
        port: 8080
    },
});

function apiProxy(host, port) {
    return function(req, res, next) {
        if(req.url.match(new RegExp('^\/api\/'))) {
            proxy.proxyRequest(req, res, {host: host, port: port});
        } else {
            next();
        }
    }
}

app.use(express.static('../resources/static'));
app.use(apiProxy('localhost', 8080));

app.listen(3000, function () {
    console.log('Example app listening on port 3000!')
});