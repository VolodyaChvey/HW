                CREATE TABLE IF NOT EXISTS User
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(255) NOT NULL,
                password VARCHAR(255));

                CREATE TABLE IF NOT EXISTS `Order`
                (id LONG PRIMARY KEY AUTO_INCREMENT,
                user_id INT,
                total_price DOUBLE,
                FOREIGN KEY (user_id) REFERENCES User (id));

                CREATE TABLE IF NOT EXISTS Good
                (id INT PRIMARY KEY AUTO_INCREMENT,
                title VARCHAR(50) NOT NULL,
                price DOUBLE NOT NULL);

                CREATE TABLE IF NOT EXISTS Order_Good
                (id LONG PRIMARY KEY AUTO_INCREMENT,
                order_id LONG,
                good_id INT,
                FOREIGN KEY (order_id) REFERENCES `Order`(id),
                FOREIGN KEY (good_id) REFERENCES Good (id))