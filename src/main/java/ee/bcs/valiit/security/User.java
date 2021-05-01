package ee.bcs.valiit.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

    @Table(name = "table_users")
    @Entity
    public class User {
        @Id
        //auto increment not ID
        //@GeneratedValue (strategy = GenerationType.IDENTITY)
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

