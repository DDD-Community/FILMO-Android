package com.ddd.filmo.presentation.setting.model

data class SettingUi(
    val name: String,
    val event: SettingEvent,
)

sealed class SettingEvent(val title: String) {
    object FAQClicked : SettingEvent(title = "FAQ")

    // 서비스 이용약관
    object ServiceClicked : SettingEvent(title = "서비스 이용약관")

    // 개인정보 처리방침
    object PersonalInformationClicked : SettingEvent("개인정보 처리방침")

    // 라이센스
    object LicenceClicked : SettingEvent("라이센스")
    object LogoutClicked : SettingEvent("로그아웃")
    object WithdrawClicked : SettingEvent("탈퇴하기")
}

val SettingUiList = listOf<SettingUi>(
    SettingUi(
        name = "FAQ",
        event = SettingEvent.FAQClicked,
    ),
    SettingUi(
        name = "서비스 이용약관",
        event = SettingEvent.ServiceClicked,
    ),
    SettingUi(
        name = "개인정보 수집 및 이용 약관",
        event = SettingEvent.PersonalInformationClicked,
    ),
    SettingUi(
        name = "라이센스",
        event = SettingEvent.LicenceClicked,
    ),
    SettingUi(
        name = "로그아웃",
        event = SettingEvent.LogoutClicked,
    ),
    SettingUi(
        name = "탈퇴하기",
        event = SettingEvent.WithdrawClicked,
    ),
)
