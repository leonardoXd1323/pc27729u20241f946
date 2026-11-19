package com.tesla.platform.u20241f946.fleet.domain.model.aggregates;

import com.tesla.platform.u20241f946.fleet.domain.model.commands.CreateFleetDispatchCommand;
import com.tesla.platform.u20241f946.fleet.domain.model.entities.Waypoint;
import com.tesla.platform.u20241f946.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u20241f946.fleet.domain.model.valueobjects.DispatchStatus;
import com.tesla.platform.u20241f946.fleet.domain.model.valueobjects.RouteData;
import com.tesla.platform.u20241f946.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u20241f946.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import com.tesla.platform.u20241f946.shared.domain.model.valueobjects.EnergyProfile;
import com.tesla.platform.u20241f946.shared.domain.model.valueobjects.TemporalWindow;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/** Owns the route, schedule, energy profile, and stops for a fleet dispatch. */
@Getter
public class FleetDispatch extends AbstractDomainAggregateRoot<FleetDispatch> {
    private final Long id;
    private final DispatchCode dispatchCode;
    private final VehicleId vehicleId;
    private final RouteData routeData;
    private final TemporalWindow temporalWindow;
    private final EnergyProfile energyProfile;
    private final Set<Waypoint> waypoints;
    private final DispatchStatus status;
    private final String safetyNotes;

    /**
     * Creates a dispatch with no database ID assigned yet.
     * @param command the registration data
     */
    public FleetDispatch(CreateFleetDispatchCommand command) {
        this(null, command);
    }

    private FleetDispatch(Long id, CreateFleetDispatchCommand command) {
        if (command.dispatchCode() == null || command.vehicleId() == null ||
                command.routeData() == null || command.temporalWindow() == null ||
                command.energyProfile() == null) {
            throw new IllegalArgumentException("Dispatch data is incomplete.");
        }

        List<Waypoint> stops = command.waypoints();
        if (stops == null || stops.isEmpty() || stops.size() > 5) {
            throw new IllegalArgumentException("A dispatch must have between one and five waypoints.");
        }
        if (stops.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Waypoint entries are required.");
        }

        this.id = id;
        this.dispatchCode = command.dispatchCode();
        this.vehicleId = command.vehicleId();
        this.routeData = command.routeData();
        this.temporalWindow = command.temporalWindow();
        this.energyProfile = command.energyProfile();
        this.waypoints = Set.copyOf(stops);
        this.status = command.status() == null ? DispatchStatus.PENDING : command.status();
        this.safetyNotes = command.safetyNotes();
    }

    /**
     * Associates the generated ID with the saved dispatch.
     * @param id the database-generated ID
     * @return dispatch with the assigned ID
     */
    public FleetDispatch withId(Long id) {
        return new FleetDispatch(id, new CreateFleetDispatchCommand(
                dispatchCode, vehicleId, routeData, temporalWindow, energyProfile,
                List.copyOf(waypoints), status, safetyNotes));
    }
}
