CREATE TABLE test_results
(
    id                    BIGSERIAL NOT NULL PRIMARY KEY,
    patient_id            INT8      NOT NULL,
    personality           float4,
    smi                   float4,
    ysq_emotional_privacy float4,
    ysq_instability       float4,
    ysq_doubt             float4,
    FOREIGN KEY (patient_id) REFERENCES users (id)
);