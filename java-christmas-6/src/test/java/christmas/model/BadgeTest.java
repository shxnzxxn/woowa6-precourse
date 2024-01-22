package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BadgeTest {

    @Test
    @DisplayName("2만원 이상일 때는 산타 배지")
    void 금액이_2만원_이상일_때_getBadge() {
        Badge badge = Badge.getBadge(20000);
        Assertions.assertThat(badge.toString()).isEqualTo(Badge.산타.toString());
    }

    @Test
    @DisplayName("2만원 이하일 때는 트리 배지")
    void 금액이_2만원_이하일_때_getBadge() {
        Badge badge = Badge.getBadge(12000);
        Assertions.assertThat(badge.toString()).isEqualTo(Badge.트리.toString());
    }

    @Test
    @DisplayName("만원 이하일 때는 별 배지")
    void 금액이_1만원_이하일_때_getBadge() {
        Badge badge = Badge.getBadge(6000);
        Assertions.assertThat(badge.toString()).isEqualTo(Badge.별.toString());
    }

    @Test
    @DisplayName("5천원 이하일 때는 배지 없음")
    void 금액이_5천원_이하일_때_getBadge() {
        Badge badge = Badge.getBadge(3000);
        Assertions.assertThat(badge.toString()).isEqualTo(Badge.없음.toString());
    }
}