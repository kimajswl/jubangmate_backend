package com.example.jugangmate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomResponseException {
    // 200 error
    SUCCESS(HttpStatus.OK, "성공"),

    // 400 error
    WRONG_VALUE(HttpStatus.BAD_REQUEST, "잘못된 형식입니다."),
    WRONG_TIME(HttpStatus.BAD_REQUEST, "잘못된 시간입니다."),
    WRONG_PWD(HttpStatus.BAD_REQUEST, "잘못된 비밀번호 입니다.."),

    CHANNEL_CREATION_NO_PERMISSION(HttpStatus.BAD_REQUEST, "채널 생성 권한이 없습니다."),
    CHANNEL_DELETE_FAILED(HttpStatus.BAD_REQUEST, "채널 삭제 실패"),

    NON_EXISTENT_USER(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다."),
    NON_EXISTENT_MANAGER(HttpStatus.BAD_REQUEST, "존재하지 않는 아이디입니다."),
    NON_EXISTENT_CHANNELS(HttpStatus.BAD_REQUEST,"존재하지 않는 채널입니다."),

    DUPLICATE_CHANNEL_NAME(HttpStatus.BAD_REQUEST, "이미 존재하는 채널 이름 입니다."),
    DUPLICATE_TOPIC_NAME(HttpStatus.BAD_REQUEST,"이미 존재하는 토픽 이름입니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    DUPLICATE_APPLY_REQUEST(HttpStatus.BAD_REQUEST,"해당 이메일로 중복 요청을 시도하였습니다. 나중에 다시 요청해주세요."),

    INVALID_SESSION(HttpStatus.BAD_REQUEST, "유효하지 않은 세션입니다."),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "이메일 형식이 올바르지 않습니다."),
    INVALID_PHONE_NUMBER_FORMAT(HttpStatus.BAD_REQUEST, "전화번호 형식이 올바르지 않습니다."),
    INVALID_DATE(HttpStatus.BAD_REQUEST,"날짜 형식이 맞지 않습니다."),

    FAIL_EXTEND_CHANNEL_DURATION(HttpStatus.BAD_REQUEST,"기간 연장에 실패하였습니다."),

    DISAGREE_PERSONAL_INFORMATION(HttpStatus.BAD_REQUEST,"개인 정보 제공 체크 박스를 체크해주세요"),

    EXPIRED_SESSION(HttpStatus.BAD_REQUEST,"세션이 만료되었습니다."),

    UNKNOWN_RATING(HttpStatus.BAD_REQUEST,"등급이 존재하지 않습니다."),

    // 500 error
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류");

    private final HttpStatus httpStatus;
    private final String message;
}
