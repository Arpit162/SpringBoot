package com.demo.type;

public enum ActionType {
	REQUEST("request"), APPROVE("approve");

	private String action;

	private ActionType(String action) {
		this.action = action;
	}

	public String getActionType() {
		return action;
	}

	public void setActionType(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return action;
	}

	public static ActionType fromAction(String action) {
		for (ActionType changeActionType : ActionType.values()) {
			if (changeActionType.getActionType().equals(action))
				return changeActionType;
		}
		throw new RuntimeException("Unable to convert action " + action);
	}
}
