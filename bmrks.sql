DROP user bmrks

Create user bmrks
identified by bmrks
Default Tablespace users
Temporary Tablespace Temp
QUOTA 10M on users;

grant connect to bmrks;
grant resource to bmrks;
grant create session to bmrks;
grant create table to bmrks;
grant create view to bmrks;

connect bmrks/bmrks;