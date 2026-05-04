package se.fk.rimfrost.framework.handlaggning.exception;

public class HandlaggningException extends Exception
{

   public enum ErrorType
   {
      NOT_FOUND, BAD_REQUEST, SERVICE_UNAVAILABLE, UNEXPECTED_ERROR
   }

   private final ErrorType errorType;

   public HandlaggningException(ErrorType errorType, String message)
   {
      super(message);
      this.errorType = errorType;
   }

   public HandlaggningException(ErrorType errorType, String message, Throwable cause)
   {
      super(message, cause);
      this.errorType = errorType;
   }

   public ErrorType getErrorType()
   {
      return errorType;
   }
}
