package com.rodix.lab3.dto;

import com.google.gson.annotations.SerializedName;


public class ModelInterestingPlaceDto {
    private final MainInfoDto mainInfo;
    private final DescriptionDto description;

    public ModelInterestingPlaceDto(MainInfoDto mainInfo, DescriptionDto description) {
        this.mainInfo = mainInfo;
        this.description = description;
    }

    public static class DescriptionDto {
        @SerializedName("wikipedia")
        String wikipedia;

        @SerializedName("wikipedia_extracts")
        InfoDto info;

        @SerializedName("preview")
        PhotoDto photoDto;

        public DescriptionDto(String wikipedia, InfoDto info, PhotoDto photoDto) {
            this.wikipedia = wikipedia;
            this.info = info;
            this.photoDto = photoDto;
        }

        public PhotoDto getPhotoDto() {
            if (photoDto==null)
                return new PhotoDto(null,null,null);
            return photoDto;
        }

        public static class InfoDto {
            @SerializedName("text")
            String text;

            public String getDescr() {
                return text;
            }

            public InfoDto(String descr) {
                this.text = descr;
            }
        }

        public static class PhotoDto {
            @SerializedName("source")
            String source;
            @SerializedName("height")
            Integer height;
            @SerializedName("photo")
            Integer width;

            public PhotoDto(String source, Integer height, Integer width) {
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


        public String getWikipedia() {
            return wikipedia;
        }


        public InfoDto getInfo() {
            if (info == null)
                return new InfoDto(null);
            return info;
        }
    }

    public static class MainInfoDto {
        @SerializedName("xid")
        private String xid;
        @SerializedName("name")
        private String name;

        public MainInfoDto(String xid, String name) {
            this.xid = xid;
            this.name = name;
        }

        public String getXid() {
            return xid;
        }

        public String getName() {
            return name;
        }
    }

    public MainInfoDto getMainInfo() {
        return mainInfo;
    }

    public DescriptionDto getDescription() {
        if (description == null)
            return new DescriptionDto(null, null, null);
        return description;
    }
}
