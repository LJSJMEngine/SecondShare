package com.lec.spring.DTO;

public class UserDto {

    public static class SmsCertificationDto {
        private String phoneNumber;
        private String randomNumber;

        public String  getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getRandomNumber() {
            return randomNumber;
        }

        public void setRandomNumber(String randomNumber) {
            this.randomNumber = randomNumber;
        }

        // 마이페이지 - 핸드폰 번호 변경
        private String newPhoneNumber;

        public String getNewPhoneNumber() {
            return newPhoneNumber;
        }

        public void setNewPhoneNumber(String newPhoneNumber) {
            this.newPhoneNumber = newPhoneNumber;
        }
    }
}
