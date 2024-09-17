private abstract user{

    private int userId;

    private String userName;

    //her er passord, fiks dette plis, Ludvig ^^ -emil

    private int roleId;

    user(int i, String n, int r){
        userId = i;
        userName = n;
        roleId = r;
    }
    public String getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setUserId(int i) {
        userId = i;
    }
    public void setUserName(String n) {
        userName = n;
    }
    public void setRoleId(int r) {
        roleId = r;
    }

    public void printUser(){
        System.out.println("User ID: " + userId);
        System.out.println("User Name: " + userName);
        System.out.println("Role ID: " + roleId);
    }
    
}