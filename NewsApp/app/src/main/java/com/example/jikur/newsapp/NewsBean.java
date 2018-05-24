package com.example.jikur.newsapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jikur on 2017-07-11.
 */

public class NewsBean {

    @SerializedName("@version")
    private String version;
    private Channel channel;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }


    class Channel{
        private String title;
        private String link;
        private String description;
        private String pubDate;
        private List<item> item;


        public List<Channel.item> getItem() {
            return item;
        }

        public void setItem(List<Channel.item> item) {
            this.item = item;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }


        class item{
            private String title;
            private String link;
            private String description;
            private String pubDate;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }
        }
    }
}
