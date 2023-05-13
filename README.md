# FakeLMS

DBMS micro project- Instructions to run

IMT2021018 IMT2021066 IMT2021067

## Installation
Create a new database called fakelms

```bash
CREATE DATABASE fakelms
```
Use the source command to run all the sql queries stored in the .sql files

```bash
SOURCE (pathname of create_FakeLMS.sql)
SOURCE (pathname of insert_FakeLMS.sql)
SOURCE (pathname of alter_FakeLMS.sql)
```
## Usage

On VSCode open the project folder. In the filename DAO_Factory.java change the variable PASS to your sql password. 

Run the project by by running the driver code which is DAO_Demo.java.

To login as a student, type 1 as student_id and 'password' as password.

To login as a professor, type 1 as professor_id and 'password' as password.
