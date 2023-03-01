delimiter $$
# LOGIN FUNCTION
drop function if exists login $$
create function login(usernameIn varchar(30), passwordIn varchar(30))
returns boolean
deterministic
begin
	return IF(
    (select password from users where username = usernameIn) = passwordIn, True, false);
end $$

# CAUGHT FUNCTION
drop procedure if exists caught $$
create procedure caught(user_name_in varchar(30), pokemon_name_in varchar(30), level_in int) # java custom exception for level over 100
begin
	IF NOT( SELECT EXISTS 
		(SELECT * FROM collected WHERE user_name = user_name_in and pokemon_name = pokemon_name_in) ) 
	THEN 
		insert into collected(user_name, pokemon_name, level, completed) 
			values (user_name_in, pokemon_name_in, level_in, (select if (level_in = 100, True, False) ) ) ;
	END IF;
    
end $$

# LEVEL UP FUNCTION
drop procedure if exists levelUp $$ # if trigger on level is not made, update collected here
create procedure levelUp(user_name_in varchar(30), pokemon_name_in varchar(30), new_level int) 
begin
	IF ( SELECT EXISTS 
		(SELECT * FROM collected WHERE user_name = user_name_in and pokemon_name = pokemon_name_in) ) 
	then 
		update collected set level = new_level  WHERE user_name = user_name_in and pokemon_name = pokemon_name_in;
	end if;
end $$

# PROGRESS PROCEDURE
drop PROCEDURE if exists get_collection $$
CREATE PROCEDURE get_collection(user_name_in varchar(30))
BEGIN
    select * from collected where user_name = user_name_in;
end$$

# PROGRESS PROCEDURE
drop PROCEDURE if exists get_pokemon $$
CREATE PROCEDURE get_pokemon(user_name_in varchar(30), pokemon_name_in varchar(30))
BEGIN
    select * from collected where user_name = user_name_in and pokemon_name = pokemon_name_in;
end$$

##############
-- TRIGGERS --
##############
drop trigger if exists collectedCheck $$
create trigger collectedCheck 
before update 
on 
collected 
for each row 
BEGIN 
	if new.level >= 100 then set new.completed = true;
    elseif new.level < 100 then set new.completed = false;
    end if;
END$$


delimiter ;
    
call caught('username', 'pikachu', 5);
call caught('username', 'charmander', 5);
call caught('username2', 'Bulbasaur', 5);
call levelUp('username','pikachu', 100);
call levelUp('username','pikachu', 10);

call get_pokemon('username', 'pikachu');
call get_pokemon('username2', 'oddish');


call get_collection('username');
truncate collected;
-- truncate users;
-- truncate pokemon;





