package com.randeng.api.controller.dto.user;

import com.randeng.api.common.Page;
import com.randeng.api.model.User;

public class LeaderboardResponse {
    private User self;
    private Long selfPosition;
    private Page<User> users;

    public User getSelf() {
        return self;
    }

    public void setSelf(User self) {
        this.self = self;
    }

    public Long getSelfPosition() {
        return selfPosition;
    }

    public void setSelfPosition(Long selfPosition) {
        this.selfPosition = selfPosition;
    }

    public Page<User> getUsers() {
        return users;
    }

    public void setUsers(Page<User> users) {
        this.users = users;
    }
}
