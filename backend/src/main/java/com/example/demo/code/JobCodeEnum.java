package com.example.demo.code;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JobCodeEnum {

    DEV_PM("2247", "개발PM"),
    GAME_DEV("80", "게임개발"),
    TECH_SUPPORT("81", "기술지원"),
    DATA_SCIENTIST("2248", "데이터 사이언티스트"),
    DATA_ANALYST("82", "데이터분석가"),
    DATA_ENGINEER("83", "데이터엔지니어"),
    BACKEND_SERVER("84", "백엔드/서버개발"),
    SECURITY_MONITORING("2239", "보안관제"),
    SECURITY_CONSULTING("85", "보안컨설팅"),
    APP_DEV("86", "앱개발"),
    WEB_DEV("87", "웹개발"),
    WEB_MASTER("88", "웹마스터"),
    MAINTENANCE("89", "유지보수"),
    INFO_SECURITY("90", "정보보안"),
    PUBLISHER("91", "퍼블리셔"),
    FRONTEND("92", "프론트엔드"),
    BI_ENGINEER("2246", "BI 엔지니어"),
    CISO("93", "CISO"),
    CPO("94", "CPO"),
    DBA("95", "DBA"),
    FAE("96", "FAE"),
    GM("97", "GM(게임운영)"),
    ICT_CONSULTING("102", "ICT컨설팅"),
    IT_CONSULTING("98", "IT컨설팅"),
    QA_TESTER("99", "QA/테스터"),
    SYSTEM_ENGINEER("100", "SE(시스템엔지니어)"),
    SI_DEVELOPER("101", "SI개발"),
    SQA("2229", "SQA");

    private final String code;
    private final String codeName;

}
