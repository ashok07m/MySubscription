package com.sub.network.model;

import java.io.Serializable;

public class SubscriptionModel implements Serializable {

    private UserInfo userInfo;
    private ServiceInfo serviceInfo;
    private SubscriptionInfo subscriptionInfo;
    private ProductInfo productInfo;

    public SubscriptionModel(UserInfo userInfo, ServiceInfo serviceInfo,
                             SubscriptionInfo subscriptionInfo, ProductInfo productInfo) {
        this.userInfo = userInfo;
        this.serviceInfo = serviceInfo;
        this.subscriptionInfo = subscriptionInfo;
        this.productInfo = productInfo;
    }

    public SubscriptionModel() {
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public SubscriptionInfo getSubscriptionInfo() {
        return subscriptionInfo;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public void setSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        this.subscriptionInfo = subscriptionInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    @Override
    public String toString() {
        return "SubscriptionModel{" +
                "userInfo=" + userInfo +
                ", serviceInfo=" + serviceInfo +
                ", subscriptionInfo=" + subscriptionInfo +
                ", productInfo=" + productInfo +
                '}';
    }

    public static final class UserInfo implements Serializable {
        private String email;
        private String title;
        private String firstName;

        public UserInfo(String email, String title, String firstName) {
            this.email = email;
            this.title = title;
            this.firstName = firstName;
        }

        public String getEmail() {
            return email;
        }

        public String getTitle() {
            return title;
        }

        public String getFirstName() {
            return firstName;
        }

    }


    public static final class ServiceInfo implements Serializable {
        private String msnId;
        private String credit;
        private String expiryDate;

        public ServiceInfo(String msnId, String credit, String expiryDate) {
            this.msnId = msnId;
            this.credit = credit;
            this.expiryDate = expiryDate;
        }

        public String getMsnId() {
            return msnId;
        }

        public String getCredit() {
            return credit;
        }

        public String getExpiryDate() {
            return expiryDate;
        }
    }


    public static final class SubscriptionInfo implements Serializable {
        private String remainingBal;
        private String expiryDate;

        public SubscriptionInfo(String remainingBal, String expiryDate) {
            this.remainingBal = remainingBal;
            this.expiryDate = expiryDate;
        }

        public String getRemainingBal() {
            return remainingBal;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

    }


    public static final class ProductInfo implements Serializable {
        private String name;
        private String price;

        public ProductInfo(String name, String price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }
    }
}
