package com.smartdrive;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

public class VehicleModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(VehicleService.class,VehicleServiceImpl.class);
    }
}
