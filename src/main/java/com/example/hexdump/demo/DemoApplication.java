package com.example.hexdump.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	private List<HexdumpRequest> hexdumps;

	public DemoApplication() {
		this.hexdumps = new ArrayList<>();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println("Someone called hello.");
		return String.format("Hello %s!", name);
	}

	@PostMapping("/hexdump")
	@ResponseBody
	public Hexdump uploadHexdump(@RequestBody HexdumpRequest request) {
		System.out.println("=== Someone called hexdump ===");
		System.out.println(request.toString());
		hexdumps.add(request);
		saveToFile(hexdumps.size() - 1);
		return request.getTraffic().get(0);
	}

	private void saveToFile(int index) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String filename = "hexdump" + index + "-" + timestamp;

		File hexFile = new File(filename);
		try {
			if (hexFile.createNewFile()) {
				System.out.println("file created.");

				FileWriter fileWriter = new FileWriter(filename);
				fileWriter.write(hexdumps.get(index).toString());
				fileWriter.close();
			}
		} catch (IOException e) {
			System.out.println("failed to either to open a file or to write into it.");
			e.printStackTrace();
		}
	}
}
