package design.state.impl;

import design.state.Result;
import design.state.State;
import design.state.Status;

public class CloseState extends State {
    @Override
    public Result arraignment(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public Result checkPass(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public Result checkRefuse(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public Result checkRevoke(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public Result close(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public Result open(String activityId, Enum<Status> currentStatus) {
        return null;
    }

    @Override
    public Result doing(String activityId, Enum<Status> currentStatus) {
        return null;
    }
}
