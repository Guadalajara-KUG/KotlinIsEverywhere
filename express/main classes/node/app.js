(function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var Unit = Kotlin.kotlin.Unit;
  function main$lambda(req, res) {
    res.type('text/plain');
    return res.send('Hello Express Server!');
  }
  function main$lambda_0() {
    println('Server running :: port 9000');
    return Unit;
  }
  function main() {
    println('Hello Node');
    var express = require('express');
    var app = express();
    app.get('/hell', main$lambda);
    app.listen(9000, main$lambda_0);
  }
  var package$net = _.net || (_.net = {});
  var package$sierisimo = package$net.sierisimo || (package$net.sierisimo = {});
  var package$express = package$sierisimo.express || (package$sierisimo.express = {});
  package$express.main = main;
  main();
  Kotlin.defineModule('app', _);
  return _;
}(module.exports, require('kotlin')));

//# sourceMappingURL=app.js.map
