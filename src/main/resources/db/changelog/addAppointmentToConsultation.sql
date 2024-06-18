alter table consultations
add column appointment_id BIGSERIAL NOT NULL ;

alter table consultations
add constraint fk_appointment
foreign key (appointment_id)
references appointments(id);