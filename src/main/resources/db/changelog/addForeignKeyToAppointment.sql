ALTER TABLE appointments
    ADD COLUMN consultation_id  BIGSERIAL NOT NULL ,
    ADD COLUMN consultation_duration INT,
    ADD COLUMN consultation_observation TEXT,
    ADD COLUMN consultation_recommendation TEXT;

ALTER TABLE appointments
    ADD CONSTRAINT fk_consultation
        FOREIGN KEY (consultation_id)
            REFERENCES consultations(id);
