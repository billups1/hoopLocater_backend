package com.my.HoopLocater.application.hoopBackup.command;

import com.my.HoopLocater.domain.hoop.FloorType;
import com.my.HoopLocater.domain.hoop.FreeState;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.hoop.LeagueType;
import com.my.HoopLocater.domain.hoop.Light;
import com.my.HoopLocater.domain.hoop.StandardState;
import com.my.HoopLocater.domain.hoopBackup.HoopBackup;
import com.my.HoopLocater.domain.hoopBackup.UpdateMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HoopBackupCreateCommandTest {

    private Hoop createHoop(LeagueType type) {
        return Hoop.builder()
                .id(1L)
                .name("테스트 농구장")
                .type(type)
                .latitude(37.5)
                .longitude(127.0)
                .hoopCount(2)
                .floorType(FloorType.PARQUET)
                .light(Light.NO_INFO)
                .freeState(FreeState.NO_INFO)
                .standardState(StandardState.STANDARD)
                .commentCount(0)
                .likeCount(0)
                .build();
    }

    @Test
    @DisplayName("농구장 생성 시 백업의 type은 원본 농구장의 type을 따라간다")
    void createBackupWithHoopType() {
        Hoop hoop = createHoop(LeagueType.KBL);

        HoopBackup backup = HoopBackupCreateCommand.of(hoop, UpdateMethod.CREATE).create();

        assertThat(backup.getType()).isEqualTo(LeagueType.KBL);
        assertThat(backup.getHoopId()).isEqualTo(1L);
        assertThat(backup.getName()).isEqualTo("테스트 농구장");
        assertThat(backup.getUpdateMethod()).isEqualTo(UpdateMethod.CREATE);
    }

    @Test
    @DisplayName("type이 null인 농구장은 백업 시 HOOP으로 대체된다 (not-null 위반 방지)")
    void createBackupWithNullTypeFallsBackToHoop() {
        Hoop hoop = createHoop(null);

        HoopBackup backup = HoopBackupCreateCommand.of(hoop, UpdateMethod.CREATE).create();

        assertThat(backup.getType()).isEqualTo(LeagueType.HOOP);
    }

    @Test
    @DisplayName("WKBL 홈구장도 type이 그대로 백업된다")
    void createBackupWithWkblType() {
        Hoop hoop = createHoop(LeagueType.WKBL);

        HoopBackup backup = HoopBackupCreateCommand.of(hoop, UpdateMethod.CREATE).create();

        assertThat(backup.getType()).isEqualTo(LeagueType.WKBL);
    }
}
