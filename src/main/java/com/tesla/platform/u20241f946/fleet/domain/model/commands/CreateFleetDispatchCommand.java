package com.tesla.platform.u20241f946.fleet.domain.model.commands;

import com.tesla.platform.u20241f946.fleet.domain.model.entities.Waypoint;
import com.tesla.platform.u20241f946.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u20241f946.fleet.domain.model.valueobjects.DispatchStatus;
import com.tesla.platform.u20241f946.fleet.domain.model.valueobjects.RouteData;
import com.tesla.platform.u20241f946.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u20241f946.shared.domain.model.valueobjects.EnergyProfile;
import com.tesla.platform.u20241f946.shared.domain.model.valueobjects.TemporalWindow;

import java.util.List;

/** Carries the data required to register a fleet dispatch. */
public record CreateFleetDispatchCommand(
        DispatchCode dispatchCode, VehicleId vehicleId, RouteData routeData,
        TemporalWindow temporalWindow, EnergyProfile energyProfile,
        List<Waypoint> waypoints, DispatchStatus status, String safetyNotes) {}
