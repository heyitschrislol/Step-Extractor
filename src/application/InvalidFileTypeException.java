package application;

import java.io.IOException;

public class InvalidFileTypeException extends IOException {

	public InvalidFileTypeException(String path, String acceptedTypeMask) {
		super(String.format("File type '{0}' does not fall within the expected range: '{1}'", path, acceptedTypeMask));
	}
}
