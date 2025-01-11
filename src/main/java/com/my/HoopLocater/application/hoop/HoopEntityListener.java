package com.my.HoopLocater.application.hoop;

import com.my.HoopLocater.application.hoop.event.HoopCreatedEvent;
import com.my.HoopLocater.application.hoop.event.HoopUpdateEvent;
import com.my.HoopLocater.domain.hoop.Hoop;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class HoopEntityListener {

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostPersist
    public void createLogData(Hoop createdHoop) {
        publisher.publishEvent(new HoopCreatedEvent(createdHoop));
    }

//    @PostUpdate
//    public void updateLogData(Hoop updateHoop) {
//        publisher.publishEvent(new HoopUpdateEvent(updateHoop));
//    }

}
