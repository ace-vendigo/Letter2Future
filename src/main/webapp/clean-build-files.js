var del = require("del");

del([
    "./*.*.js", "./*.*.js.map",
    "./*.*.css", "./*.*.css.map",
    "index.html"
]).then(function () {
    console.log("Deleting files");
}, function (error) {
    console.log(error);
});