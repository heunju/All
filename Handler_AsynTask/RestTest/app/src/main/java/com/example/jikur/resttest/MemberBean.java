package com.example.jikur.resttest;

/**
 * Created by jikur on 2017-07-21.
 */

public class MemberBean extends CommonBean{

    private MemberBeanSub memberBeanSub;

    class MemberBeanSub
    {
        private String userId;
        private String userPw;
        private String name;
        private String addr;
        private String hp;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserPw() {
            return userPw;
        }

        public void setUserPw(String userPw) {
            this.userPw = userPw;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getHp() {
            return hp;
        }

        public void setHp(String hp) {
            this.hp = hp;
        }
    };

}
