package com.blanc.recrute.member.dto;

import java.time.LocalDate;

public class MemberDTO {
    private int id;
    private String memberId;
    private String name;
    private String email;
    private String password;
    private LocalDate birth;
    private String phoneNumber;
    private int gender;

    public MemberDTO(int id, String memberId, String name, String email, String password, LocalDate birth, String phoneNumber, int gender) {
        this.id = id;
        this.name = name;
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public MemberDTO(Builder builder) {
        id = builder.id;
        memberId = builder.memberId;
        name = builder.name;
        email = builder.email;
        password = builder.password;
        birth = builder.birth;
        phoneNumber = builder.phoneNumber;
        gender = builder.gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getGender() {
        return gender;
    }

    public static class Builder {
        private int id;
        private String memberId;
        private String email;
        private String password;
        private LocalDate birth;
        private String phoneNumber;
        private int gender;
        private String name;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder memberId(String memberId) {
            this.memberId = memberId;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder birth(LocalDate birth) {
            this.birth = birth;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder gender(int gender) {
            this.gender = gender;
            return this;
        }

        public MemberDTO build() {
            return new MemberDTO(this);
        }
    }
}
