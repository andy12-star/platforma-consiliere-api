CREATE TABLE appointments (
                              id BIGSERIAL NOT NULL PRIMARY KEY,
                              patient_id INT8 NOT NULL,
                              specialization VARCHAR(255) NOT NULL,
                              doctor_id INT8 NOT NULL,
                              date TIMESTAMP NOT NULL,
                              location VARCHAR(255) NOT NULL,
                              FOREIGN KEY (patient_id) REFERENCES users(id),
                              FOREIGN KEY (doctor_id) REFERENCES users(id)
);