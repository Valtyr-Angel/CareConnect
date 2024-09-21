public class user {
        private int userId;
        private String userName;
        private String passwordHash;
        private int roleID;
        private String roleName;
        private int writePermission;
        private int readPermission;
        // setter opp spring framework, more to come - ludvig


        //private String lastName;
        //private String firstName;
    
        public user(int userId, String userName, String passwordHash, int roleID,String roleName,int writePermission,int readPermission) {
            this.userId = userId;
            this.userName=userName;
            this.passwordHash=passwordHash;
            this.roleID=roleID;
            this.roleName=roleName;
            this.writePermission=writePermission;
            this.readPermission=readPermission;
            //this.notes = notes;
            //this.lastName = lastName;
           // this.firstName = firstName;
        }
        
        
// getter setter autogen
        /**
         * @return int return the userId
         */
        public int getUserId() {
            return userId;
        }

        /**
         * @param userId the userId to set
         */
        public void setUserId(int userId) {
            this.userId = userId;
        }

        /**
         * @return String return the userName
         */
        public String getUserName() {
            return userName;
        }

        /**
         * @param userName the userName to set
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        /**
         * @return String return the passwordHash
         */
        public String getPasswordHash() {
            return passwordHash;
        }

        /**
         * @param passwordHash the passwordHash to set
         */
        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        /**
         * @return int return the roleID
         */
        public int getRoleID() {
            return roleID;
        }

        /**
         * @param roleID the roleID to set
         */
        public void setRoleID(int roleID) {
            this.roleID = roleID;
        }

        /**
         * @return String return the roleName
         */
        public String getRoleName() {
            return roleName;
        }

        /**
         * @param roleName the roleName to set
         */
        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        /**
         * @return int return the writePermission
         */
        public int getWritePermission() {
            return writePermission;
        }

        /**
         * @param writePermission the writePermission to set
         */
        public void setWritePermission(int writePermission) {
            this.writePermission = writePermission;
        }

        /**
         * @return int return the readPermission
         */
        public int getReadPermission() {
            return readPermission;
        }

        /**
         * @param readPermission the readPermission to set
         */
        public void setReadPermission(int readPermission) {
            this.readPermission = readPermission;
        }

}
