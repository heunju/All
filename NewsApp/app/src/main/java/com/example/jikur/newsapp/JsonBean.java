package com.example.jikur.newsapp;

import java.util.List;

/**
 * Created by jikur on 2017-07-12.
 */

public class JsonBean {
    private String result;
    private String resultMsg;
    private List<AdDeal> adDealList;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<AdDeal> getAdDealList() {
        return adDealList;
    }

    public void setAdDealList(List<AdDeal> adDealList) {
        this.adDealList = adDealList;
    }

    class AdDeal{

        private String dealName;
        private String dealId;
        private String price;
        private String thumbnailPath;
        private List<PriceSection> dealPriceSectionList; //AdDeal안에 있는 클래스 선언

        public String getDealName() {
            return dealName;
        }

        public void setDealName(String dealName) {
            this.dealName = dealName;
        }

        public String getDealId() {
            return dealId;
        }

        public void setDealId(String dealId) {
            this.dealId = dealId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getThumbnailPath() {
            return thumbnailPath;
        }

        public void setThumbnailPath(String thumbnailPath) {
            this.thumbnailPath = thumbnailPath;
        }

        public List<PriceSection> getDealPriceSectionList() {
            return dealPriceSectionList;
        }

        public void setDealPriceSectionList(List<PriceSection> dealPriceSectionList) {
            this.dealPriceSectionList = dealPriceSectionList;
        }


        class PriceSection {

            private String token;

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        } //end PriceSection

    } //end AdDeal
} //end JsonBean