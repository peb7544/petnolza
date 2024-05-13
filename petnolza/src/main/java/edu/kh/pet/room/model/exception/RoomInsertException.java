package edu.kh.pet.room.model.exception;

public class RoomInsertException extends RuntimeException {

	public RoomInsertException() {
		
		super("객실 삽입 중 예외 발생");
	}
	
	public RoomInsertException(String message) {
		
		super(message);
	}
}
