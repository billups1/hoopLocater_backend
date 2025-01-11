package com.my.HoopLocater.application.hoopBackup;

import com.my.HoopLocater.application.hoop.event.HoopCreatedEvent;
import com.my.HoopLocater.application.hoop.event.HoopUpdateEvent;
import com.my.HoopLocater.application.hoopBackup.command.HoopBackupCreateCommand;
import com.my.HoopLocater.domain.hoopBackup.UpdateMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HoopBackupEventListener {

    private final HoopBackupCommandHandler commandHandler;

    @EventListener
    public void handle(HoopCreatedEvent hoopCreatedEvent) {
        commandHandler.handler(HoopBackupCreateCommand.of(hoopCreatedEvent.hoop(), UpdateMethod.CREATE));
    }

//    @EventListener
//    public void handle(HoopUpdateEvent hoopUpdateEvent) {
//        commandHandler.handler(HoopBackupCreateCommand.of(hoopUpdateEvent.hoop(), UpdateMethod.UPDATE));
//    }

}
