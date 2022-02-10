
# http://localhost:3000/auth/newUser --> primero crea nuevo usuario en el postman
/*
{
    "name": "admin2",
    "username": "admin2",
    "email": "cortezkevinq2@gmail.com",
    "password": "admin2",
    "roles":["admin"]
}
*/
# http://localhost:3000/auth/login --> despues iniciar sesion para poder usar el servicio con el token
/*
{
    "username":"admin",
    "password":"admin"
}
*/
#Para registrar - ejemplo - primero colocar el token o retornara error status 401 no authorized
/*
{
    "category": {
        "id_category":"C0001"
        },
    "supplier": {
        "id_supplier":"S0001"
        },
    "name": "prueba",
    "mark": "Coca Cola",
    "description": "prueba",
    "url_image": "https://www.corporacionliderperu.com/shop/29610-large_default/fanta-gaseosas-x-3-lt-naranja.jpg",
    "expiration_date": "2021-10-10",
    "price": 8.5,
    "stock": 25
}
*/


### ORDERNAR LOS CAMPOS DE LAS TABLAS ANTES DE INSERTAR DATOS - SE GENERA DESORDENADO CON EL HIBERNATE
insert into Categories values('C0001','Bebidas','Productos Liquidos','https://i.pinimg.com/originals/17/53/07/17530782da06b17d1e6654526dbde2be.jpg','Activo');
insert into Categories values('C0002','Dulces','Productos Azucarados','https://sonorastar.com/wp-content/uploads/2020/10/dulces-mexicanos-2.jpg','Activo');
#insert into Categories values('C0001','Productos Liquidos','Bebidas','Activo','https://i.pinimg.com/originals/17/53/07/17530782da06b17d1e6654526dbde2be.jpg');
#insert into Categories values('C0002','Productos Azucarados','Dulces','Activo','https://sonorastar.com/wp-content/uploads/2020/10/dulces-mexicanos-2.jpg');

insert into Payment values('P0001','Pago por Paypal','Activo');
#insert into Payment values('P0001','Activo','Pago por Paypal');

insert into Suppliers values('S0001','Coca Cola','Lima',7412547,'Activo');
insert into Suppliers values('S0002','Ambrosoli','Lima',7584215,'Activo');
insert into Suppliers values('S0003','Primor','Lima',7412142,'Activo');
#insert into Suppliers values('S0001','Lima','Coca Cola',7412547,'Activo');
#insert into Suppliers values('S0002','Lima','Ambrosoli',7584215,'Activo');
#insert into Suppliers values('S0003','Lima','Primor',7412142,'Activo');

insert into Products values('PR0001','C0001','S0001','Fanta','Coca Cola','Gaseosa Fanta de 3 litros','https://www.corporacionliderperu.com/shop/29610-large_default/fanta-gaseosas-x-3-lt-naranja.jpg','2021-10-10',8.5,25,'Activo');
insert into Products values('PR0002','C0002','S0002','GloboPop','Ambrosoli','Chupetin sabor fresa','https://swissbrothers.com/2341-large_default/chupete-globo-pop-sabor-a-fresa-1-unidad.jpg','2022-12-10',1.5,50,'Activo');
insert into Products values('PR0003','C0002','S0001','Sublime','Nestle','Chocolate sublime Clásico','https://home.ripley.com.pe/Attachment/WOP_5/Holi/7055.jpg','2022-12-10',1.5,30,'Activo');

insert into Orders values('O0001','1','P0001','Kevin','2021-09-09','31.0','Activo') ;
## DETALLE DE PEDIDOS
insert into Order_details values('OD0001','O0001','PR0001','8.0','4','32.0','1.0','31.0');


 DELIMITER $$
CREATE  PROCEDURE sp_Register_Category(IN name varchar(255), IN description varchar(255), IN url_image varchar(255))
BEGIN
	SET @id_category = (SELECT CONCAT('C',RIGHT(CONCAT('000',RIGHT(MAX(id_category),4) + 1),4)) FROM Categories);	
	INSERT INTO Categories VALUES (@id_category,name,description,url_image,'Activo');
END$$
 DELIMITER ;
