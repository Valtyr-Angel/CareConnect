-- Table for storing patient information
CREATE TABLE Patients (
    PatientID INT PRIMARY KEY AUTO_INCREMENT,
    LastName VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    BirthDate DATE,
    Notes TEXT,
    EncryptedKey VARBINARY(255)
);

-- Table for storing patient treatments
CREATE TABLE PatientTreatments (
    TreatmentID INT PRIMARY KEY AUTO_INCREMENT,
    PatientID INT,
    TreatmentDetails TEXT,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID)
);

-- Table for storing patient activity logs
CREATE TABLE PatientActivityLogs (
    ActivityLogID INT PRIMARY KEY AUTO_INCREMENT,
    PatientID INT,
    ActivityDetails TEXT,
    LogTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID)
);

-- Table for storing roles
CREATE TABLE Roles (
    RoleID INT PRIMARY KEY AUTO_INCREMENT,
    RoleName VARCHAR(255) NOT NULL
);

-- Table for storing users (health workers, doctors, etc.)
CREATE TABLE Users (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(255) NOT NULL,
    PasswordHash VARBINARY(255) NOT NULL,
    RoleID INT,
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

-- Junction table for assigning multiple doctors to a patient
CREATE TABLE PatientDoctors (
    PatientID INT,
    UserID INT,
    PRIMARY KEY (PatientID, UserID),
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Table for storing permissions
CREATE TABLE Permissions (
    PermissionID INT PRIMARY KEY AUTO_INCREMENT,
    PermissionName VARCHAR(255) NOT NULL
);

-- Junction table for assigning permissions to roles
CREATE TABLE RolePermissions (
    RoleID INT,
    PermissionID INT,
    PRIMARY KEY (RoleID, PermissionID),
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID),
    FOREIGN KEY (PermissionID) REFERENCES Permissions(PermissionID)
);

-- Insert Roles
INSERT INTO Roles (RoleName) VALUES ('Doctor'), ('Nurse'), ('Admin'), ('Ambulance');

-- Insert Permissions
INSERT INTO Permissions (PermissionName) VALUES ('View Patients'), ('Edit Patients'), ('View Doctors'), ('Edit Doctors'), ('Emergency');

-- Assign Permissions to Roles
INSERT INTO RolePermissions (RoleID, PermissionID) VALUES
(1, 1), -- Doctor can view patients
(1, 3), -- Doctor can view doctors
(2, 1), -- Nurse can view patients
(3, 1), -- Admin can view patients
(3, 2), -- Admin can edit patients
(3, 3), -- Admin can view doctors
(3, 4), -- Admin can edit doctors
(4, 1), -- Ambulance can view patients
(4, 3), -- Ambulance can view doctors
(4, 5); -- Ambulance has emergency permission

-- Insert Users
INSERT INTO Users (Username, PasswordHash, RoleID) VALUES
('doctor1', 'hashed_password', 1),
('nurse1', 'hashed_password', 2),
('admin1', 'hashed_password', 3),
('ambulance1', 'hashed_password', 4);
