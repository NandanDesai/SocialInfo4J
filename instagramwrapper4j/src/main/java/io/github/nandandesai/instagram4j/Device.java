package io.github.nandandesai.instagram4j;


public class Device {

	public static final Device[] GOOD_DEVICES = {
			/*
			 * Samsung Galaxy S7 Edge SM-G935F. Released: March 2016.
			 * https://www.amazon.com/Samsung-SM-G935F-Factory-Unlocked-Smartphone/dp/
			 * B01C5OIINO
			 * https://www.handsetdetection.com/properties/devices/Samsung/SM-G935F
			 */
			new Device("23", "6.0.1", "640dpi", "1440x2560", "samsung", "SM-G935F", "hero2lte", "samsungexynos8890"),

			/*
			 * OnePlus 3T. Released: November 2016.
			 * https://www.amazon.com/OnePlus-A3010-64GB-Gunmetal-International/dp/
			 * B01N4H00V8 https://www.handsetdetection.com/properties/devices/OnePlus/A3010
			 */
			new Device("24/7.0; 380dpi; 1080x1920; OnePlus; ONEPLUS A3010; OnePlus3T; qcom"),

			/*
			 * LG G5. Released: April 2016.
			 * https://www.amazon.com/LG-Unlocked-Phone-Titan-Warranty/dp/B01DJE22C2
			 * https://www.handsetdetection.com/properties/devices/LG/RS988
			 */
			new Device("23/6.0.1; 640dpi; 1440x2392; LGE/lge; RS988; h1; h1"),

			/*
			 * Huawei Mate 9 Pro. Released: January 2017.
			 * https://www.amazon.com/Huawei-Dual-Sim-Titanium-Unlocked-International/dp/
			 * B01N9O1L6N https://www.handsetdetection.com/properties/devices/Huawei/LON-L29
			 */
			new Device("24/7.0; 640dpi; 1440x2560; HUAWEI; LON-L29; HWLON; hi3660"),

			/*
			 * ZTE Axon 7. Released: June 2016.
			 * https://www.frequencycheck.com/models/OMYDK/zte-axon-7-a2017u-dual-sim-lte-a-
			 * 64gb https://www.handsetdetection.com/properties/devices/ZTE/A2017U
			 */
			new Device("23/6.0.1; 640dpi; 1440x2560; ZTE; ZTE A2017U; ailsa_ii; qcom"),

			/*
			 * Samsung Galaxy S7 SM-G930F. Released: March 2016.
			 * https://www.amazon.com/Samsung-SM-G930F-Factory-Unlocked-Smartphone/dp/
			 * B01J6MS6BC
			 * https://www.handsetdetection.com/properties/devices/Samsung/SM-G930F
			 */
			new Device("23/6.0.1; 640dpi; 1440x2560; samsung; SM-G930F; herolte; samsungexynos8890") };

	public Device(String formatted) {
		String[] format = formatted.split("; ");
		this.DEVICE_ANDROID_VERSION = format[0].split("/")[0];
		this.DEVICE_ANDROID_RELEASE = format[0].split("/")[1];
		this.DPI = format[1];
		this.DISPLAY_RESOLUTION = format[2];
		this.DEVICE_MANUFACTURER = format[3];
		this.DEVICE_MODEL = format[4];
		this.DEVICE = format[5];
		this.CPU = format[6];
	}

    public Device(String DEVICE_ANDROID_VERSION, String DEVICE_ANDROID_RELEASE, String DPI, String DISPLAY_RESOLUTION, String DEVICE_MANUFACTURER, String DEVICE_MODEL, String DEVICE, String CPU) {
        this.DEVICE_ANDROID_VERSION = DEVICE_ANDROID_VERSION;
        this.DEVICE_ANDROID_RELEASE = DEVICE_ANDROID_RELEASE;
        this.DPI = DPI;
        this.DISPLAY_RESOLUTION = DISPLAY_RESOLUTION;
        this.DEVICE_MANUFACTURER = DEVICE_MANUFACTURER;
        this.DEVICE_MODEL = DEVICE_MODEL;
        this.DEVICE = DEVICE;
        this.CPU = CPU;
    }

    /**
	 * Android version to mimic
	 */
	private final String DEVICE_ANDROID_VERSION;

	/**
	 * Android Release
	 */
	private final String DEVICE_ANDROID_RELEASE;

	/**
	 * Device DPI
	 */
	private final String DPI;

	/**
	 * Device Display Resolution
	 */
	private final String DISPLAY_RESOLUTION;

	/**
	 * Device to mimic
	 */
	private final String DEVICE_MANUFACTURER;

	/**
	 * Model to mimic
	 */
	private final String DEVICE_MODEL;

	/**
	 * Device name
	 */
	private final String DEVICE;

	/**
	 * Device CPU
	 */
	private final String CPU;

    public String getDEVICE_ANDROID_VERSION() {
        return DEVICE_ANDROID_VERSION;
    }

    public String getDEVICE_ANDROID_RELEASE() {
        return DEVICE_ANDROID_RELEASE;
    }

    public String getDPI() {
        return DPI;
    }

    public String getDISPLAY_RESOLUTION() {
        return DISPLAY_RESOLUTION;
    }

    public String getDEVICE_MANUFACTURER() {
        return DEVICE_MANUFACTURER;
    }

    public String getDEVICE_MODEL() {
        return DEVICE_MODEL;
    }

    public String getDEVICE() {
        return DEVICE;
    }

    public String getCPU() {
        return CPU;
    }
}
