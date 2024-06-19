package com.my.HoopLocater.application.hoop.event;

import com.my.HoopLocater.domain.hoop.Hoop;

public record HoopUpdateEvent(
        Hoop hoop
) {
}
