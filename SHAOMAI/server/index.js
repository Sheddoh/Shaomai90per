var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql');
const e = require('express');


//app.use(bodyParser.json()); This line is error
app.use(bodyParser.urlencoded({ extended: true}));



var conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'shaomai'
});


app.get('/', function(req, res){
    return res.send({error: true, message: 'Test API'})
});

conn.connect();

//SHOW PRODUCT
app.get('/allproduct', function(req, res){
    conn.query('SELECT * FROM product ORDER BY product_price_rent', function(error, results, fields){
        if(error) throw error;
        return res.send(results);
    });
});







//SHOW CUSTOMER
app.get('/allcus', function(req, res){
    conn.query('SELECT * FROM customer', function(error, results, fields){
        if(error) throw error;
        return res.send(results);
    });
});



//REGISTER ADMIN
app.post('/register', function(req, res){
    var register = req.body
    if(!register){
        return res.status(400).send({error: true, message: 'Please Add ADmin'});
    }

    conn.query("INSERT INTO admin SET ?", register, function(error, results, fields){
        if(error) throw error;
        return res.send(results);
    });
});

//REGISTER ADD TO CART
app.post('/addtocart', function(req, res){
    var register = req.body
    if(!register){
        return res.status(400).send({error: true, message: 'Please Add customer'});
    }

    conn.query("INSERT INTO customer SET ? ", register, function(error, results, fields){
        if(error) throw error;
        return res.send(results);
    });
});


//LOGIN ADMIN
app.post('/login', function(req, res){
    var email = req.body.admin_mail;
    var password = req.body.admin_pass;
    if(email && password){
        conn.query('SELECT * FROM admin WHERE admin_mail = ? AND admin_pass = ?', [email, password], function (error, results, fields){
            if(results.length > 0){
               res.end(JSON.stringify(results[0]))
            }else{
                res.send("Incorrect Username and/or Password");
            }
        });
    };
   
});


//SEARCH PRODUCT

app.get('/product/:product_name', function(req, res){
    var product_name = req.params.product_name

    if(!product_name){
        return res.status(400).send({error: true, message: 'Add product '});
    }
    conn.query("SELECT * FROM product WHERE product_name = ?"   , product_name, function(error, results, fields){
        if(error) throw error;
        if(results[0]){
            return res.send(results[0]);
        }else{
            return res.status(400).send({error: true, message: 'Mai mee product'});
        }
    });

});


//Add Product
app.post('/product', function(req, res){
    var addproduct = req.body
    if(!addproduct){
        return res.status(400).send({error: true, message: 'Please Add Product'});
    }

    conn.query('INSERT INTO product SET  ?', addproduct, function(error, results, fields){
        if(error) throw error;
        return res.send(results);
    });
});


//Call PRODUCT ID
app.get('/product/:product_id', function (req, res) {
  
    var product_id = req.params.product_id;
  
    if (!product_id) {
        return res.status(400).send({ error: true, message: 'Please provide dorm_id' });
    }
  
    dbConn.query("SELECT * FROM product WHERE product_id =?", product_id , function (error, results, fields) {
        if (error) throw error;
        return res.send(results[0]);
    });
  
});
//SEARCH Customer
app.get('/customer/:customer_name', function(req, res){
    var customer_name = req.params.customer_name

    if(!customer_name){
        return res.status(400).send({error: true, message: 'Add customer '});
    }
    conn.query('SELECT * FROM customer WHERE customer_name = ?', customer_name, function(error, results, fields){
        if(error) throw error;
        if(results[0]){
            return res.send(results[0]);
        }else{
            return res.status(400).send({error: true, message: 'Mai mee customer'});
        }
    });
});



//DELETE CUSTOMER

app.delete('/customer/:customer_id', function(req, res){
    var customer_id = req.params.customer_id;
    if(!customer_id){
        return res.status(400).send({error: true, message:'Please Provide Customer id'});
    }

    conn.query('DELETE FROM customer WHERE customer_id =?', customer_id, function(error, results, fields){
        if (error) throw error;

        return res.send({error: false, message: 'Customer DELETE Sed'});
    });
});

//EDIT CUSTOMER

app.put('/customer/:customer_id', function(req, res){

    var customer_id = req.params.customer_id;
    var cus = req.body
    if(!customer_id || !cus){
        return res.status(400).send({error: true, message:'Please Provude cutomer and customer data'});
    }

    conn.query('UPDATE customer SET ? WHERE customer_id = ?', [cus, customer_id], function(error, results, fields){
        if (error) throw error;
        return res.send({error: false, message: 'Customer has been update sed'});
    });

});


    




app.listen(3000, function(){
    console.log("Kum rung run bon port 3000 na " );

});

module.exports = app;