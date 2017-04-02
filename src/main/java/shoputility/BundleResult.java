package shoputility;

public class BundleResult {
	
	private int numberOfBundle;
	private Bundle bundle;
	
	public BundleResult(int numberOfBundle, Bundle bundle){
		this.setNumberOfBundle(numberOfBundle);
		this.setBundle(bundle);
	}

	public int getNumberOfBundle() {
		return numberOfBundle;
	}

	private void setNumberOfBundle(int numberOfBundle) {
		this.numberOfBundle = numberOfBundle;
	}

	public Bundle getBundle() {
		return bundle;
	}

	private void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

}
