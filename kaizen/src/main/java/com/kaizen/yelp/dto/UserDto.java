package com.kaizen.yelp.dto;

import java.util.ArrayList;
import java.util.List;

import com.kaizen.yelp.domain.User;

public class UserDto {
        private List<User> users = new ArrayList<User>();

        public void addUser(User user)
        {
                users.add(user);
        }
        /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
}

