## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# Connect to mysql and run as root user

# Create Databases
CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;

# Create database service accounts
# IDENTIFIED BY <password>:
# - Sets the account authentication plugin to the default plugin, passes the
#   cleartext 'auth_string' value to the plugin for hashing, and stores the
#   result in the mysql.user account row
#CREATE USER 'sfg_dev_user'@'localhost' IDENTIFIED BY 'guru';
#CREATE USER 'sfg_prod_user'@'localhost' IDENTIFIED BY 'guru';
#CREATE USER 'sfg_dev_user'@'%' IDENTIFIED BY 'guru';
#CREATE USER 'sfg_prod_user'@'%' IDENTIFIED BY 'guru';

# From host: 192.168.99.100
CREATE USER 'sfg_dev_user'@'192.168.99.100' IDENTIFIED BY 'guru';
CREATE USER 'sfg_prod_user'@'192.168.99.100' IDENTIFIED BY 'guru';

# From any host
CREATE USER 'sfg_dev_user'@'%' IDENTIFIED BY 'guru';
CREATE USER 'sfg_prod_user'@'%' IDENTIFIED BY 'guru';

# Database grants
# GRANT <privilege> ON <db> TO <username>@<host>
# '%' and '_' are only applicable in LIKE-queries
# '%' match 0 or more characters
# '_' match exactly one character
# '*' can only be used as a wildcard (or truncation) in a full text search
GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'192.168.99.100';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'192.168.99.100';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'192.168.99.100';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'192.168.99.100';

GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'192.168.99.100';
GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'192.168.99.100';
GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'192.168.99.100';
GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'192.168.99.100';

GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'%';

GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'%';
