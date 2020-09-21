create database expense_note_db; -- Creates the new database
create user 'expense_note_auth'@'%' identified by 'expense_note_auth'; -- Creates the user
grant all on expense_note_db.* to 'expense_note_auth'@'%'; -- Gives all privileges to the new user on the newly created database