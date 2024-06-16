CREATE TABLE consultations (
                             id  BIGSERIAL NOT NULL PRIMARY KEY,
                             patient_id INT8 NOT NULL,
                             doctor_id INT8 NOT NULL,
                             services VARCHAR(255),
                             duration INT,
                             observations TEXT,
                             recommendations TEXT,
                             FOREIGN KEY (patient_id) REFERENCES users(id),
                             FOREIGN KEY (doctor_id) REFERENCES users(id)
);
