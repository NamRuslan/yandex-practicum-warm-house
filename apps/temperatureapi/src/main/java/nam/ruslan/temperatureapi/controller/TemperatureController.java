package nam.ruslan.temperatureapi.controller;

import nam.ruslan.temperatureapi.model.DeviceParams;
import org.springframework.web.bind.annotation.*;

@RestController
public class TemperatureController {

	@GetMapping("/temperature")
	public DeviceParams getTemperature(@RequestParam(required = false) String location) {
		return new DeviceParams(1L, Math.random() * 36, location);
	}

	@GetMapping("/temperature/{sensorId}")
	public DeviceParams getTemperatureBySensorId(@PathVariable String sensorId) {
		return new DeviceParams(1L, Math.random() * 36, sensorId);
	}
}