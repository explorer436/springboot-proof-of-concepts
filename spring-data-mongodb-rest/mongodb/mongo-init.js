db.createUser(
        {
            user: "persondbuser",
            pwd: "persondbpassword",
            roles: [
                {
                    role: "readWrite",
                    db: "persondb"
                }
            ]
        }
);
