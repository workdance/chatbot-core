package com.workdance.chatbot.model.enums;

public enum ModelPlatformEnum {
    OPENAI("openai"),
    AZURE("azure"),
    BAICHUAN("baichuan"),
    TONGYI("tongyi"),
    BAILING("bailing"),
    BAIDU("baidu");

    private final String platform;

    ModelPlatformEnum(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }
}
