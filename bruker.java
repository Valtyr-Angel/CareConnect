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
}