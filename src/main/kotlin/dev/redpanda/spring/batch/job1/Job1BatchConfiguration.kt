/*! Copyright (c) 2020 LSWare Inc. All rights reserved */
package dev.redpanda.spring.batch.job1

import dev.redpanda.spring.batch.Job.JOB1
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.batch.repeat.RepeatStatus.CONTINUABLE
import org.springframework.batch.repeat.RepeatStatus.FINISHED
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Job1BatchConfiguration(
  private val jobBuilderFactory: JobBuilderFactory,
  private val stepBuilderFactory: StepBuilderFactory
) {

  @Bean
  fun job1(): Job = jobBuilderFactory.get(JOB1)
    .start(step1(null))
    .build()

  @Bean
  @JobScope
  fun step1(@Value("#{jobParameters[seed]}") seed: Long?): Step = stepBuilderFactory.get("$JOB1-step")
    .tasklet { _, _ ->
      println(">> $JOB1-step (seed: $seed)")
      return@tasklet FINISHED
    }
    .build()
}
