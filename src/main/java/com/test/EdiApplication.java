package com.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.amqproducer.AMQProducer;

@SpringBootApplication
public class EdiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdiApplication.class, args);
		/*ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

		while (true) {
			AMQProducer producer = new AMQProducer();
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			executor.execute(producer);
		}*/
	}

}
