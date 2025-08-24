

CREATE TABLE IF NOT EXISTS public."roles"(
	role_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	role_name varchar(150) NOT NULL unique
	);


CREATE TABLE IF NOT EXISTS public."users"(
	user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	fio varchar(150) NOT NULL,
	phone_number varchar (15) unique,
	avatar text,
	role_id UUID,
	CONSTRAINT role_id FOREIGN key(role_id) REFERENCES public."roles"(role_id)
	ON DELETE CASCADE
	ON UPDATE CASCADE
	);
	