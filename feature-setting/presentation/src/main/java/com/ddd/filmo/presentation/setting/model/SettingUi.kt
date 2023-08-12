package com.ddd.filmo.presentation.setting.model

data class SettingUi(
    val name: String,
    val event: SettingEvent,
)

sealed interface SettingEvent {
    object FAQClicked : SettingEvent

    // 서비스 이용약관
    object ServiceClicked : SettingEvent

    // 개인정보 처리방침
    object PersonalInformationClicked : SettingEvent

    // 라이센스
    object LicenceClicked : SettingEvent
    object LogoutClicked : SettingEvent
    object WithdrawClicked : SettingEvent
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
