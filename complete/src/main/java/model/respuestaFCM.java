package model;

public class respuestaFCM {
	private static String success;
	private static String failure;
	private static String failed_registration_ids;
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getFailure() {
		return failure;
	}
	public void setFailure(String failure) {
		this.failure = failure;
	}
	public String getFailed_registration_ids() {
		return failed_registration_ids;
	}
	public void setFailed_registration_ids(String failed_registration_ids) {
		this.failed_registration_ids = failed_registration_ids;
	}
	@Override
	public String toString() {
        return "Quote{" +
                "success='" + success + '\'' +
                "failure='" + failure + '\'' +
                ", failed_registration_ids=" + failed_registration_ids +
                '}';
    }
}
