var del = require("del");

del([
    "./app.*", "./vendor.*",
    "./polyfills.*",
    "index.html"
]).then(function () {
    console.log("Deleting files");
}, function (error) {
    console.log(error);
});