package com.my.HoopLocater.application.hoop.event;

import com.my.HoopLocater.domain.hoop.Hoop;

public record HoopCreatedEvent(
        Hoop hoop
) {
}
