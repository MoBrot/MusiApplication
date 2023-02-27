package de.musicapp.music;

public enum MusicAction {

    PLAY("PLAY"),
    STOP("STOPPPED"),
    SKIP("SKIPPING"),
    BACK("BACK"),
    RESTART("RESTARTING"),
    NULLCHECK("NULLCHECK FAILED");

    String actionName;

    MusicAction(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }
}
