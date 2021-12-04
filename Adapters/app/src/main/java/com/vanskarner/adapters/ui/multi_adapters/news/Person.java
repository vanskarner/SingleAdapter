package com.vanskarner.adapters.ui.multi_adapters.news;

import com.vanskarner.adapters.common.adaptersothers.AdapterItem;

public abstract class Person implements AdapterItem {

    public static class PersonOne extends Person {
        private final int image;
        private final String name;

        public PersonOne(int image, String name) {
            this.image = image;
            this.name = name;
        }

        public int getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

    }

    public static class PersonSecond extends Person {
        private final int image;
        private final String name;

        public PersonSecond(int image, String name) {
            this.image = image;
            this.name = name;
        }

        public int getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

    }

    public static class PersonThird extends Person {
        private final int image;
        private final String name;
        private final String age;

        public PersonThird(int image, String name, String age) {
            this.image = image;
            this.name = name;
            this.age = age;
        }

        public int getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getAge() {
            return age;
        }

    }
}
