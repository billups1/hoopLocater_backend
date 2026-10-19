package com.my.HoopLocater.infrastructure.web.hoop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.server.PathContainer;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * /api/v1/hoop/pro 가 /api/v1/hoop/{hoopId} 에 가로채이지 않는지 검증한다.
 * (증상: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: "pro")
 * <p>
 * Spring Boot 3 는 PathPatternParser 를 사용하며, 여러 패턴에 동시에 매칭될 때
 * 더 구체적인 패턴(리터럴 &gt; 정규식 변수 &gt; 순수 변수)이 우선 선택된다.
 */
class HoopQueryControllerMappingTest {

    private final PathPatternParser parser = new PathPatternParser();

    private boolean matches(String patternValue, String path) {
        return parser.parse(patternValue).matches(PathContainer.parsePath(path));
    }

    @Test
    @DisplayName("/api/v1/hoop/{hoopId:[0-9]+} 는 'pro' 경로를 잡지 않는다")
    void proPathIsNotMatchedByPathVariable() {
        String variablePattern = "/api/v1/hoop/{hoopId:[0-9]+}";

        assertThat(matches(variablePattern, "/api/v1/hoop/1"))
                .as("숫자 경로는 {hoopId} 에 매칭되어야 한다")
                .isTrue();
        assertThat(matches(variablePattern, "/api/v1/hoop/pro"))
                .as("'pro' 는 {hoopId} 에 매칭되지 않아야 한다")
                .isFalse();
    }

    @Test
    @DisplayName("/pro 리터럴 패턴이 {hoopId} 변수 패턴보다 우선순위가 높다")
    void literalPatternWinsOverPathVariable() {
        PathPattern literal = parser.parse("/api/v1/hoop/pro");
        PathPattern variable = parser.parse("/api/v1/hoop/{hoopId:[0-9]+}");

        assertThat(literal.compareTo(variable))
                .as("리터럴 패턴이 변수 패턴보다 먼저 정렬되어야 한다")
                .isLessThan(0);
    }

    @Test
    @DisplayName("/api/v1/hoop/detail/{hoopId:[0-9]+} 는 숫자만 매칭된다")
    void detailPathVariableAcceptsOnlyNumbers() {
        String detailPattern = "/api/v1/hoop/detail/{hoopId:[0-9]+}";

        assertThat(matches(detailPattern, "/api/v1/hoop/detail/12")).isTrue();
        assertThat(matches(detailPattern, "/api/v1/hoop/detail/abc")).isFalse();
    }
}