/*
 DELIMITER $$
CREATE  PROCEDURE sp_Register_Category(IN name varchar(255), IN description varchar(255), IN url_image varchar(255))
BEGIN
	SET @id_category = (SELECT CONCAT('C',RIGHT(CONCAT('000',RIGHT(MAX(id_category),4) + 1),4)) FROM Categories);	
	INSERT INTO Categories VALUES (@id_category,description,name,'Activo',url_image);
END$$
 DELIMITER ;
 */
  DELIMITER $$
CREATE  PROCEDURE sp_Register_Supplier(IN name varchar(255), IN address varchar(255), IN phone int)
BEGIN
	SET @id_supplier = (SELECT CONCAT('S',RIGHT(CONCAT('000',RIGHT(MAX(id_supplier),4) + 1),4)) FROM Suppliers);	
	INSERT INTO Suppliers VALUES (@id_supplier,name,address,phone,'Activo');
END$$
 DELIMITER ;
 /*
 DELIMITER $$
CREATE  PROCEDURE sp_Register_Supplier(IN name varchar(255), IN address varchar(255), IN phone int)
BEGIN
	SET @id_supplier = (SELECT CONCAT('S',RIGHT(CONCAT('000',RIGHT(MAX(id_supplier),4) + 1),4)) FROM Suppliers);	
	INSERT INTO Suppliers VALUES (@id_supplier,address,name,phone,'Activo');
END$$
 DELIMITER ;
 */
DELIMITER $$
CREATE  PROCEDURE sp_Register_Product(IN id_category varchar(255),IN id_supplier varchar(255),
 IN name varchar(255), IN mark varchar(255),IN description varchar(255), IN url_image varchar(255),IN  expiration_date varchar(255), 
in price double, IN stock int)
BEGIN
	SET @id_product = (SELECT CONCAT('PR',RIGHT(CONCAT('000',RIGHT(MAX(id_product),4) + 1),4)) FROM Products);	
	INSERT INTO Products VALUES (@id_product,id_category,id_supplier,name,mark,description,url_image,expiration_date,price,stock,'Activo');
END$$
 DELIMITER ;
 
 DELIMITER $$
CREATE  PROCEDURE sp_Register_Payment(IN tipo_payment varchar(255))
BEGIN
	SET @id_payment = (SELECT CONCAT('P',RIGHT(CONCAT('000',RIGHT(MAX(id_payment),4) + 1),4)) FROM Payment);	
	INSERT INTO Payment VALUES (@id_payment,'Activo',tipo_payment);
END$$
 DELIMITER ;
 
 DELIMITER $$
CREATE  PROCEDURE sp_Register_Orders(IN user_id varchar(255), IN id_payment varchar(255), IN order_date varchar(255), IN total double)
BEGIN
	SET @user = (SELECT name FROM user WHERE id_user = user_id);
	SET @id_order = (SELECT CONCAT('O',RIGHT(CONCAT('000',RIGHT(MAX(id_order),4) + 1),4)) FROM Orders);	
	INSERT INTO Orders VALUES (@id_order,user_id, id_payment, @user, order_date,total,'Activo');
END$$
 DELIMITER ; 
 
 DELIMITER $$
CREATE  PROCEDURE sp_Update_Orders(IN user_id char(5), IN id_payment char(5), IN order_date varchar(40), IN total double, IN order_id char(5))
BEGIN
	SET @user = (SELECT name FROM user WHERE id_user = user_id);	
	UPDATE Orders SET id_user = user_id, id_payment = id_payment, user = @user, order_date = order_date,total = total, state = 'Activo' where id_order = order_id;
END$$
 DELIMITER ;
 
 DELIMITER $$
CREATE  PROCEDURE sp_Register_Order_Details(IN product_id char(6),IN amount int, IN discount double)
BEGIN
	SET @unit_price = (SELECT price FROM products WHERE id_product = product_id);
    SET @subtotal = (select @unit_price * amount);    
    SET  @discount = (select @subtotal * discount);
    SET @total = (select @subtotal - @discount);
	SET @id_order_detail = (SELECT CONCAT('OD',RIGHT(CONCAT('000',RIGHT(MAX(id_order_detail),4) + 1),4)) FROM Order_details);	
    SET @id_order = (SELECT max(id_order) FROM orders);
	INSERT INTO Order_details VALUES (@id_order_detail,@id_order , product_id, @unit_price, amount,@subtotal,@discount,@total);
    UPDATE products set stock = stock - amount where id_product = product_id;
    END$$
 DELIMITER ;