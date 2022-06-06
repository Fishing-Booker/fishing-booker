const express = require('express');
const app = express();
const path = require('path');
const serverStatic = require('serve-static');
const port = process.env.PORT || 3000; 

app.use('/', serverStatic(path.join(__dirname, 'build')));
app.get('/*', function (req, res) { 
    res.sendFile(path.join(__dirname, 'build', 'index.html')); 
  }); 


app.listen(process.env.PORT || 3000, function() { 
        console.log("Express server listening on port %d in %s mode", port, app.settings.env);
    });