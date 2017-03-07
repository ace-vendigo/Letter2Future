const webpack = require('webpack');
const webpackMerge = require('webpack-merge');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const commonConfig = require('./webpack.common.js');
const helpers = require('./helpers');

module.exports = webpackMerge(commonConfig, {
    devtool: 'source-map',

    output: {
        path: './../resources/static/src',
        publicPath: '/resources/src',
        filename: '[name].js',
        chunkFilename: '[id].chunk.js'
    },
    
    debug: true,

    htmlLoader: {
        minimize: false
    },

    plugins: [
        new webpack.NoErrorsPlugin(),
        new ExtractTextPlugin('[name].css')
    ]
});