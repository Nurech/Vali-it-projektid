// vue.config.js
module.exports = {
    devServer: {
        proxy: {
            '/api/': {
                target: 'http://localhost:8082', // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
                ws: true,
                changeOrigin: true
            },
            '/public/': {
                target: 'http://localhost:8082', // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
                ws: true,
                changeOrigin: true
            }
        }
    },
    // Change build paths to make them Maven compatible
    // see https://cli.vuejs.org/config/
    outputDir: 'target/dist',
    assetsDir: 'static'
};
