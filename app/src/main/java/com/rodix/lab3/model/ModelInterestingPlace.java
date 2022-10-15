package com.rodix.lab3.model;

import com.google.gson.annotations.SerializedName;

public class ModelInterestingPlace {
    private String xid;
    private String name;
    private Description description;


    public static class Description {
        String wikipedia;
        Photo photo;
        String descr;

        public Description(String wikipedia, Photo photo, String descr) {
            this.wikipedia = wikipedia;
            this.photo = photo;
            this.descr = descr;
        }

        public String getWikipedia() {
            return wikipedia;
        }

        public Photo getPhoto() {
            return photo;
        }

        public String getDescr() {
            return descr;
        }

        public static class Photo {
            String source;
            Integer height;
            Integer width;

            public Photo(String source, Integer height, Integer width) {
                this.source = source;
                this.height = height;
                this.width = width;
            }

            public String getSource() {
                return source;
            }

            public Integer getHeight() {
                return height;
            }

            public Integer getWidth() {
                return width;
            }
        }
    }

    public ModelInterestingPlace(String xid, String name, Description description) {
        this.xid = xid;
        this.name = name;
        this.description = description;
    }

    public String getXid() {
        return xid;
    }

    public String getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }
}
