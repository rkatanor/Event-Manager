package com.test.configurations;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class Config {
	@Value("${amq.connection.url}")
	private String amq_url;
	@Value("${amq.queue.name}")
	private String queue_name;

	@Bean
	public Queue queue() {
		return new ActiveMQQueue(queue_name);
	}

	@Bean
	public ActiveMQConnectionFactory getConnection() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(amq_url);
		return factory;
	}

	@Bean
	public JmsTemplate getJmsTemplate() {
		return new JmsTemplate(getConnection());
	}
	 @Bean
	    public TaskExecutor threadPoolTaskExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(4);
	        executor.setMaxPoolSize(4);
	        executor.setThreadNamePrefix("default_task_executor_thread");
	        executor.initialize();
	        return executor;
	    }


}
