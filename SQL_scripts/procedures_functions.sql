delimiter $$
drop function if exists login $$

create function login(usernameIn varchar(30), passwordIn varchar(30))
returns boolean
deterministic
begin
	return IF(
    (select password from users where username = usernameIn) = passwordIn, True, false);
end $$

drop function if exists caught $$
create function caught(user_name_in varchar(30), pokemon_name_in varchar(30), level_in int) # java custom exception for level over 100
returns boolean
deterministic
begin
	IF NOT( SELECT EXISTS 
		(SELECT * FROM collected WHERE user_name = user_name_in and pokemon_name = pokemon_name_in) ) 
	THEN 
		insert into collected(user_name, pokemon_name, level, completed) 
			values (user_name_in, pokemon_name_in, level_in, (select if (level_in = 100, True, False) ) ) ;
		return True;
	ELSE
		return False;
	END IF;
    
end $$

drop function if exists levelUp $$ # if trigger on level is not made, update collected here
create function levelUp(user_name_in varchar(30), pokemon_name_in varchar(30), new_level int) 
returns boolean
deterministic
begin
	IF ( SELECT EXISTS 
		(SELECT * FROM collected WHERE user_name = user_name_in and pokemon_name = pokemon_name_in) ) 
	then 
		update collected set level = new_level  WHERE user_name = user_name_in and pokemon_name = pokemon_name_in;
        return True;
	else
		return false;
	end if;
end $$

delimiter ;

insert into users(username, password)
	values('username', 'password'); 
insert into pokemon(id, name)
	values(1, 'pikachu'); 
    
select caught('username', 'pikachu', 5);
select levelUp('username','pikachu', 10);

select * from collected;
delete from collected where user_name = 'username';
delete from users where username = 'username'; 
delete from pokemon where name = 'pikachu'; 



