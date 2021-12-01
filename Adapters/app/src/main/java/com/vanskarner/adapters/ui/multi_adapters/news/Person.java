package com.vanskarner.adapters.ui.multi_adapters.news;

public abstract class Person implements Element {

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

        @Override
        public int accept(Visitor visitor) {
            return visitor.visit(this);
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

        @Override
        public int accept(Visitor visitor) {
            return visitor.visit(this);
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

        @Override
        public int accept(Visitor visitor) {
            return visitor.visit(this);
        }
    }
}
